package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.CatFood;
import org.example.service.CatFoodService;

import java.io.IOException;

@WebServlet("/api/catfood")
public class CatFoodServlet extends HttpServlet {
    private CatFoodService service = new CatFoodService();
    private Gson gson = new Gson();

    // GET: Отримати список усіх кормів із файлу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(gson.toJson(service.getAll()));
    }

    // POST: Додати новий корм (отримує JSON від фронтенду)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CatFood newFood = gson.fromJson(req.getReader(), CatFood.class);
        service.add(newFood);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    // DELETE: Видалити корм за його ID
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            service.delete(Integer.parseInt(idParam));
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID не вказано");
        }
    }
    // PUT: Оновити існуючий товар
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Читаємо оновлені дані з браузера
        CatFood updatedFood = gson.fromJson(req.getReader(), CatFood.class);
        service.update(updatedFood); // Відправляємо в сервіс
        resp.setStatus(HttpServletResponse.SC_OK); // Кажемо, що все добре (200)
    }
}