package com.yan.bank.nexusbank.domain.service;

import com.yan.bank.nexusbank.domain.model.*;
import com.yan.bank.nexusbank.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Transactional
    public void deposit(Long id, BigDecimal amount) {
        Account acc = findOrThrow(id);
        acc.deposit(amount);
        accountRepository.save(acc);
        saveLog(id, TransactionType.DEPOSIT, amount, "Depósito em dinheiro");
    }

    @Transactional
    public void withdraw(Long id, BigDecimal amount) {
        Account acc = findOrThrow(id);
        acc.withdraw(amount);
        accountRepository.save(acc);
        saveLog(id, TransactionType.WITHDRAWAL, amount, "Saque em espécie");
    }

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        if (fromId.equals(toId)) throw new RuntimeException("Contas de origem e destino devem ser diferentes");

        Account source = findOrThrow(fromId);
        Account target = findOrThrow(toId);

        source.withdraw(amount);
        target.deposit(amount);

        accountRepository.save(source);
        accountRepository.save(target);

        saveLog(fromId, TransactionType.TRANSFER_OUT, amount, "Transferência enviada para conta " + toId);
        saveLog(toId, TransactionType.TRANSFER_IN, amount, "Transferência recebida da conta " + fromId);
    }

    private Account findOrThrow(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta " + id + " não encontrada"));
    }

    private void saveLog(Long accId, TransactionType type, BigDecimal amt, String desc) {
        Transaction log = new Transaction();
        log.setAccountId(accId);
        log.setType(type.name()); // Salva o nome do Enum no banco
        log.setAmount(amt);
        log.setTimestamp(LocalDateTime.now());
        log.setDescription(desc);
        transactionRepository.save(log);
    }
}