package ru.javawebinar.topjava;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Topjava Enterprise!");
        LocalDateTime ldt = LocalDateTime.of(2016, 01, 17, 17, 17, 17);
        System.out.println(ldt);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Brazil/East"));
        System.out.println(zdt);
        System.out.println(zdt.toInstant());
        System.out.println(Date.from(zdt.toInstant()));
        System.out.println(ldt.atZone(ZoneId.systemDefault()));


    }
}
