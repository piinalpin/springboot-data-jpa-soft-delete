package com.piinalpin.customsoftdeletes;

import com.piinalpin.customsoftdeletes.config.CustomJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean.class)
public class CustomSoftDeletesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomSoftDeletesApplication.class, args);
	}

}
