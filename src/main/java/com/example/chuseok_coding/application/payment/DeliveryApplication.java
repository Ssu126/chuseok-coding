package com.example.chuseok_coding.application.payment;

import com.example.chuseok_coding.controller.external.api.dto.DeliveryResponseDto;
import com.example.chuseok_coding.repository.payment.Payment;
import com.example.chuseok_coding.repository.user.User;
import com.example.chuseok_coding.service.payment.PaymentService;
import com.example.chuseok_coding.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryApplication {
    private final PaymentService paymentService;
    private final UserService userService;

    public DeliveryResponseDto delivery(Integer paymentId) {
        Payment payment = paymentService.getPayment(paymentId);
        payment.delivering();
        return DeliveryResponseDto.from(payment);
    }

    public DeliveryResponseDto delivered(Integer paymentId) {
        Payment payment = paymentService.getPayment(paymentId);
        payment.delivered();
        Integer paidUserId = payment.getCreatedBy();
        User user = userService.getUser(paidUserId);
        user.earn(payment.getPaidPrice());
        return DeliveryResponseDto.from(payment);
    }
}
