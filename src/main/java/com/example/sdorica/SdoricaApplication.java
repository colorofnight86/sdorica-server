package com.example.sdorica;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.sdorica.dao")
public class SdoricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdoricaApplication.class, args);
	}

}
