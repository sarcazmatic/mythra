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
    <p id="user-name" hidden>${userName}</p>
    <p id="char-name" hidden>${charName}</p>
    <p id="char-id" hidden>${charId}</p>
    <div class="section">
        <h2>Редактирование атрибутов</h2>
        <div id="attributes">
            <div class="field-row" data-name="Сила">
                <span>Сила</span>
                <output>${attribStr}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribStrMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Ловкость">
                <span>Ловкость</span>
                <output>${attribDex}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribDexMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Телосложение">
                <span>Телосложение</span>
                <output>${attribCon}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribConMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Интеллект">
                <span>Интеллект</span>
                <output>${attribInt}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribIntMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Мудрость">
                <span>Мудрость</span>
                <output>${attribWis}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribWisMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Харизма">
                <span>Харизма</span>
                <output>${attribCha}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${attribChaMan}" />
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
                <output>${athletics}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${athleticsMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Акробатика">
                <span>Акробатика</span>
                <output>${acrobatics}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${acrobaticsMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Ловкость-рук">
                <span>Ловкость рук</span>
                <output>${sleight_of_hand}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${sleight_of_handMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Скрытность">
                <span>Скрытность</span>
                <output>${stealth}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${stealthMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Инициатива">
                <span>Инициатива</span>
                <output>${initiative}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${initiativeMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Аркана">
                <span>Аркана</span>
                <output>${arcana}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${arcanaMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="История">
                <span>История</span>
                <output>${history}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${historyMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Расследование">
                <span>Расследование</span>
                <output>${investigation}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${investigationMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Природа">
                <span>Природа</span>
                <output>${nature}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${natureMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Религия">
                <span>Религия</span>
                <output>${religion}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${religionMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Проницательность">
                <span>Проницательность</span>
                <output>${insight}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${insightMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Медицина">
                <span>Медицина</span>
                <output>${medicine}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${medicineMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Восприятие">
                <span>Восприятие</span>
                <output>${perception}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${perceptionMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Выживание">
                <span>Выживание</span>
                <output>${survival}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${survivalMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Обращение с животными">
                <span>Обращение с животными</span>
                <output>${animal_handling}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${animal_handlingMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Обман">
                <span>Обман</span>
                <output>${deception}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${deceptionMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Запугивание">
                <span>Запугивание</span>
                <output>${intimidation}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${intimidationMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Выступление">
                <span>Выступление</span>
                <output>${performance}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${performanceMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
            <div class="field-row" data-name="Убеждение">
                <span>Убеждение</span>
                <output>${persuasion}</output>
                <button onclick="changeValue(this, -1)">-</button>
                <input type="number" value="${persuasionMan}" />
                <button onclick="changeValue(this, 1)">+</button>
            </div>
        </div>
        <button class="update-button" onclick="updateSkills()">Обновить навыки</button>
    </div>
</div>

<script>
    var userName = document.getElementById("user-name").innerText;
    var charName = document.getElementById("char-name").innerText;
    var charId = document.getElementById("char-id").innerText;
    var uriText = "/api/manualEdit/" + charId;
    const urlChar = '/' + userName + '/' + charName + '/charsheet';


    function changeValue(button, delta) {
        const input = button.parentElement.querySelector('input'); <%-- тут у нас, значит, он выискивыает родителя у элемента button и уже родителя ищет блок input  --%>
        const output = button.parentElement.querySelector('output');
        input.value = parseInt(input.value) + delta;
        output.value = parseInt(output.value) + delta;
    }

    function updateAttributes() {
        const attrs = document.querySelectorAll('#attributes .field-row');
        const result = {};
        attrs.forEach(row => {
            const name = row.querySelector('span').innerText;
            const value = row.querySelector('input').value;
            result[name] = value;
        });
        console.log(attrs)
        console.log(result)
        const xhr = new XMLHttpRequest()
        xhr.open('PUT', uriText)
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const body = JSON.stringify(result);
        xhr.send(body)
        xhr.onload = function () {
            location.replace(urlChar)
        }
    }

    function updateSkills() {
        const skills = document.querySelectorAll('#skills .field-row');
        const result = {};
        skills.forEach(row => {
            const name = row.querySelector('span').innerText;
            const value = row.querySelector('input').value;
            result[name] = value;
        });
        const xhr = new XMLHttpRequest()
        xhr.open('PUT', uriText)
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const body = JSON.stringify(result);
        xhr.send(body)
        xhr.onload = function () {
            location.replace(urlChar)
        }
    }
</script>

</body>
</html>