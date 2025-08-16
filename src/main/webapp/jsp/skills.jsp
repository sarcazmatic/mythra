<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Mythra D&D Service</title>
    <link href="https://fonts.googleapis.com/css2?family=Philosopher&display=swap" rel="stylesheet">
    <style>
        :root {
            --bg: #0d0d0d;
            --panel: #222;
            --line: #444;
            --muted: #bfbfbf;
            --text: #fff;
            --pill: #666;
            --accent: #7aa7ff;
        }

        * {
            box-sizing: border-box
        }

        body {
            margin: 0;
            padding: 10px;
            background: var(--bg);
            color: var(--text);
            font-family: 'Philosopher', cursive;
            display: flex;
            justify-content: center;
        }

        .page {
            width: 100%;
            max-width: 1100px;
            display: grid;
            grid-template-columns:320px 1fr;
            gap: 12px
        }

        @media (max-width: 900px) {
            .page {
                grid-template-columns:1fr
            }
        }

        .card {
            background: var(--panel);
            border: 1px solid var(--line);
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, .1);
        }

        .pad {
            padding: 12px
        }

        h1 {
            font-size: 20px;
            text-align: center;
            margin: 4px 0 10px
        }

        h2 {
            font-size: 16px;
            text-align: center;
            margin: 6px 0 8px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, .8)
        }

        h3 {
            font-size: 14px;
            text-align: left;
            margin: 8px 0
        }

        h5, h6 {
            margin: 6px 0;
            text-align: left
        }

        /* Левая колонка — короткая и липкая на десктопе */
        .summary-box {
            display: block
        }

        @media (min-width: 901px) {
            .summary-box {
                position: sticky;
                top: 10px;
                align-self: start
            }
        }

        .summary-box .kv {
            display: grid;
            grid-template-columns:auto 1fr;
            gap: 4px 8px;
            font-size: 14px
        }

        .summary-box .attrs {
            display: grid;
            grid-template-columns:repeat(2, minmax(0, 1fr));
            gap: 4px 8px;
            margin-top: 6px
        }

        .pill {
            display: inline-block;
            padding: .15rem .5rem;
            background: var(--pill);
            border-radius: 999px;
            font-size: 12px
        }

        /* Правая колонка */
        .content {
            display: grid;
            gap: 10px
        }

        /* HP блок */
        .hp-wrap {
            display: flex;
            justify-content: center
        }

        .hp {
            min-width: 120px;
            text-align: center;
            border: 1px solid var(--line);
            padding: 6px 10px;
            background: #1a1a1a;
            border-radius: 8px
        }

        .hp output {
            font-size: 22px
        }

        /* Accordion */
        details {
            border: 1px solid var(--line);
            border-radius: 8px;
            overflow: hidden;
            background: #1b1b1b
        }

        details + details {
            margin-top: 8px
        }

        summary {
            cursor: pointer;
            list-style: none;
            padding: 10px 12px;
            font-size: 14px;
            background: #1f1f1f;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 10px;
        }

        summary::-webkit-details-marker {
            display: none
        }

        .count {
            font-size: 12px;
            color: var(--muted)
        }

        .inside {
            padding: 10px 10px 12px
        }

        /* Сетки форм */
        .grid-2 {
            display: grid;
            grid-template-columns:repeat(2, 1fr);
            gap: 8px
        }

        .grid-3 {
            display: grid;
            grid-template-columns:repeat(3, 1fr);
            gap: 8px
        }

        @media (max-width: 700px) {
            .grid-2, .grid-3 {
                grid-template-columns:1fr
            }
        }

        .field {
            background: #202020;
            border: 1px solid var(--line);
            border-radius: 8px;
            padding: 8px
        }

        .field label {
            font-size: 13px;
            display: flex;
            align-items: center;
            gap: 8px;
            justify-content: space-between
        }

        .field input[type="text"] {
            width: 68px;
            text-align: center;
            background: #2c2c2c;
            color: #fff;
            border: 1px solid #333;
            border-radius: 6px;
            padding: 4px
        }

        .field input[type="checkbox"] {
            transform: scale(1.1)
        }

        .field.readonly input {
            pointer-events: none;
            opacity: .9
        }

        /* Кнопка */
        .submit-button {
            width: 100%;
            max-width: 420px;
            margin: 10px auto 0;
            display: block;
            font-size: 15px;
            padding: .7em 1em;
            background: #666;
            border: none;
            border-radius: 8px;
            color: #fff;
            cursor: pointer;
            transition: background-color .2s
        }

        .submit-button:hover {
            background: #4a4a4a
        }

        /* Быстрые действия */
        .actions {
            display: flex;
            flex-wrap: wrap;
            gap: 6px;
            margin-bottom: 8px
        }

        .btn-mini {
            border: 1px solid var(--line);
            background: #202020;
            color: #fff;
            padding: 6px 10px;
            border-radius: 6px;
            font-size: 12px;
            cursor: pointer
        }

        .search {
            flex: 1 1 180px;
            min-width: 160px;
            border: 1px solid var(--line);
            background: #181818;
            color: #fff;
            border-radius: 6px;
            padding: 6px 8px
        }

        .muted {
            color: var(--muted);
            font-size: 12px
        }
    </style>
