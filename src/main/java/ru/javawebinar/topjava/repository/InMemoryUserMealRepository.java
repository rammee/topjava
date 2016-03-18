package ru.javawebinar.topjava.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by RAMSES on 11.03.2016.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserMealRepository.class);

    private Map<Long, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    protected InMemoryUserMealRepository() {
        add(new UserMeal(1l, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new UserMeal(2l, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new UserMeal(1l, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new UserMeal(2l, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        add(new UserMeal(1l, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
    }

    @Override
    public List<UserMeal> getAll(Long userId) {
        log.info("Getting all meals for userId={}", userId);
        List<UserMeal> userMeals =
                repository.values().stream().filter(
                        um -> userId == null || um.getUserId() == userId).collect(Collectors.toList());
        Collections.sort(userMeals, UserMeal.BY_DATE_DESC_COMPARATOR);
        return userMeals;
    }

    @Override
    public UserMeal get(long userId, long id) {
        log.info("Getting userMeal userId={}, mealId={}", userId, id);
        UserMeal userMeal = repository.get(id);
        if (userMeal != null && userMeal.getUserId() == userId) {
            return userMeal;
        } else {
            return null;
        }
    }

    @Override
    public UserMeal add(UserMeal userMeal) {
        checkArgument(userMeal.isNew());
        userMeal.setId(idCounter.incrementAndGet());
        log.info("Saving {}", userMeal);
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean update(UserMeal userMeal) {
        checkArgument(userMeal.getId() != null, "Update only supports existing UserMeal with valid ID");
        checkArgument(repository.containsKey(userMeal.getId()), "UserMeal with getId=%s not found", userMeal.getId());
        log.info("Updating {}", userMeal);
        return repository.put(userMeal.getId(), userMeal) != null;
    }

    @Override
    public boolean remove(long userId, long mealId) {
        log.info("Deleting userMeal userId= {}, mealId={}", userId, mealId);
        UserMeal mealToRemove = get(userId, mealId);
        if (mealToRemove != null) {
            return repository.remove(mealToRemove.getId()) != null;
        } else {
            return false;
        }
    }

    @Override
    public Collection<UserMeal> getBetween(LocalDate fromDate, LocalDate toDate) {
        return repository.values().stream().filter(
                um -> TimeUtil.isBetween(um.getDateTime().toLocalDate(), fromDate, toDate)).collect(Collectors.toList());
    }
}
