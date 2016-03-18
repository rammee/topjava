package ru.javawebinar.topjava.web;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    public static final String MEAL_LIST_ATTR = "mealList";
    public static final String EDIT_MEAL_JSP = "editMeal.jsp";
    private static final Logger log = getLogger(MealServlet.class);
    private UserMealRestController userMealController;

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userMealController = springContext.getBean(UserMealRestController.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userIdString = request.getParameter("userId");
        if (userIdString != null) {
            LoggedUser.setId(Long.parseLong(userIdString));
        }
        if (action == null || action.equalsIgnoreCase("filter")) {
            String fromDateString = request.getParameter("fromDate");
            String toDateString = request.getParameter("toDate");
            List<UserMealWithExceed> userMeals = userMealController.listLoggedUserMeals(fromDateString, toDateString);
            request.setAttribute(MEAL_LIST_ATTR, userMeals);
            request.setAttribute("fromDate", fromDateString);
            request.setAttribute("toDate", toDateString);
            request.getRequestDispatcher("mealList.jsp").forward(request,response);
        } else if (action.equalsIgnoreCase("remove")) {
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            userMealController.remove(mealId);
            response.sendRedirect("meals");
        } else if (action.equalsIgnoreCase("edit")) {
            Long mealId = Long.parseLong(request.getParameter("mealId"));
            UserMeal userMeal = userMealController.get(mealId);
            request.setAttribute("userMeal", userMeal);
            request.getRequestDispatcher(EDIT_MEAL_JSP).forward(request,response);
        } else if (action.equalsIgnoreCase("insert")) {
            request.getRequestDispatcher(EDIT_MEAL_JSP).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        String idParam = req.getParameter("mealId");
        String mealDateString = req.getParameter("mealDate");
        String mealDescription = req.getParameter("mealDescription");
        String mealCaloriesString = req.getParameter("mealCalories");
        String selectedAction = req.getParameter("selectedAction");
        String userIdString = req.getParameter("userId");
        Preconditions.checkState(!Strings.isNullOrEmpty(mealDateString), "Meal date is required");
        Preconditions.checkState(!Strings.isNullOrEmpty(mealDescription), "Meal description is required");
        Preconditions.checkState(!Strings.isNullOrEmpty(mealCaloriesString), "Meal calories is required");
        Preconditions.checkState(!Strings.isNullOrEmpty(selectedAction), "Action is required");
        Preconditions.checkState(!Strings.isNullOrEmpty(userIdString), "User Id is required");

        Long mealId = null;
        if (!Strings.isNullOrEmpty(idParam)) {
            Preconditions.checkState(!selectedAction.equals("insert"));
            mealId = Long.parseLong(idParam);
        }

        Long userId = Long.parseLong(userIdString);

        LocalDateTime mealDateTime = LocalDateTime.parse(mealDateString);
        Integer mealCalories = Integer.parseInt(mealCaloriesString);


        if (selectedAction.equalsIgnoreCase("insert")) {
            UserMeal userMeal = new UserMeal(userId, mealDateTime, mealDescription, mealCalories);
            userMealController.create(userMeal);
        } else if (selectedAction.equalsIgnoreCase("edit")) {
            UserMeal userMeal = new UserMeal(mealId, mealDateTime, mealDescription, mealCalories);
            userMealController.update(userMeal);
        } else if (selectedAction.equalsIgnoreCase("filter")) {
            // do nothing
        } else {
            throw new RuntimeException("Unknown action: " + selectedAction);
        }

        doGet(req, resp);
    }
}
