package cn.edu.bistu.mail;

import cn.edu.bistu.util.CodeGeneratorUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailMsg {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String senderMailAddress;

    public boolean mail(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        String code = CodeGeneratorUtil.generatorCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("KeySpace验证码");

        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("code", code);
        String content = this.templateEngine.process("verify_code_mail", context);
        helper.setText(content, true);

        helper.setTo(email);
        helper.setFrom(senderMailAddress);
        mailSender.send(message);
        return true;
    }
}
