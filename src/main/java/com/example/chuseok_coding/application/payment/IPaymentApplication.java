package com.example.chuseok_coding.application.payment;

import com.example.chuseok_coding.controller.internal.api.dto.PaymentResponseDto;
import java.util.List;

public interface IPaymentApplication {
    PaymentResponseDto payment(List<Integer> productIds);
    PaymentResponseDto cancel(Integer id);
}
