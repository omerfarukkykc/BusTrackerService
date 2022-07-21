package com.lepric.btservice;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BtserviceApplication {
	//@Autowired
    //private UserService userService;

    @PostConstruct
    public void initUsers() {
		/*
		if(userService.GetUsers().size()==0){
			User user = new User();
			user.setFirstname("Ömer Faruk");
			user.setLastname("Kayıkcı");
			user.setEmail("omer.twd@gmail.com");
			user.setPassword("123456");
			userService.AddUser(user);
		}
		*/
    }
	public static void main(String[] args) {
		SpringApplication.run(BtserviceApplication.class, args);
	}

}
