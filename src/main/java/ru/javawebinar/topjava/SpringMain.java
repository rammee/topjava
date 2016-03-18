package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.service.UserService;

import java.util.Arrays;

/**
 * Created by RAMSES on 14.03.2016.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        UserService userService = context.getBean(UserService.class);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        context.close();
    }
}