</head>
<body>
<div class="page">
    <!-- Левая колонка -->
    <aside class="card pad summary-box">
        <h2>Твой персонаж</h2>
        <div class="kv">
            <section>
                <div class="muted">Имя:</div>
                <div><span class="pill">${charName}</span></div>
                <div class="muted">Раса:</div>
                <div><span class="pill">${charRace}</span></div>
                <div class="muted">Класс:</div>
                <div><span class="pill">${charClass}</span></div>
            </section>
            <!-- HP -->
            <section class="hp-wrap">
                <div class="hp">
                    <div class="muted">Твои хит-поинты</div>
                    <br>
                    <output id="hitPoints">${hitPoints}</output>
                </div>
            </section>
        </div>
        <h3>Атрибуты</h3>
        <div class="attrs">
            <div>Сила: <b>${strength}</b></div>
            <div>Ловк.: <b>${dexterity}</b></div>
            <div>Тело: <b>${constitution}</b></div>
            <div>Инт.: <b>${intelligence}</b></div>
            <div>Мудр.: <b>${wisdom}</b></div>
            <div>Хар.: <b>${charisma}</b></div>
        </div>
    </aside>

    <!-- Правая колонка -->
    <main class="content">
        <form action="charsheet" method="post" class="card pad">
            <h1>Mythra D&D Service</h1>

            <!-- Навыки (accordion) -->
            <details open>
                <summary>
                    Выбор навыков
                    <span class="count muted">значения по модификаторам</span>
                </summary>
                <div class="inside">
                    <div class="grid-3">
                        <div class="field readonly">
                            <label for="acrobatics">Акробатика
                                <input name="acrobatics" value="${dexterityMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="acrobatics" name="profs" value="acrobatics"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="athletics">Атлетика
                                <input name="athletics" value="${strengthMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="athletics" name="profs" value="athletics"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="arcana">Магия
                                <input name="arcana" value="${intelligenceMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="arcana" name="profs" value="arcana"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="deception">Обман
                                <input name="deception" value="${charismaMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="deception" name="profs" value="deception"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="history">История
                                <input name="history" value="${intelligenceMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="history" name="profs" value="history"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="insight">Проницательн.
                                <input name="insight" value="${wisdomMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="insight" name="profs" value="insight"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="intimidation">Запугивание
                                <input name="intimidation" value="${charismaMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="intimidation" name="profs" value="intimidation">
                                Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="investigation">Расследование
                                <input name="investigation" value="${intelligenceMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="investigation" name="profs" value="investigation">
                                Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="medicine">Медицина
                                <input name="medicine" value="${wisdomMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="medicine" name="profs" value="medicine"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="nature">Природа
                                <input name="nature" value="${intelligenceMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="nature" name="profs" value="nature"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="perception">Восприятие
                                <input name="perception" value="${wisdomMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="perception" name="profs" value="perception"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="performance">Выступление
                                <input name="performance" value="${charismaMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="performance" name="profs" value="performance">
                                Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="persuasion">Убеждение
                                <input name="persuasion" value="${charismaMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="persuasion" name="profs" value="persuasion"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="religion">Религия
                                <input name="religion" value="${intelligenceMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="religion" name="profs" value="religion"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="sleight-of-hand">Ловкость рук
                                <input name="sleight-of-hand" value="${dexterityMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="sleight-of-hand" name="profs" value="sleight-of-hand">
                                Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="stealth">Скрытность
                                <input name="stealth" value="${dexterityMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="stealth" name="profs" value="stealth"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="survival">Выживание
                                <input name="survival" value="${wisdomMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="survival" name="profs" value="survival"> Проф.</label>
                        </div>
                        <div class="field readonly">
                            <label for="animal-handling">Обращение с животными
                                <input name="animal-handling" value="${wisdomMod}" readonly>
                            </label>
                            <label><input type="checkbox" id="animal-handling" name="profs" value="animal-handling">
                                Проф.</label>
                        </div>
                    </div>
                </div>
            </details>

            <!-- Владения (accordion, компактно + поиск + быстрые кнопки) -->
            <details>
                <summary>
                    Владения (доспехи и оружие)
                    <span class="count muted">отметь, чем владеешь</span>
                </summary>
                <div class="inside">
                    <div class="actions">
                        <input class="search" type="search" id="searchProf" placeholder="Фильтр по названию…">
                        <button type="button" class="btn-mini" data-group="simple-all">Простое — всё</button>
                        <button type="button" class="btn-mini" data-group="martial-all">Боевое — всё</button>
                        <button type="button" class="btn-mini" data-group="armor-all">Доспехи+Щиты</button>
                        <button type="button" class="btn-mini" id="clearAll">Снять всё</button>
                    </div>

                    <h3>Доспехи</h3>
                    <div class="grid-3 prof-group" data-group="armor-all">
                        <label class="field"><input type="checkbox" name="masteries" value="LIGHT_ARMOR"> Лёгкие
                            доспехи</label>
                        <label class="field"><input type="checkbox" name="masteries" value="MEDIUM_ARMOR"> Средние
                            доспехи</label>
                        <label class="field"><input type="checkbox" name="masteries" value="HEAVY_ARMOR"> Тяжёлые
                            доспехи</label>
                        <label class="field"><input type="checkbox" name="masteries" value="SHIELD"> Щиты</label>
                    </div>

                    <h3>Простое ближнее</h3>
                    <div class="grid-3 prof-group" data-group="simple-all">
                        <label class="field"><input type="checkbox" name="masteries" value="CLUB"> Дубинка</label>
                        <label class="field"><input type="checkbox" name="masteries" value="DAGGER"> Кинжал</label>
                        <label class="field"><input type="checkbox" name="masteries" value="GREATCLUB"> Тяжёлая
                            дубина</label>
                        <label class="field"><input type="checkbox" name="masteries" value="HANDAXE"> Топор</label>
                        <label class="field"><input type="checkbox" name="masteries" value="JAVELIN"> Метательное
                            копьё</label>
                        <label class="field"><input type="checkbox" name="masteries" value="LIGHT_HAMMER"> Лёгкий
                            молот</label>
                        <label class="field"><input type="checkbox" name="masteries" value="MACE"> Булава</label>
                        <label class="field"><input type="checkbox" name="masteries" value="QUARTERSTAFF">
                            Посох</label>
                        <label class="field"><input type="checkbox" name="masteries" value="SICKLE"> Серп</label>
                    </div>

                    <h3>Простое дальнобойное</h3>
                    <div class="grid-3 prof-group" data-group="simple-all">
                        <label class="field"><input type="checkbox" name="masteries" value="LIGHT_CROSSBOW"> Лёгкий
                            арбалет</label>
                        <label class="field"><input type="checkbox" name="masteries" value="DART"> Дротик</label>
                        <label class="field"><input type="checkbox" name="masteries" value="SLING"> Праща</label>
                    </div>

                    <h3>Боевое ближнее</h3>
                    <div class="grid-3 prof-group" data-group="martial-all">
                        <label class="field"><input type="checkbox" name="masteries" value="BATTLEAXE"> Боевой
                            топор</label>
                        <label class="field"><input type="checkbox" name="masteries" value="FLAIL"> Цеп</label>
                        <label class="field"><input type="checkbox" name="masteries" value="GREATAXE"> Грандиозный
                            топор</label>
                        <label class="field"><input type="checkbox" name="masteries" value="GREATSWORD"> Двуручный
                            меч</label>
                        <label class="field"><input type="checkbox" name="masteries" value="HALBERD">
                            Алебарда</label>
                        <label class="field"><input type="checkbox" name="masteries" value="LANCE"> Копьё
                            (лансерская пика)</label>
                        <label class="field"><input type="checkbox" name="masteries" value="LONGSWORD"> Длинный
                            меч</label>
                        <label class="field"><input type="checkbox" name="masteries" value="MAUL"> Кувалда</label>
                        <label class="field"><input type="checkbox" name="masteries" value="MORNINGSTAR"> Утренняя
                            звезда</label>
                        <label class="field"><input type="checkbox" name="masteries" value="PIKE"> Пика</label>
                        <label class="field"><input type="checkbox" name="masteries" value="RAPIER"> Рапира</label>
                        <label class="field"><input type="checkbox" name="masteries" value="SCIMITAR">
                            Скимитар</label>
                        <label class="field"><input type="checkbox" name="masteries" value="SHORTSWORD"> Короткий
                            меч</label>
                        <label class="field"><input type="checkbox" name="masteries" value="TRIDENT">
                            Трезубец</label>
                        <label class="field"><input type="checkbox" name="masteries" value="WAR_PICK"> Боевая
                            кирка</label>
                        <label class="field"><input type="checkbox" name="masteries" value="WARHAMMER"> Боевой
                            молот</label>
                        <label class="field"><input type="checkbox" name="masteries" value="WHIP"> Кнут</label>
                    </div>

                    <h3>Боевое дальнобойное</h3>
                    <div class="grid-3 prof-group" data-group="martial-all">
                        <label class="field"><input type="checkbox" name="masteries" value="HAND_CROSSBOW"> Ручной
                            арбалет</label>
                        <label class="field"><input type="checkbox" name="masteries" value="HEAVY_CROSSBOW"> Тяжёлый
                            арбалет</label>
                        <label class="field"><input type="checkbox" name="masteries" value="LONGBOW"> Длинный
                            лук</label>
                        <label class="field"><input type="checkbox" name="masteries" value="NET"> Сеть</label>
                    </div>
                </div>
            </details>

            <!-- Кнопка и скрытые поля -->
            <button type="submit" class="submit-button">Дальше</button>

            <input type="text" name="charName" value="${charName}" hidden>
            <input type="text" name="charRace" value="${charRace}" hidden>
            <input type="text" name="charClass" value="${charClass}" hidden>

            <input type="text" name="strength" value=${strength} hidden>
            <input type="text" name="dexterity" value=${dexterity} hidden>
            <input type="text" name="constitution" value=${constitution} hidden>
            <input type="text" name="intelligence" value=${intelligence} hidden>
            <input type="text" name="wisdom" value=${wisdom} hidden>
            <input type="text" name="charisma" value=${charisma} hidden>
            <input type="text" name="hitPoints" value=${hitPoints} hidden>
        </form>
    </main>
