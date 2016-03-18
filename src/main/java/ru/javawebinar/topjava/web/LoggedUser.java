package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Set;

/**
 * Created by RAMSES on 12.03.2016.
 */
public class LoggedUser {
    private static long id = 1;

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        LoggedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
