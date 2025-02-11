package com.example.payment_service.Controller;

import com.example.payment_service.Config.RazorPayClient;
import com.example.payment_service.DTOs.GeneratePaymentLinkRequestDto;
import com.example.payment_service.Service.PaymentServices;
import com.example.payment_service.Service.RazorPayGateWay;
import com.example.payment_service.Service.StripeGateWay;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentController {

    private RazorPayGateWay razorPayGateWay;
    private StripeGateWay stripeGateWay;

    public PaymentController(RazorPayGateWay razorPayGateWay, StripeGateWay stripeGateWay) {
        this.razorPayGateWay = razorPayGateWay;
        this.stripeGateWay = stripeGateWay;
    }
    /* here we are not fetching the data that is already exists, so we are making post call using orderId */
    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto generatePaymentLinkRequestDto) throws RazorpayException, StripeException {

        // return razorPayGateWay.generatePaymentLink(generatePaymentLinkRequestDto.getOrderId());

        return stripeGateWay.generatePaymentLink(generatePaymentLinkRequestDto.getOrderId());
    }
}
