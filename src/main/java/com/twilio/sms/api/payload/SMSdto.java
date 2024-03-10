package com.twilio.sms.api.payload;



public class SMSdto {
    public String to;
    public String message;

    //Getter And Setter
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
