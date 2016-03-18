package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by RAMSES on 12.03.2016.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserMeal save(UserMeal userMeal) {
        return mealRepository.add(userMeal);
    }

    @Override
    public void update(long userId, UserMeal userMeal) throws NotFoundException {
        if (userMeal.getUserId() != userId || !mealRepository.update(userMeal)) {
            throw new NotFoundException("UserMeal not found");
        }
    }

    @Override
    public void remove(long userId, long mealId) throws NotFoundException {
        if (!mealRepository.remove(userId, mealId)) {
            throw new NotFoundException("UserMeal not found");
        }
    }

    @Override
    public UserMeal get(long userId, long mealId) throws NotFoundException {
        UserMeal userMeal = mealRepository.get(userId, mealId);
        if (userMeal == null) {
            throw new NotFoundException("UserMeal not found");
        }
        return userMeal;
    }

    @Override
    public List<UserMealWithExceed> getAll(long userId, LocalDateTime fromDate, LocalDateTime toDate) {
        List<UserMeal> userMeals = mealRepository.getAll(userId);
        User user = userRepository.get(userId);
        return UserMealsUtil.getFilteredWithExceeded(userMeals, fromDate, toDate, user.getCaloriesPerDay());
    }
}
