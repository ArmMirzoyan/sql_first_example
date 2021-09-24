package com.example.tomcattest.repository.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContext {
    public static final org.springframework.context.ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

}

