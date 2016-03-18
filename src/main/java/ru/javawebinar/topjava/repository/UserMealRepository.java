package ru.javawebinar.topjava.repository;

import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by RAMSES on 11.03.2016.
 */
public interface UserMealRepository {
    Collection<UserMeal> getAll(Long userId);
    UserMeal get(long userId, long mealId);
    UserMeal add(UserMeal userMeal);
    boolean update(UserMeal userMeal);
    boolean remove(long userId, long mealId);
    Collection<UserMeal> getBetween(LocalDate fromDate, LocalDate toDate);
}
