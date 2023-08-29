package com.prueba.nisum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProjectDetails {

    @Autowired
    private Environment environment;

    public String getProjectRegex() {
        return environment.getProperty("regex.validate.password");
    }
}