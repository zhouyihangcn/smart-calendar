package com.sda.smartCalendar.controller;

//import com.twilio.http.TwilioRestClient;
import com.sda.smartCalendar.domain.model.User;
import com.sda.smartCalendar.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;

import java.util.ArrayList;
import java.util.List;


//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import pl.smsapi.BasicAuthClient;
//import pl.smsapi.api.SmsFactory;
//import pl.smsapi.api.action.sms.SMSSend;
//import pl.smsapi.api.response.MessageResponse;
//import pl.smsapi.api.response.StatusResponse;
//import pl.smsapi.exception.ClientException;
//import pl.smsapi.exception.SmsapiException;

@Controller
public class SMSController {

@Autowired
    SMSService smsService;

    @RequestMapping("/greeting")
    public String greeting(){
//            @RequestParam(value="mode", required=false, defaultValue="text") String mode,
//            @RequestParam(value="number", required=true)User user,
//            Model model) {

//            model.addAttribute("number", user.getPhoneNumber());
//            model.addAttribute("mode", mode);
//
//        if(mode.equalsIgnoreCase("text")){
//            sendSMS();
//        }

            smsService.sendSMS();
            return "greeting";

        // public void sendSMS(String to){
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    @RequestMapping("/greeting")
//    public String greeting() {
//        try {
//            String passwordHash = "00000000000000000000000000000000";
//            BasicAuthClient client = new BasicAuthClient("username", passwordHash);
//
//            SmsFactory smsApi = new SmsFactory(client);
//            String phoneNumber = "000000000";
//            SMSSend action = smsApi.actionSend()
//                    .setText("test")
//                    .setTo(phoneNumber);
//
//            StatusResponse result = action.execute();
//
//            for (MessageResponse status : result.getList()) {
//                System.out.println(status.getNumber() + " " + status.getStatus());
//            }
//        } catch (ClientException e) {
//            /**
//             * 101 Niepoprawne lub brak danych autoryzacji.
//             * 102 Nieprawidłowy login lub hasło
//             * 103 Brak punków dla tego użytkownika
//             * 105 Błędny adres IP
//             * 110 Usługa nie jest dostępna na danym koncie
//             * 1000 Akcja dostępna tylko dla użytkownika głównego
//             * 1001 Nieprawidłowa akcja
//             */
//            e.printStackTrace();
//        } catch (SmsapiException e) {
//            e.printStackTrace();
//        }
//        return "greeting";
//    }
//
//}

}
