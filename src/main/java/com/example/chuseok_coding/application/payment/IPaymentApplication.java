package com.example.chuseok_coding.application.payment;

import com.example.chuseok_coding.controller.internal.api.dto.PaymentResponseDto;
import java.util.List;

public interface IPaymentApplication {
    PaymentResponseDto payment(List<Integer> productIds, Integer requestedUserId);
    PaymentResponseDto cancel(Integer id, Integer requestedUserId);
}
