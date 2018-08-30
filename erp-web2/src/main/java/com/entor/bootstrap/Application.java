package com.entor.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.entor"})
@MapperScan(basePackages={"com.entor.mapper"})

public class Application {
public static void main(String[] args) {
	SpringApplication springApplication = new SpringApplication(Application.class);
	springApplication.run(args);
}

}
