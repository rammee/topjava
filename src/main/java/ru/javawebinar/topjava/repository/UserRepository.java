package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;
import java.util.List;

/**
 * Created by RAMSES on 11.03.2016.
 */
public interface UserRepository {
    Collection<User> getAll();
    User get(long id);
    User add(User user);
    void update(User user);
    void remove(long id);
    public User getByEmail(String email);
}
