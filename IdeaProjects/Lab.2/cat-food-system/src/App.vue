<script setup>
import { ref, onMounted } from 'vue'

const foodList = ref([])

// Змінні для форми
const isEditing = ref(false)
const editingId = ref(null)

const newFood = ref({
  brand: '', flavor: '', weightKg: 1.0, price: 100.0, imageUrl: ''
})

// READ: Отримання списку
const loadData = async () => {
  try {
    const response = await fetch('/Lab_2_Project/api/catfood')
    foodList.value = await response.json()
  } catch (error) {
    console.error('Помилка при завантаженні даних:', error)
  }
}

// CREATE / UPDATE: Збереження товару
const saveFood = async () => {
  try {
    if (isEditing.value) {
      // Якщо ми редагуємо - відправляємо PUT запит
      await fetch('/Lab_2_Project/api/catfood', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: editingId.value, ...newFood.value })
      })
      isEditing.value = false
      editingId.value = null
    } else {
      // Якщо додаємо новий - відправляємо POST запит
      await fetch('/Lab_2_Project/api/catfood', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newFood.value)
      })
    }
    // Очищаємо форму і оновлюємо список
    newFood.value = { brand: '', flavor: '', weightKg: 1.0, price: 100.0, imageUrl: '' }
    loadData()
  } catch (error) {
    console.error('Помилка при збереженні:', error)
  }
}

// Підготовка до редагування (перенесення даних у форму)
const editFood = (food) => {
  isEditing.value = true
  editingId.value = food.id
  newFood.value = { ...food } // Копіюємо дані
  window.scrollTo({ top: 0, behavior: 'smooth' }) // Скролимо сторінку вгору до форми
}

// DELETE: Видалення
const deleteFood = async (id) => {
  try {
    await fetch(`/Lab_2_Project/api/catfood?id=${id}`, { method: 'DELETE' })
    loadData()
  } catch (error) {
    console.error('Помилка при видаленні:', error)
  }
}

onMounted(() => { loadData() })
</script>

<template>
  <div style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; padding: 30px; max-width: 900px; margin: 0 auto; background-color: #fffaf0; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <h1 style="color: #d35400; text-align: center;">🐱 Магазин "Муркотик"</h1>

    <div :style="{ background: isEditing ? '#fff9c4' : 'white', padding: '20px', borderRadius: '12px', border: '2px dashed #f39c12', marginBottom: '30px', textAlign: 'center', transition: '0.3s' }">
      <h3 style="margin-top: 0; color: #e67e22;">{{ isEditing ? '✏️ Редагувати корм' : '➕ Додати новий корм' }}</h3>
      <div style="display: flex; gap: 10px; flex-wrap: wrap; justify-content: center; align-items: center;">
        <input v-model="newFood.brand" placeholder="Бренд" style="padding: 8px; border: 1px solid #ccc; border-radius: 5px;" />
        <input v-model="newFood.flavor" placeholder="Смак" style="padding: 8px; border: 1px solid #ccc; border-radius: 5px;" />
        <input v-model="newFood.weightKg" type="number" placeholder="Вага (кг)" style="padding: 8px; border: 1px solid #ccc; border-radius: 5px; width: 80px;" />
        <input v-model="newFood.price" type="number" placeholder="Ціна" style="padding: 8px; border: 1px solid #ccc; border-radius: 5px; width: 80px;" />
        <input v-model="newFood.imageUrl" placeholder="Посилання на фото" style="padding: 8px; border: 1px solid #ccc; border-radius: 5px; width: 200px;" />
        <button @click="saveFood" :style="{ backgroundColor: isEditing ? '#f39c12' : '#27ae60', color: 'white', border: 'none', padding: '10px 20px', borderRadius: '5px', cursor: 'pointer', fontWeight: 'bold' }">
          {{ isEditing ? 'Зберегти зміни' : 'Додати товар' }}
        </button>
      </div>
    </div>

    <div v-if="foodList.length > 0" style="display: flex; gap: 20px; flex-wrap: wrap; justify-content: center;">
      <div v-for="food in foodList" :key="food.id" style="background-color: white; border: 2px solid #f39c12; padding: 20px; border-radius: 12px; width: 250px; text-align: center; position: relative;">

        <img :src="food.imageUrl" :alt="food.brand" style="width: 100%; height: 180px; object-fit: cover; border-radius: 8px; margin-bottom: 15px;">

        <h3 style="color: #e67e22; margin-top: 0; border-bottom: 1px solid #eee; padding-bottom: 10px;">{{ food.brand }}</h3>
        <p style="font-size: 1rem; text-align: left;"><strong>🍖 Смак:</strong> {{ food.flavor }}</p>
        <p style="font-size: 1rem; text-align: left;"><strong>⚖️ Вага:</strong> {{ food.weightKg }} кг</p>
        <p style="font-size: 1.2rem; color: #27ae60; font-weight: bold;">{{ food.price }} грн</p>

        <div style="display: flex; gap: 10px; margin-top: 15px;">
          <button @click="editFood(food)" style="background-color: #3498db; color: white; border: none; padding: 8px; width: 50%; border-radius: 5px; cursor: pointer; font-weight: bold;">
             Редагувати
          </button>
          <button @click="deleteFood(food.id)" style="background-color: #e74c3c; color: white; border: none; padding: 8px; width: 50%; border-radius: 5px; cursor: pointer; font-weight: bold;">
             Видалити
          </button>
        </div>
      </div>
    </div>

    <div v-else style="text-align: center; color: #95a5a6; margin-top: 30px;">
      <p>🐾 Каталог порожній. Додайте перший товар!</p>
    </div>
  </div>
</template>