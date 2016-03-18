package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by RAMSES on 12.03.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void update(User user) throws NotFoundException {

    }

    @Override
    public void delete(long id) throws NotFoundException {

    }

    @Override
    public User get(long id) throws NotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

}
