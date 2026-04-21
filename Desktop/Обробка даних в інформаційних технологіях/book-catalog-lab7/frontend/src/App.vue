<template>
  <div class="container">
    <h1>📚 Мій Онлайн-Каталог Книг </h1>

    <div class="add-form">
      <h3>Додати нову книгу</h3>
      <input v-model="newBook.title" placeholder="Назва книги" />
      <input v-model="newBook.author" placeholder="Автор" />
      <input v-model="newBook.description" placeholder="Короткий опис" />
      <input v-model="newBook.imageUrl" placeholder="Посилання на картинку (URL)" />
      <button @click="addBook">Додати книгу</button>
    </div>

    <div v-if="books.length === 0" class="empty-message">
      <p>Каталог поки що порожній. Додайте свою першу книгу вище! 👆</p>
    </div>

    <div class="books-grid" v-else>
      <div v-for="book in books" :key="book.id" class="book-card">
        <img :src="book.imageUrl || 'https://via.placeholder.com/150'" alt="Обкладинка" class="book-cover"/>
        <h2>{{ book.title }}</h2>
        <p><strong>Автор:</strong> {{ book.author }}</p>
        <p class="desc">{{ book.description }}</p>
        <button class="delete-btn" @click="deleteBook(book.id)">❌ Видалити</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const books = ref([]);
const newBook = ref({ title: '', author: '', description: '', imageUrl: '' });

// Адреса твого Java-сервера
const API_URL = 'http://localhost:8081/api/books';

const fetchBooks = async () => {
  try {
    const response = await axios.get(API_URL);


    if (response.data._embedded && response.data._embedded.books) {
      books.value = response.data._embedded.books.map(book => {
        // Витягуємо ID з посилання
        const urlParts = book._links.self.href.split('/');
        book.id = urlParts[urlParts.length - 1];
        return book;
      });
    } else {
      books.value = []; // Якщо база порожня
    }
  } catch (error) {
    console.error('Помилка завантаження книг:', error);
  }
};

const addBook = async () => {
  if (!newBook.value.title) {
    alert('Назва книги обов\'язкова!');
    return;
  }
  try {
    await axios.post(API_URL, newBook.value);
    newBook.value = { title: '', author: '', description: '', imageUrl: '' };
    fetchBooks();
  } catch (error) {
    console.error('Помилка додавання книги:', error);
  }
};

const deleteBook = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`);
    fetchBooks();
  } catch (error) {
    console.error('Помилка видалення книги:', error);
  }
};

onMounted(() => {
  fetchBooks();
});
</script>

<style scoped>
.container { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; text-align: center; }
.add-form { background: #f9f9f9; padding: 20px; border-radius: 8px; margin-bottom: 30px; display: flex; flex-direction: column; gap: 10px; }
input { padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
button { padding: 10px; background: #42b983; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }
button:hover { background: #33996b; }
.empty-message { padding: 30px; background: #fff3cd; color: #856404; border-radius: 8px; margin-top: 20px; font-size: 18px; font-weight: bold; border: 1px solid #ffeeba; }
.delete-btn { background: #ff4d4d; margin-top: 10px; }
.delete-btn:hover { background: #cc0000; }
.books-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 20px; }
.book-card { border: 1px solid #eee; padding: 15px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); display: flex; flex-direction: column; justify-content: space-between; }
.book-cover { width: 100%; height: 250px; object-fit: cover; border-radius: 4px; }
.desc { font-size: 0.9em; color: #555; }
</style>