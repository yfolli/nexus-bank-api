package com.yan.bank.nexusbank.config;

import com.yan.bank.nexusbank.domain.model.Account;
import com.yan.bank.nexusbank.domain.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestConfig {

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            if (repository.count() == 0) {

                Account acc1 = new Account();
                acc1.setBalance(new BigDecimal("1000.00"));

                Account acc2 = new Account();
                acc2.setBalance(new BigDecimal("500.00"));

                repository.save(acc1);
                repository.save(acc2);

                System.out.println("--------------------------------------");
                System.out.println(">>> BANCO DE DADOS INICIALIZADO <<<");
                System.out.println(">>> Conta 1: R$ 1000.00           <<<");
                System.out.println(">>> Conta 2: R$ 500.00            <<<");
                System.out.println("--------------------------------------");
            }
        };
    }
}