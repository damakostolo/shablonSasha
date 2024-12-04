async function updateAmount(button, operation) {
// Получаем родительский элемент карточки
const card = button.closest('.cart');

console.log(card)

// Получаем ID товара
const Id = card.id;


// Получаем текущее значение количества
const amountSpan = card.querySelector('.amount');
const quantityInput = card.querySelector('.quantity-input');
const inputValue = parseInt(quantityInput.value) || 0;

// Считаем новое значение
let newAmount = parseInt(amountSpan.textContent) || 0;
console.log(newAmount)

if (operation === 1) {
newAmount += inputValue;
} else if (operation === -1) {
newAmount -= inputValue;
}
console.log(newAmount)

if (newAmount < 0) {
alert('Кількість не може бути від\'ємною!');
return;
}

console.log(newAmount)

// Отправляем PUT-запрос на сервер
try {
const response = await fetch(`/api/addWallpapers/${Id}`, {
method: 'PUT',
headers: {
'Content-Type': 'application/json'
},
body: JSON.stringify({ amount: newAmount })
});

if (response.ok) {
// Обновляем значение на странице
amountSpan.textContent = newAmount;
quantityInput.value = 1; // Сбрасываем инпут
} else {
alert('Помилка оновлення товару!');
}
} catch (error) {
console.error('Помилка запиту:', error);
alert('Не вдалося оновити товар!');
}
}