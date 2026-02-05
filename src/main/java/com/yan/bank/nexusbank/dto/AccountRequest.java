package com.yan.bank.nexusbank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record AccountRequest(
        @NotNull(message = "O valor não pode ser nulo")
        @Positive(message = "O valor deve ser maior que zero")
        BigDecimal amount
) {}