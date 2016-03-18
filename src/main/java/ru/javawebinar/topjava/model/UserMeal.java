package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {

    public static final Comparator<UserMeal> BY_DATE_DESC_COMPARATOR
            = ((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private Long userId;

    public UserMeal() {
        dateTime = LocalDateTime.now();
    }

    public UserMeal(Long id, LocalDateTime dateTime, String description, int calories) {
        Objects.requireNonNull(dateTime);
        Objects.requireNonNull(description);
        Objects.requireNonNull(userId);
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                "} " + super.toString();
    }
}
