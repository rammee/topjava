package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by RAMSES on 14.03.2016.
 */
@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Long, User> repository = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    public InMemoryUserRepository() {
        add(new User("Sam User", "sam@gmail.com", "1234", true, LocalDateTime.now(), 2000, Role.USER));
        add(new User("John Admin", "john@gmail.com", "1234", true, LocalDateTime.now(), 1500, Role.ADMIN));
    }

    @Override
    public Collection<User> getAll() {
        return repository.values().stream().sorted(NamedEntity.COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public User get(long id) {
        log.info("Get getId={}", id);
        return repository.get(id);
    }

    @Override
    public User add(User user) {
        log.info("Add {}", user);
        checkArgument(user.isNew());
        Long id = counter.incrementAndGet();
        user.setId(id);
        repository.put(id, user);
        return user;
    }

    @Override
    public void update(User user) {
        log.info("Update {}", user);
        checkArgument(!user.isNew());
        repository.put(user.getId(), user);
    }

    @Override
    public void remove(long id) {
        log.info("Remove getId={}", id);
        repository.remove(id);
    }

    @Override
    public User getByEmail(String email) {
        return getAll().stream().filter(u -> email.equals(u.getEmail())).findFirst().orElse(null);
    }
}
