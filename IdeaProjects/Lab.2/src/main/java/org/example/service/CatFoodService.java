package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.CatFood;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CatFoodService {
    // Шлях до файлу (створиться автоматично в папці Tomcat або проєкті)
    private static final String FILE_PATH = "cat_food_data.json";
    private Gson gson = new Gson();

    // Читання списку з файлу
    public List<CatFood> getAll() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<CatFood>>(){}.getType());
        } catch (IOException e) {
            return new ArrayList<>(); // Якщо файлу немає, повертаємо порожній список
        }
    }

    // Збереження списку у файл
    private void saveAll(List<CatFood> list) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Додавання нового товару
    public void add(CatFood food) {
        List<CatFood> list = getAll();
        // Автоматична генерація ID: беремо макс ID + 1
        int newId = list.stream().mapToInt(CatFood::getId).max().orElse(0) + 1;
        food.setId(newId);
        list.add(food);
        saveAll(list);
    }

    // Видалення товару за ID
    public void delete(int id) {
        List<CatFood> list = getAll();
        list.removeIf(food -> food.getId() == id);
        saveAll(list);
    }
    // Оновлення існуючого товару (PUT)
    public void update(CatFood updatedFood) {
        List<CatFood> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            // Шукаємо товар з таким самим ID
            if (list.get(i).getId() == updatedFood.getId()) {
                list.set(i, updatedFood); // Замінюємо старий об'єкт на новий
                break;
            }
        }
        saveAll(list); // Зберігаємо у файл
    }
}