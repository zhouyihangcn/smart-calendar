package com.sda.smartCalendar.service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SMSService {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACd20f0b138f45cc6ba77268ba1736cb1b";
    public static final String AUTH_TOKEN = "dd08befa5e138319109f86caac362fa8";
    public static final String TWILIO_NUMBER = "+48732230756";

    public void sendSMS(String number){
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", "Halllo grupa Escolla :)"));
            //params.add(new BasicNameValuePair("To", to));
            params.add(new BasicNameValuePair("To", number));
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));
            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
