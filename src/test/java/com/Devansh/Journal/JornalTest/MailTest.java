package com.Devansh.Journal.JornalTest;


import com.Devansh.Journal.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTest {

    @Autowired
    private EmailService emailService;

    @Test
    public  void mailTest(){
        emailService.sendMail("mannatkumar053@gmail.com","Backchodi","Moja main");
    }
}
