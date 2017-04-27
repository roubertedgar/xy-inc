package com.xyinc.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Value("${spring.h2.console.path}")
    String h2Path;

    @Value("${spring.h2.console.enabled}")
    Boolean enabled;

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings(h2Path);
        registrationBean.setEnabled(enabled);
        return registrationBean;
    }
}
