<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="ru.javawebinar.topjava.web.MealServlet,ru.javawebinar.topjava.util.TimeUtil" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<h2>Edit meal</h2>
<form id="edit_meal_form" method="post" action="meals">
    <table>
        <tr>
            <td>Date:</td>
            <td><input name="mealDate" value="${userMeal.dateTime.format(TimeUtil.DATE_TIME_FORMATTER)}"/></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input name="mealDescription" value="${userMeal.description}"/></td>
        </tr>
        <tr>
            <td>Calories:</td>
            <td><input name="mealCalories" value="${userMeal.calories}"/></td>
        </tr>
    </table>
    <input style="display: none;" name="mealId" value="${userMeal.id}">
    <input type="submit" value="Save"/>
</form>
</body>
</html>
