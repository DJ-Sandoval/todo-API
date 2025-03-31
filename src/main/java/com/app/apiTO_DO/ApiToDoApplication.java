package com.app.apiTO_DO;

import com.app.apiTO_DO.persistence.entity.PermissionEntity;
import com.app.apiTO_DO.persistence.entity.RoleEntity;
import com.app.apiTO_DO.persistence.entity.RoleEnum;
import com.app.apiTO_DO.persistence.entity.UserEntity;
import com.app.apiTO_DO.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ApiToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiToDoApplication.class, args);
	}


}
