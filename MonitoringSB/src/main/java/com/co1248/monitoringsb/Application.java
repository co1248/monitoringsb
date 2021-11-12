package com.co1248.monitoringsb;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.co1248.monitoringsb.controlloer.HostController;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		HostController hostcon = new HostController();
		hostcon.monitoring();
	}

}
