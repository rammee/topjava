package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by RAMSES on 12.03.2016.
 */

public interface UserMealService {

    public UserMeal save(UserMeal entity);

    public void update(long userId, UserMeal entity) throws NotFoundException;

    public void remove(long userId, long id) throws NotFoundException;

    public UserMeal get(long userId, long id) throws NotFoundException;

    public List<UserMealWithExceed> getAll(long userId, LocalDateTime fromDate, LocalDateTime toDate);

}
