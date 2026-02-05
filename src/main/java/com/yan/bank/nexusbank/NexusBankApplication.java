package com.yan.bank.nexusbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yan.bank.nexusbank") // <-- ADICIONE ISSO
public class NexusBankApplication {
    public static void main(String[] args) {
        SpringApplication.run(NexusBankApplication.class, args);
    }
}