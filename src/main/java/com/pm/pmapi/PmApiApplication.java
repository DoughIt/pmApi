package com.pm.pmapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.pm.pmapi.mbg.mapper"})
public class PmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmApiApplication.class, args);
    }

}
