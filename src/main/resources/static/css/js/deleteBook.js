async function deleteBook(button) {
// Получаем родительский элемент карточки
const card = button.closest('.bookInfo');

console.log(card)

// Получаем ID товара
const bookId = card.id;



// Отправляем PUT-запрос на сервер
try {
const response = await fetch(`/api/deleteBook/${bookId}`, {
    method: 'delete'
});

if (response.ok) {
    window.location.href = 'http://localhost:5000/main';
} else {
alert('Помилка видалення товару!');
}
} catch (error) {
console.error('Помилка запиту:', error);
alert('Не вдалося оновити товар!');
}
}