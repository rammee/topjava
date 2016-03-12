package ru.javawebinar.topjava.datastorage;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by RAMSES on 11.03.2016.
 */
public class SimpleMealDAO implements UserMealDAO {

    private static final Logger log = LoggerFactory.getLogger(SimpleMealDAO.class);

    private static UserMealDAO userMealDAOInstance = new SimpleMealDAO();

    private Map<Long, UserMeal> dataSource = new ConcurrentHashMap<>();

    protected SimpleMealDAO() {
        dataSource.put(1l, new UserMeal(1l, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        dataSource.put(2l, new UserMeal(2l, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        dataSource.put(3l, new UserMeal(3l, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        dataSource.put(4l, new UserMeal(4l, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        dataSource.put(5l, new UserMeal(5l, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
    }

    public static UserMealDAO getInstance() {
        return userMealDAOInstance;
    }

    @Override
    public List<UserMeal> getAll() {
        return new ArrayList<>(dataSource.values());
    }

    @Override
    public UserMeal getById(long id) {
        return dataSource.get(id);
    }

    @Override
    public void save(UserMeal userMeal) {
        if (userMeal.getId() == null) {
            userMeal.setId(getNextId());
        }
        log.debug("Saving %s", userMeal);
        dataSource.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(Long id) {

        Preconditions.checkNotNull(id);

        log.debug("Deleting userMeal /w id=%s", id);
        Preconditions.checkArgument(
                dataSource.remove(id) != null, "User Meal with id = " + id + " not found");

    }

    private long getNextId() {
       return Collections.max(dataSource.keySet()) + 1l;
    }
}
