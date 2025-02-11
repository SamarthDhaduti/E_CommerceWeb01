package com.example.payment_service.Service;


import com.example.payment_service.Config.RazorPayClient;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayGateWay implements PaymentServices{

   private RazorpayClient razorpayClient;
   public RazorPayGateWay(RazorpayClient razorpayClient) {
       this.razorpayClient = razorpayClient;
   }

    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {
         /*
        Will you first make a call to order service to validate the orderId and get some order details?
        Rest template can be used to interact with order service. This will done in future classes.
        Assuming that orderId is valid & we will assume some random amount for it.
         */
        /* code for calling the razorpay to get the link from the razor pay github repository */
        JSONObject paymentLinkRequest = new JSONObject();//request body / payload to be sent to razorpay
        paymentLinkRequest.put("amount",1000);//10 rupees. PG supports amounts upto 2 decimal places. 10.50 => 1050
        paymentLinkRequest.put("currency","INR");
        //paymentLinkRequest.put("accept_partial",true);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 10*60*1000);//epoch (Standard way of representing time), 10 minutes = 6*10^5 ms
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Test payment for Integration of Payment gateway session on 11th Jan 25");

        JSONObject customer = new JSONObject();
        customer.put("name","Samarth Dhaduti");
        customer.put("contact","+91 9480515368");
        customer.put("email","samarthcdhaduti@gmail.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);

//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);

        paymentLinkRequest.put("callback_url","https://www.scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
