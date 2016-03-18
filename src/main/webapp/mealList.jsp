<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="ru.javawebinar.topjava.util.TimeUtil" %>
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

        .exceeded {
            color: red;
        }

        .not-exceeded {
            color: green;
        }
    </style>
</head>
<body>
<h2>Meal list</h2>
<a href="meals?action=insert">Add</a>
<form name="filter_form" action="meals">
    <p>From: <input type="date" name="fromDate" value="${fromDate}"/></p>
    <p>To: <input type="date" name="toDate" value="${toDate}"/></p>
    <p>User id: <select name="userId">
        <option value="1">1</option>
        <option value="2">2</option>
    </select>
    </p>
    <input type="hidden" name="action" value="filter">
    <button type="submit">Filter</button>
</form>
<table class="meal-table">
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <c:forEach items="${requestScope['mealList']}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.service.to.UserMealWithExceed"/>
        <tr class="${meal.exceeded ? 'exceeded' : 'not-exceeded'}">
            <td>${meal.dateTime.format(TimeUtil.DATE_TIME_FORMATTER)}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&mealId=${meal.id}">Edit</a>&nbsp
                <a href="meals?action=remove&mealId=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
