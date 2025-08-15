<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Mythra D&D Service</title>
    <style>
        :root {
            --bg: #0d0d0d;
            --panel: #222;
            --muted: #a7b0bd;
            --text: #fff;
            --accent: #6aa6ff;
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 24px;
            font-family: 'Philosopher', cursive;
            color: var(--text);
            background: #0d0d0d;
            zoom: 90%;
        }

        h1 {
            text-align: center;
            margin: 0 0 20px;
            font-size: 22px;
            letter-spacing: .2px;
        }

        .wrap {
            max-width: fit-content;
            margin: 0 auto;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .card {
            background: var(--panel);
            border: 1px solid #2b3036;
            border-radius: 14px;
            padding: 18px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, .25);
        }

        .grid {
            display: grid;
            gap: 12px;
        }

        .optional-field {
            display: none;
        }

        label {
            display: block;
            font-size: 16px;
            color: var(--muted);
            margin-bottom: 6px;
        }

        input[type="text"], select, textarea, input[type="number"] {
            width: 100%;
            padding: 10px 12px;
            border-radius: 10px;
            border: 1px solid #555;
            background: #333;
            color: var(--text);
            outline: none;
            transition: border-color .15s ease;
        }

        textarea {
            min-height: 140px;
            resize: vertical;
        }

        input:focus, select:focus, textarea:focus {
            border-color: var(--accent);
        }

        .row {
            display: grid;
            grid-template-columns: 1fr 160px;
            gap: 12px;
        }

        .hint {
            font-size: 14px;
            color: var(--muted);
        }

        .actions {
            display: flex;
            gap: 10px;
            margin-top: 12px;
        }

        button {
            appearance: none;
            border: 1px solid #2b3036;
            font-family: 'Philosopher', cursive;
            background-color: #666;
            color: white;
            padding: 10px 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        button.secondary {
            background: #0f1216;
            color: var(--text);
            border: 1px solid #30363d;
            box-shadow: none;
        }

        .error {
            color: #ff8a8a;
            font-size: 12px;
            margin-top: 6px;
            display: none;
        }

        pre {
            margin: 0;
            background: #0f1216;
            border: 1px solid #30363d;
            padding: 12px;
            border-radius: 10px;
            overflow: auto;
        }

        @media (max-width: 840px) {
            .wrap {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<h1>Создание предмета</h1>

<div class="wrap">
    <p id="char-id" hidden>${charId}</p>
    <p id="user-name" hidden>${userName}</p>
    <p id="char-name" hidden>${charName}</p>
    <!-- Форма -->
    <form id="itemForm" class="card grid" novalidate>
        <div>
            <label for="type">Тип предмета</label>
            <select id="type" name="type" required>
                <option value="" disabled selected>Выберите тип…</option>
                <option value="RING">Кольцо</option>
                <option value="AMULET">Амулет</option>
                <option value="RANGED_WEAPON">Дальнобойное оружие</option>
                <option value="MELEE_WEAPON">Рукопашное оружие</option>
                <option value="AMMUNITION">Амуниция</option>
                <option value="HELM">Шлем</option>
                <option value="SHIELD">Щит</option>
                <option value="CUIRASS">Кираса</option>
                <option value="PAULDRONS">Наплечники</option>
                <option value="GAUNTLETS">Перчатки</option>
                <option value="LEGGINGS">Поножи</option>
                <option value="GRIEVES">Сапоги</option>
                <option value="CLOAK">Плащ</option>
                <option value="MISC">Прочее</option>
                <option value="CONSUMABLE">Полезное</option>
                <option value="TEXTS">Тексты</option>
            </select>
            <div class="error" id="typeErr">Выберите тип предмета.</div>
        </div>

        <div>
            <label for="name">Название предмета</label>
            <input id="name" name="name" type="text" placeholder="Напр.: Перстень следопыта" required maxlength="120"/>
            <div class="error" id="nameErr">Укажите название.</div>
        </div>

        <div class="optional-field" id="armor_opt">
            <label for="armor-type">Тип доспеха</label>
            <select id="armor-type" name="armorType">
                <option value="" disabled selected>Выберите тип…</option>
                <option value="CLOTH">Ткань</option>
                <option value="LIGHT_ARMOR">Легкий доспех</option>
                <option value="MEDIUM_ARMOR">Средний доспех</option>
                <option value="HEAVY_ARMOR">Тяжелый доспех</option>
            </select>
            <div class="error" id="armorTypeErr">Укажите тип доспеха.</div>
        </div>

        <div class="optional-field" id="weapon_opt">
            <label for="melee-weapon-mastery" style="display: none">Вид оружия</label>
            <select id="melee-weapon-mastery" name="meleeWeaponMastery" style="display: none">
                <option value="" disabled selected>Выберите вид…</option>
                <option value="CLUB">Дубинка</option>
                <option value="DAGGER">Кинжал</option>
                <option value="GREATCLUB">Тяжёлая дубина</option>
                <option value="HANDAXE">Топор</option>
                <option value="JAVELIN">Метательное копьё</option>
                <option value="LIGHT_HAMMER">Лёгкий молот</option>
                <option value="MACE">Булавa</option>
                <option value="QUARTERSTAFF">Посох</option>
                <option value="SICKLE">Серп</option>
                <option value="BATTLEAXE">Боевой топор</option>
                <option value="FLAIL">Цеп</option>
                <option value="GREATAXE">Грандиозный топор</option>
                <option value="GREATSWORD">Двуручный меч</option>
                <option value="HALBERD">Алебарда</option>
                <option value="LANCE">Копьё (лансерская пика)</option>
                <option value="LONGSWORD">Длинный меч</option>
                <option value="MAUL">Кувалда</option>
                <option value="MORNINGSTAR">Утренняя звезда</option>
                <option value="PIKE">Пика</option>
                <option value="RAPIER">Рапира</option>
                <option value="SCIMITAR">Скимитар</option>
                <option value="SHORTSWORD">Короткий меч</option>
                <option value="TRIDENT">Трезубец</option>
                <option value="WAR_PICK">Боевая кирка</option>
                <option value="WARHAMMER">Боевой молот</option>
                <option value="WHIP">Кнут</option>
            </select>
            <label for="ranged-weapon-mastery" style="display: none">Вид оружия</label>
            <select id="ranged-weapon-mastery" name="rangedWeaponMastery" style="display: none">
                <option value="" disabled selected>Выберите вид…</option>
                <option value="LIGHT_CROSSBOW">Лёгкий арбалет</option>
                <option value="DART">Дротик</option>
                <option value="SLING">Праща</option>
                <option value="HAND_CROSSBOW">Ручной арбалет</option>
                <option value="HEAVY_CROSSBOW">Тяжёлый арбалет</option>
                <option value="LONGBOW">Длинный лук</option>
                <option value="NET">Сеть</option>
            </select>
            <label for="numberOfDice">Кол-во костей урона</label>
            <input id="numberOfDice" name="numberOfDice" type="number" placeholder="Это первая цифра в 1к6" required
                   maxlength="120"/>
            <label for="qualityOfDice">Тип костей урона</label>
            <input id="qualityOfDice" name="qualityOfDice" type="number" placeholder="Это вторая цифра в 1к6" required
                   maxlength="120"/>
            <div style="display: flex; justify-content: space-between;">
                <label for="isFinesse">Оружие фехтовальное?</label>
                <input id="isFinesse" name="isFinesse" type="checkbox"/>
                <label for="isUniversal">Оружие универсальное?</label>
                <input id="isUniversal" name="isUniversal" type="checkbox"/>
            </div>
            <div class="error" id="weaponErr">Укажите особенности оружия.</div>
        </div>

        <div>
            <label for="desc">Описание предмета</label>
            <textarea id="desc" name="description" placeholder="Кратко опишите внешний вид и свойства…"
                      maxlength="2000"></textarea>
        </div>

        <div class="row">
            <div>
                <label for="weight">Вес (кг)</label>
                <!-- Принимаем 0.00…9999.99; заменяем запятую на точку и валидируем по RegExp -->
                <input id="weight" name="weight" type="text" inputmode="decimal" placeholder="0.00" autocomplete="off"
                       aria-describedby="wHint"
                       pattern="^\\d{1,4}(?:\\.\\d{1,2})?$"/>
                <div class="hint" id="wHint">Формат: десятичное число с точкой (напр., 0.50 или 1.00).</div>
                <div class="error" id="weightErr">Введите вес в формате 0.50 или 1.00.</div>
            </div>
        </div>

        <div class="actions">
            <button type="submit">Создать предмет</button>
            <button type="button" class="secondary" id="resetBtn">Сбросить</button>
        </div>
    </form>

</div>

<script>
    const form = document.getElementById('itemForm');
    const type = document.getElementById('type');
    const weightInput = document.getElementById('weight');
    const resetBtn = document.getElementById('resetBtn');

    type.addEventListener('change', () => {
            const selectedType = type.value;
            if (selectedType === "CUIRASS" ||
                selectedType === "GAUNTLETS" ||
                selectedType === "LEGGINGS" ||
                selectedType === "GRIEVES" ||
                selectedType === "HELM" ||
                selectedType === "SHIELD" ||
                selectedType === "PAULDRONS") {
                document.getElementById('melee-weapon-mastery').style.display = "none";
                document.getElementById('ranged-weapon-mastery').style.display = "none";
                document.getElementById('weapon_opt').style.display = "none";
                document.getElementById('armor_opt').style.display = "block";
            } else if (selectedType === "MELEE_WEAPON") {
                document.getElementById('melee-weapon-mastery').style.display = "block";
                document.getElementById('ranged-weapon-mastery').style.display = "none";
                document.getElementById('weapon_opt').style.display = "block";
                document.getElementById('armor_opt').style.display = "none";
            } else if (selectedType === "RANGED_WEAPON") {
                document.getElementById('melee-weapon-mastery').style.display = "none";
                document.getElementById('ranged-weapon-mastery').style.display = "block";
                document.getElementById('weapon_opt').style.display = "block";
                document.getElementById('armor_opt').style.display = "none";
            } else {
                document.getElementById('melee-weapon-mastery').style.display = "none";
                document.getElementById('ranged-weapon-mastery').style.display = "none";
                document.getElementById('weapon_opt').style.display = "none";
                document.getElementById('armor_opt').style.display = "none";
            }
        }
    )

    // Нормализуем вес: заменяем запятую на точку на лету
    weightInput.addEventListener('input', () => {
        const cur = weightInput.value.replace(',', '.');
        if (cur !== weightInput.value) weightInput.value = cur;
    });

    function collectData(strict = true) {
        const type = form.type.value || null;
        const name = form.name.value.trim() || null;
        const description = form.description.value.trim() || null;
        let weightRaw = (form.weight.value || '').replace(',', '.').trim();

        const armorType = form.armorType.value || null;

        const weaponMastery = form.rangedWeaponMastery.value || form.meleeWeaponMastery.value || null;
        const numberOfDice = form.numberOfDice.value || null;
        const qualityOfDice = form.qualityOfDice.value || null;
        const isUniversal = form.isUniversal.checked;
        const isFinesse = form.isFinesse.checked;


        // Валидируем по RegExp (точка, до 2 знаков)
        const weightPattern = /^\d{1,4}(?:\.\d{1,2})?$/;
        let weight = null;
        if (weightRaw !== '') {
            if (weightPattern.test(weightRaw)) {
                weight = Number(weightRaw);
            } else if (strict) {
                throw new Error('bad_weight');
            }
        }

        return {type, name, armorType, description, weight, weaponMastery, numberOfDice, qualityOfDice, isUniversal, isFinesse};
    }

    function showError(id, show) {
        const el = document.getElementById(id);
        el.style.display = show ? 'block' : 'none';
    }

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        // Скрыть все ошибки
        showError('typeErr', false);
        showError('nameErr', false);
        showError('weightErr', false);
        showError('armorTypeErr', false);
        showError('weaponErr', false);


        // Простая проверка обязательных полей
        let ok = true;
        if (!form.type.value) { //если пустое
            showError('typeErr', true);
            ok = false;
        }
        if (!form.name.value.trim()) {
            showError('nameErr', true);
            ok = false;
        }

        if (document.getElementById('armor_opt').style.display === "block"
            && !form.armorType.value) {
            showError('armorTypeErr', true);
            ok = false;
        }

        if (document.getElementById('weapon_opt').style.display === "block"
            && (!form.numberOfDice.value
                || !form.qualityOfDice.value
                || (!form.meleeWeaponMastery.value || !form.rangedWeaponMastery.value))) {
            showError('weaponErr', true);
            ok = false;
        }

        // Проверка веса
        const w = form.weight.value.trim();
        if (w) {
            const pattern = /^\d{1,4}(?:\.\d{1,2})?$/;
            if (!pattern.test(w.replace(',', '.'))) {
                showError('weightErr', true);
                ok = false;
            }
        }

        if (!ok) return;

        // Если return не случился, значит ok === true. Давайте пробовать отправить запрос
        try {
            var charId = document.getElementById('char-id').innerText
            var userName = document.getElementById('user-name').innerText
            var charName = document.getElementById('char-name').innerText
            const data = collectData(true);
            var host = window.location.protocol;
            var url = host + '/api/addItem/' + charId;
            const urlChar = host + '/' + userName + '/' + charName + '/charsheet';
            var xhr = new XMLHttpRequest();
            xhr.open("POST", url)
            xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
            const body = JSON.stringify(data);
            console.log(body)
            xhr.send(body)
            xhr.onload = function () {
                location.replace(urlChar)
            }
        } catch (err) {
            if (err.message === 'bad_weight') {
                showError('weightErr', true);
            } else {
                alert('Ошибка: ' + err.message);
            }
        }
    });

    resetBtn.addEventListener('click', () => {
        form.reset();
        showError('typeErr', false);
        showError('nameErr', false);
        showError('weightErr', false);
        showError('armorTypeErr', false);
        showError('weaponErr', false);
    });

</script>
</body>
</html>
