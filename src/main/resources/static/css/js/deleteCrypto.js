async function deleteCrypto(button) {
// Получаем родительский элемент карточки
const card = button.closest('.cryptoInfo');

console.log(card)

// Получаем ID товара
const Id = card.id;



// Отправляем PUT-запрос на сервер
try {
const response = await fetch(`/api/deleteCrypto/${Id}`, {
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