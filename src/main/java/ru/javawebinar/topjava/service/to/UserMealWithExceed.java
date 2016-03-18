package ru.javawebinar.topjava.service.to;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {

    private UserMeal userMeal;

    private final boolean exceeded;

    public UserMealWithExceed(UserMeal userMeal, boolean exceeded) {
        this.userMeal = userMeal;
        this.exceeded = exceeded;
    }

    public LocalDateTime getDateTime() {
        return userMeal.getDateTime();
    }

    public Long getId() {
        return userMeal.getId();
    }

    public String getDescription() {
        return userMeal.getDescription();
    }

    public int getCalories() {
        return userMeal.getCalories();
    }

    public boolean isExceeded() {
        return exceeded;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "getId=" + userMeal.getId() +
                "dateTime=" + userMeal.getDateTime() +
                ", description='" + userMeal.getDescription() + '\'' +
                ", calories=" + userMeal.getCalories() +
                ", exceeded=" + exceeded +
                '}';
    }
}
