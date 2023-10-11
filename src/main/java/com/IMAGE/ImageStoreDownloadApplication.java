package com.IMAGE;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.IMAGE.CONFIG.twilioConfig;
import com.twilio.Twilio;


@SpringBootApplication
public class ImageStoreDownloadApplication {
	
	@Autowired
	private twilioConfig twilioConfig;

	@PostConstruct
	public void initTwilio(){
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}


	public static void main(String[] args) {
		SpringApplication.run(ImageStoreDownloadApplication.class, args);
	}

}
