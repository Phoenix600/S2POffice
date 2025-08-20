package com.s2p;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class S2POfficeApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(S2POfficeApplication.class, args);
	}

}
