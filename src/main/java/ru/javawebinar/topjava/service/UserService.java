package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by RAMSES on 12.03.2016.
 */
@Service
public interface UserService  {

    User getByEmail(String email) throws NotFoundException;

    public User save(User entity);

    public void update(User entity) throws NotFoundException;

    public void delete(long id) throws NotFoundException;

    public User get(long id) throws NotFoundException;

    public List<User> getAll();

}
