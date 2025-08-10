<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Создание предмета</title>
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
    <!-- Форма -->
    <form id="itemForm" class="card grid" novalidate>
        <div>
            <label for="type">Тип предмета</label>
            <select id="type" name="type" required>
                <option value="" disabled selected>Выберите тип…</option>
                <option value="RING">Кольцо</option>
                <option value="AMULET">Амулет</option>
                <option value="RANGED_WEAPON">Стрелковое оружие</option>
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
                <option value="LIGHT_ARMOR">Легкий доспех</option>
                <option value="MEDIUM_ARMOR">Средний доспех</option>
                <option value="HEAVY_ARMOR">Тяжелый доспех</option>
            </select>
            <div class="error" id="armorTypeErr">Укажите тип доспеха.</div>
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
                document.getElementById('armor_opt').style.display = "block";
            } else {
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
        const armorType = form.armorType.value || null;
        const description = form.description.value.trim() || null;
        let weightRaw = (form.weight.value || '').replace(',', '.').trim();

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

        return {type, name, armorType, description, weight};
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

        // Простая проверка обязательных полей
        let ok = true;
        console.log(form.type.value)
        if (!form.type.value) {
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

        try {
            const data = collectData(true);
            console.log(data)
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
    });

</script>
</body>
</html>
