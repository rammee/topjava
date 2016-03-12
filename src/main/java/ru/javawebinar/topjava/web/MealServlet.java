package ru.javawebinar.topjava.web;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import ru.javawebinar.topjava.datastorage.SimpleMealDAO;
import ru.javawebinar.topjava.datastorage.UserMealDAO;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    public static final String MEAL_LIST = "mealList";
    public static final String MEAL_LIST_JSP = "mealList.jsp";
    public static final String EDIT_MEAL_JSP = "editMeal.jsp";
    private static final Logger LOG = getLogger(MealServlet.class);
    private UserMealDAO userMealDAO = SimpleMealDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = Strings.nullToEmpty(request.getParameter("action"));

        if (action.equalsIgnoreCase("delete")){
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            userMealDAO.delete(mealId);
            forward = MEAL_LIST_JSP;
        } else if (action.equalsIgnoreCase("edit")){
            forward = EDIT_MEAL_JSP;
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            UserMeal userMeal = userMealDAO.getById(mealId);
            request.setAttribute("userMeal", userMeal);
        } else if (action.equalsIgnoreCase("insert")){
            forward = EDIT_MEAL_JSP;
        } else {
            forward = MEAL_LIST_JSP;
        }

        List<UserMeal> userMeals = userMealDAO.getAll();
        List<UserMealWithExceed> userMealsWithExceed = UserMealsUtil.getFilteredMealsWithExceeded(userMeals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        request.setAttribute(MEAL_LIST, userMealsWithExceed);

        LOG.debug("Dispatching to " + forward);
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long mealId = null;

        String idParam = req.getParameter("mealId");
        String mealDateString = req.getParameter("mealDate");
        String mealDescription = req.getParameter("mealDescription");
        String mealCaloriesString = req.getParameter("mealCalories");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mealDateString), "Meal date is required");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mealDescription), "Meal description is required");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(mealCaloriesString), "Meal calories is required");

        if (!Strings.isNullOrEmpty(idParam)) {
            mealId = Long.parseLong(idParam);
        }

        LocalDateTime mealDateTime = LocalDateTime.parse(mealDateString, TimeUtil.DATE_TIME_FORMATTER);
        Integer mealCalories = Integer.parseInt(mealCaloriesString);


        UserMeal userMeal = new UserMeal(mealId, mealDateTime, mealDescription, mealCalories);

        userMealDAO.save(userMeal);

        doGet(req, resp);
    }
}
