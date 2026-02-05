package com.yan.bank.nexusbank.controller;

import com.yan.bank.nexusbank.domain.service.BankService;
import com.yan.bank.nexusbank.dto.AccountRequest;
import com.yan.bank.nexusbank.dto.TransferRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final BankService bankService;

    /**
     * Lista as contas com quebra de linha para leitura humana.
     * O Postman exibirá cada conta em uma linha nova, sem colchetes.
     */
    @GetMapping(produces = "text/plain;charset=UTF-8")
    public String listAll(Pageable pageable) {
        return bankService.findAll(pageable)
                .getContent()
                .stream()
                .map(acc -> String.format("CONTA %d\nSaldo Disponível: R$ %.2f\n-----------------------",
                        acc.getId(),
                        acc.getBalance()))
                .collect(Collectors.joining("\n"));
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long id, @RequestBody @Valid AccountRequest req) {
        bankService.deposit(id, req.amount());
        return ResponseEntity.ok("Sucesso: Depósito de R$ " + req.amount() + " realizado na conta " + id);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long id, @RequestBody @Valid AccountRequest req) {
        bankService.withdraw(id, req.amount());
        return ResponseEntity.ok("Sucesso: Saque de R$ " + req.amount() + " realizado com sucesso.");
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<String> transfer(@PathVariable Long id, @RequestBody @Valid TransferRequest req) {
        bankService.transfer(id, req.targetId(), req.amount());
        return ResponseEntity.ok("Sucesso: R$ " + req.amount() + " transferidos para a conta " + req.targetId());
    }
}