</div>

<script>
    // Фильтр по владениям
    const search = document.getElementById('searchProf');
    search.addEventListener('input', () => {
        const q = search.value.trim().toLowerCase();
        document.querySelectorAll('.prof-group label.field').forEach(lab => {
            const text = lab.textContent.toLowerCase();
            lab.style.display = text.includes(q) ? '' : 'none';
        });
    });

    // Быстрые кнопки групп
    document.querySelectorAll('.btn-mini[data-group]').forEach(btn => {
        btn.addEventListener('click', () => {
            const group = btn.getAttribute('data-group');
            if (group === "martial-all") {
                document.querySelectorAll('.prof-group[data-group="martial-all"] input[type="checkbox"]')
                    .forEach(ch => ch.checked = true)
            } else if (group === "simple-all") {
                document.querySelectorAll('.prof-group[data-group="simple-all"] input[type="checkbox"]')
                    .forEach(ch => ch.checked = true)
            } else if (group === "armor-all") {
                document.querySelectorAll('.prof-group[data-group="armor-all"] input[type="checkbox"]')
                    .forEach(ch => ch.checked = true)
            }
        });
    });

    // Снять все
    const clearBtn = document.getElementById('clearAll');
    if (clearBtn) {
        clearBtn.addEventListener('click', () => {
            document.querySelectorAll('.prof-group input[type="checkbox"]').forEach(ch => ch.checked = false);
        });
    }
</script>
</body>
</html>
