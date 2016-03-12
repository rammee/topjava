<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="ru.javawebinar.topjava.web.MealServlet,ru.javawebinar.topjava.util.TimeUtil" %>
<html>
<head>
    <title>Meal list</title>
    <style type="text/css">
        .meal-table th, .meal-table td {
            border: solid #000000 thin;
            padding: 5px;
        }

        .meal-table {
            border: solid #000000 thin;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h2>Meal list</h2>
<a href="meals?action=insert">Add</a>
<table class="meal-table">
    <thead>
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope[MealServlet.MEAL_LIST]}" var="meal">
        <tr style="color:${meal.exceed ? 'red' : 'green'}">
            <td>${meal.dateTime.format(TimeUtil.DATE_TIME_FORMATTER)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&mealId=${meal.id}">Edit</a>&nbsp
                <a href="meals?action=delete&mealId=${meal.id}">Delete</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
