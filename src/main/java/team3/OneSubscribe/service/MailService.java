package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team3.OneSubscribe.domain.Member;

@RequiredArgsConstructor
@Service
public class MailService {

    //자동 autowired
    private final JavaMailSender javaMailSender;

    public void sendMail(String to, String title, String content) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo("zhdhfhd33@naver.com");
//        simpleMailMessage.setSubject("spring mail test!!");
//        simpleMailMessage.setText("안녕민건!");
//        javaMailSender.send(simpleMailMessage);
    }
}


