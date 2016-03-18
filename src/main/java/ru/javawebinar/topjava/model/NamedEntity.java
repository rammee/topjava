package ru.javawebinar.topjava.model;

import java.util.Comparator;
import java.util.jar.Attributes;

/**
 * Created by RAMSES on 12.03.2016.
 */
public class NamedEntity extends BaseEntity {

    public static final Comparator<NamedEntity> COMPARATOR =
            ((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName()));
    protected String name;

    protected NamedEntity() {
    }

    protected NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NamedEntity{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
