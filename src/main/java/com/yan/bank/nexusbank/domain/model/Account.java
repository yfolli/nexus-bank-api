package com.yan.bank.nexusbank.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance = BigDecimal.ZERO;

    @Version // <-- Isso aqui brilha os olhos de recrutadores (Controle de Concorrência)
    private Long version;

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) throw new RuntimeException("Saldo insuficiente");
        this.balance = this.balance.subtract(amount);
    }
}