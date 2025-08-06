<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>DND Редактирование</title>
    <link href="https://fonts.googleapis.com/css2?family=Philosopher&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Philosopher', cursive;
            background-color: #1e1e1e;
            color: #fff;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
        }

        .flex-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }

        .section {
            flex: 1 1 450px;
            background-color: #2a2a2a;
            padding: 20px;
            border-radius: 10px;
            min-width: 300px;
            max-width: 500px;
        }

        .field-row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin: 6px 0;
        }

        .field-row span {
            flex: 1;
        }

        .field-row button {
            width: 30px;
            height: 30px;
            font-size: 18px;
            margin: 0 5px;
            background-color: #444;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .field-row input {
            width: 50px;
            text-align: center;
            font-size: 16px;
            background-color: #333;
            color: #fff;
            border: 1px solid #555;
            border-radius: 5px;
        }

        .update-button {
            display: block;
            margin: 20px auto 0;
            padding: 10px 20px;
            background-color: #007acc;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        .update-button:hover {
            background-color: #005fa3;
        }
    </style>
</head>
<body>

<div class="flex-container">
    <div class="section">
        <h2>Редактирование атрибутов</h2>
        <div id="attributes">
            <div class="field-row" data-name="Сила">
                <span>Сила</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribStr}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Ловкость">
                <span>Ловкость</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribDex}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Телосложение">
                <span>Телосложение</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribCon}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Интеллект">
                <span>Интеллект</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribInt}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Мудрость">
                <span>Мудрость</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribWis}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Харизма">
                <span>Харизма</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribCha}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
        </div>
        <button class="update-button" onclick="updateAttributes()">Обновить атрибуты</button>
    </div>

    <div class="section">
        <h2>Редактирование навыков</h2>
        <div id="skills">
            <div class="field-row" data-name="Атлетика">
                <span>Атлетика</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Акробатика">
                <span>Акробатика</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Ловкость-рук">
                <span>Ловкость рук</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Скрытность">
                <span>Скрытность</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Инициатива">
                <span>Инициатива</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Аркана">
                <span>Аркана</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="История">
                <span>История</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Расследование">
                <span>Расследование</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Природа">
                <span>Природа</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Религия">
                <span>Религия</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Проницательность">
                <span>Проницательность</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Медицина">
                <span>Медицина</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Восприятие">
                <span>Восприятие</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Выживание">
                <span>Выживание</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Обращение с животными">
                <span>Обращение с животными</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Обман">
                <span>Обман</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Запугивание">
                <span>Запугивание</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Выступление">
                <span>Выступление</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Убеждение">
                <span>Убеждение</span>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="10" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
        </div>
        <button class="update-button" onclick="updateSkills()">Обновить навыки</button>
    </div>
</div>

<script>
    function changeValue(button, delta) {
        const input = button.parentElement.querySelector('input'); <%-- тут у нас, значит, он выискивыает родителя у элемента button и уже родителя ищет блок input  --%>
        input.value = parseInt(input.value) + delta;
    }

    function updateAttributes() {
        const attrs = document.querySelectorAll('#attributes .field-row');
        const result = {};
        attrs.forEach(row => {
            const name = row.getAttribute('data-name');
            const value = row.querySelector('input').value;
            result[name] = value;
        });
        console.log("Атрибуты:", result);
        alert("Атрибуты обновлены!");
    }

    function updateSkills() {
        const skills = document.querySelectorAll('#skills .field-row');
        const result = {};
        skills.forEach(row => {
            const name = row.getAttribute('data-name');
            const value = row.querySelector('input').value;
            result[name] = value;
        });
        console.log("Навыки:", result);
        alert("Навыки обновлены!");
    }
</script>

</body>
</html>