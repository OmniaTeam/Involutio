<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Отчёт</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700" rel="stylesheet"/>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Roboto', sans-serif;
        }

        h1, h2, h3, h4, h5, h6, p {
            margin-block: 0;
            padding-block: 0;
        }

        .main {
            height: 100vh;
            overflow: hidden;
        }

        .hero {
            height: 100%;
        }

        .hero--container {
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            flex-direction: column;
            max-width: 1200px;
            margin: 0 auto
        }

        .title {
            font-size: 64px;
            font-weight: 700;
            letter-spacing: 5px;
        }

        .information {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .information--path {
            font-size: 32px;
            font-weight: 700;
            letter-spacing: 2px;
        }

        span {
            font-weight: 400;
            letter-spacing: 1px;
        }

        .line {
            width: 100%;
            height: 1px;
            background-color: rgba(0, 0, 0, .5);
            margin: 30px 0
        }
    </style>
</head>
<body>
<main class="main">
    <section class="hero">
        <div class="hero--container">
            <h1 class="title">Иванов Иван Иванович</h1>
            <div class="line"></div>
            <div class="information">
                <h2 class="information--path">Отдел: <span>${departmentName}</span></h2>
                <h2 class="information--path">Почта: <span>${email}</span></h2>
                <h2 class="information--path">Начальник: <span>${managerName}</span></h2>
                <h2 class="information--path">Куратор: <span>${curatorName}</span></h2>
            </div>
            <div class="line"></div>
            <div class="information">
                <h2 class="information--path">Средняя вероятнось увольнения за последие 7 дней: <span>${avg}</span></h2>
                <h2 class="information--path">Текущая вероятнось увольнения: <span>${actual}</span></h2>
            </div>
        </div>
    </section>
</main>
</body>
</html>