package ru.javawebinar.topjava.model;

import com.google.common.base.Objects;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by RAMSES on 12.03.2016.
 */
public class User extends NamedEntity {

    private String email;

    private String password;

    private boolean enabled = true;

    private LocalDateTime registrationDate;

    private Set<Role> roles;

    private int caloriesPerDay;

    public User() {}

    public User(String name, String email, String password, boolean enabled, LocalDateTime registrationDate, int caloriesPerDay, Role role, Role... roles) {
        super(name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
        this.roles = EnumSet.of(role, roles);
        this.caloriesPerDay = caloriesPerDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registrationDate=" + registrationDate +
                ", roles=" + roles +
                "} " + super.toString();
    }
}
