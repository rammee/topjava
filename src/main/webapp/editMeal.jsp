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
    <jsp:useBean id="userMeal" scope="request" class="ru.javawebinar.topjava.model.UserMeal" />
    <table>
        <tr>
            <td>Date:</td>
            <td><input type="datetime-local" name="mealDate" value="${userMeal.dateTime}"/></td>
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
    <input type="hidden" name="mealId" value="${userMeal.id}">
    <input type="hidden" name="selectedAction" value="<%=request.getParameter("action")%>">
    <input type="submit" value="Save"/>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
