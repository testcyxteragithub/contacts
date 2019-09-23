package com.avivas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@ComponentScan({"com.avivas"})
@SpringBootApplication
public class ContactsMain 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(ContactsMain.class, args);
	}	
}
