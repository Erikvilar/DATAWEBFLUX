package com.ltadcrm.ltadcrm;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class LtadcrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtadcrmApplication.class, args);

		System.out.println(calcRandom() + " Valor retornado ");

	}

	private static int calcRandom() {
		int randomNum = new Random().nextInt(1000);
		return randomNum;
	}

}
