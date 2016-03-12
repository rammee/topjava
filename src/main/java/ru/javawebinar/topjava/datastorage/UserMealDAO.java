package ru.javawebinar.topjava.datastorage;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by RAMSES on 11.03.2016.
 */
public interface UserMealDAO {
    List<UserMeal> getAll();
    UserMeal getById(long id);
    void save(UserMeal userMeal);
    void delete(Long id);
}
