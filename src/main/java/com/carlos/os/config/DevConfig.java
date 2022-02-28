package com.carlos.os.config;

import com.carlos.os.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciaDB(){
        if (ddl.equals("create")) {
            this.dbService.instanciaDB();
        }
        return false;
    }
}
