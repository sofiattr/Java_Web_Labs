<script setup>
import { ref, onMounted } from 'vue'

const foodList = ref([])

onMounted(async () => {
  try {
    const response = await fetch('/Lab_2_Project/api/catfood')
    foodList.value = await response.json()
  } catch (error) {
    console.error('Помилка при завантаженні даних:', error)
  }
})
</script>

<template>
  <div style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; padding: 30px; max-width: 900px; margin: 0 auto; background-color: #fffaf0; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <h1 style="color: #d35400; text-align: center;">🐱 Магазин "Муркотик"</h1>
    <h2 style="color: #7f8c8d; text-align: center; font-size: 1.2rem;">Каталог кормів</h2>

    <div v-if="foodList.length > 0" style="display: flex; gap: 20px; flex-wrap: wrap; justify-content: center; margin-top: 20px;">

      <div v-for="(food, index) in foodList" :key="index" style="background-color: white; border: 2px solid #f39c12; padding: 20px; border-radius: 12px; width: 250px; text-align: center;">

        <img :src="food.imageUrl" :alt="food.brand" style="width: 100%; height: 180px; object-fit: cover; border-radius: 8px; margin-bottom: 15px;">

        <h3 style="color: #e67e22; margin-top: 0; border-bottom: 1px solid #eee; padding-bottom: 10px;">{{ food.brand }}</h3>
        <p style="font-size: 1rem; text-align: left;"><strong>🍖 Смак:</strong> {{ food.flavor }}</p>
        <p style="font-size: 1rem; text-align: left;"><strong>⚖️ Вага:</strong> {{ food.weightKg }} кг</p>
        <p style="font-size: 1.2rem; color: #27ae60; font-weight: bold;">{{ food.price }} грн</p>
      </div>

    </div>

    <div v-else style="text-align: center; color: #95a5a6; margin-top: 30px;">
      <p>🐾 Завантажуємо свіженький корм з сервера...</p>
    </div>
  </div>
</template>