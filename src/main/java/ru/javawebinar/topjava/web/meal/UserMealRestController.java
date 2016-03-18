package ru.javawebinar.topjava.web.meal;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.LoggedUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by RAMSES on 12.03.2016.
 */
@Controller
public class UserMealRestController {

    @Autowired
    UserMealService service;

    public List<UserMealWithExceed> listLoggedUserMeals(String fromDateTimeString, String toDateTimeString) {
        LocalDateTime fromDateTime = Strings.isNullOrEmpty(fromDateTimeString) ? LocalDateTime.MIN : LocalDateTime.parse(fromDateTimeString);
        LocalDateTime toDateTime = Strings.isNullOrEmpty(toDateTimeString) ? LocalDateTime.MAX : LocalDateTime.parse(toDateTimeString);
        return service.getAll(LoggedUser.getId(), fromDateTime, toDateTime);
    }

    public List<UserMealWithExceed> listLoggedUserMeals() {
        return listLoggedUserMeals(null, null);
    }

    public void update(UserMeal userMeal) {
        service.update(LoggedUser.getId(), userMeal);
    }

    public void create(UserMeal userMeal) {
        Preconditions.checkArgument(userMeal.getUserId() == LoggedUser.getId());
        service.save(userMeal);
    }

    public void remove(long mealId) throws NotFoundException {
        service.remove(LoggedUser.getId(), mealId);
    }

    public UserMeal get(long mealId) throws NotFoundException {
        return service.get(LoggedUser.getId(), mealId);
    }
}
