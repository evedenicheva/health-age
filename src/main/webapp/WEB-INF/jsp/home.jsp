<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>health age</title>
</head>
<body class="container-fluid wrapper">

<form method="POST" action="${pageContext.request.contextPath}/home">

    <div class="form-group row">
        <h4><label for="weight" class="col-sm-2 col-form-label">Возраст</label></h4>
        <div class="col-sm-4">
            <input type="number" required name= "age" class="form-control" id="age" min="10" max="90">
        </div>
    </div>

    <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sex" id="female" value="female" required>
                <label class="form-check-label" for="female">
                    Жен
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="sex" id="male" value="male" required>
                <label class="form-check-label" for="male">
                    Муж
                </label>
            </div>
    </div>

    <div class="form-group row">
         <h4><label for="weight" class="col-sm-2 col-form-label">Вес</label></h4>
         <div class="col-sm-4">
             <input type="number" required name= "weight" class="form-control" id="weight" aria-describedby="weightHelpInline">
             <small id="weightHelpInline" class="text-muted">
                   кг
             </small>
         </div>
    </div>

    <div class="form-group row">
         <h4><label for="height" class="col-sm-2 col-form-label">Рост</label></h4>
         <div class="col-sm-4">
             <input type="number" required name= "height" class="form-control" id="height" aria-describedby="heightHelpInline" step="0.01">
             <small id="heightHelpInline" class="text-muted">
                   см 
             </small>
         </div>
    </div>

    <div class="form-group row">
         <h4><label for="psg" class="col-sm-2 col-form-label">ПСЖ</label></h4>
         <div class="col-sm-4">
             <input type="number" required="" name= "psg" class="form-control" id="psg" aria-describedby="psgHelpInline" required>
             <small id="psgHelpInline" class="text-muted">
                   %
             </small>
         </div>
    </div>

    <div class="form-group row">
         <h4><label for="sleepingHours" class="col-sm-2 col-form-label">Сон</label></h4>
         <div class="col-sm-4">
             <input type="number" required="" name= "sleepingHours" class="form-control" id="sleepingHours" aria-describedby="sleepingHoursHelpInline" required>
             <small id="sleepingHoursHelpInline" class="text-muted">
                   часов
             </small>
         </div>
    </div>

        <h4 class="top-name center-block">Физ. упражнения</h4>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="physicalExercisesStatus" id="everyday" value="everyday" required>
                <label class="form-check-label" for="everyday">
                    Каждый день
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="physicalExercisesStatus" id="few_times_per_week" value="few_times_per_week">
                <label class="form-check-label" for="few_times_per_week">
                    3 раза в неделю
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="physicalExercisesStatus" id="rarely" value="rarely">
                <label class="form-check-label" for="rarely">
                    Редко
                </label>
            </div>
        </div>

        <h4 class="top-name center-block">Курение</h4>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="smokingStatus" id="never" value="never" required>
                <label class="form-check-label" for="never">
                    Никогда
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="smokingStatus" id="give_up" value="give_up" required>
                <label class="form-check-label" for="give_up">
                    Бросил(а)
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="smokingStatus" id="less_than_box" value="less_than_box" required>
                <label class="form-check-label" for="less_than_one">
                    Меньше 1 пачки в день
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="smokingStatus" id="more_than_box" value="more_than_box" required>
                <label class="form-check-label" for="more_than_one">
                    Больше 1 пачки в день
                </label>
            </div>
        </div>

        <h4 class="top-name center-block">Алкоголь</h4>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="alcoholStatus" id="often" value="often" required>
                <label class="form-check-label" for="often">
                    Часто
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="alcoholStatus" id="rarely" value="rarely" required>
                <label class="form-check-label" for="rarely">
                    Редко
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="alcoholStatus" id="do_not" value="do_not" required>
                <label class="form-check-label" for="do_not">
                    Не упротребляю
                </label>
            </div>
        </div>

        <h4 class="top-name center-block">Завтрак</h4>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="breakfastStatus" id="everyday" value="everyday" required>
                <label class="form-check-label" for="everyday">
                    Каждый день
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="breakfastStatus" id="sometimes" value="sometimes" required>
                <label class="form-check-label" for="sometimes">
                    Иногда
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="breakfastStatus" id="rarely" value="rarely" required>
                <label class="form-check-label" for="rarely">
                    Редко/никогда
                </label>
            </div>
        </div>

        <h4 class="top-name center-block">Перекусы</h4>
        <div class="form-group">
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="biteStatus" id="always" value="always" required>
                <label class="form-check-label" for="always">
                    Постоянно
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="biteStatus" id="sometimes" value="sometimes" required>
                <label class="form-check-label" for="sometimes">
                    Иногда
                </label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="biteStatus" id="rarely" value="rarely" required>
                <label class="form-check-label" for="rarely">
                    Редко/никогда
                </label>
            </div>
        </div>

    <div class="form-group">
        <button type="submit" class="btn btn-dark">Рассчитать</button>
    <div>


</form>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>