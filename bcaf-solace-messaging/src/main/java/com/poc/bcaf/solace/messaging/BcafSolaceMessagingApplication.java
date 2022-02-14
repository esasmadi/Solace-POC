package com.poc.bcaf.solace.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.poc.bcaf.solace.messaging.model.Json;
import com.poc.bcaf.solace.messaging.model.SendEmail;
import com.poc.bcaf.solace.messaging.util.UtilBase64Image;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class BcafSolaceMessagingApplication {


	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(BcafSolaceMessagingApplication.class, args);


		SendEmail sendEmail = new SendEmail("from@gmail.com", "to@gmail.com");
		String jsonSource = sendEmail.toJSON();

		try {
			JsonNode node = Json.parse(jsonSource);
			System.out.println("=== PARSE JSON === " + node.get("RequestParameter"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
