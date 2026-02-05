package com.yan.bank.nexusbank.dto;
import java.math.BigDecimal;
public record TransferRequest(Long targetId, BigDecimal amount) {}