package com.twilio.sms.api.controller;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.sms.api.payload.SMSdto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/3rd_Party_SMS")

public class SMScontrollerTwilio {
    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;
    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    //twilio free account work on register no. only
    @PostMapping
    public ResponseEntity<?> sendSMS(@RequestBody SMSdto msgService){
        try{
            Twilio.init(twilioAccountSid,twilioAuthToken); //toLoginInTwilioAccount
            Message msg= Message.creator(new com.twilio.type.PhoneNumber(msgService.getTo()),
                    new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                    msgService.getMessage())
                    .create();
            return ResponseEntity.ok("SMS sent: "+msg.getSid());

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sent SMS:"+e.getMessage());
        }


    }


}
