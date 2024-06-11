package cn.edu.bistu.mail;

import cn.edu.bistu.service.RedisService;
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

/**
 * 发送验证码，并将验证码是否正确
 */
@Component
public class MailMsg {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String senderMailAddress;

    public static final String VERIFY_CODE = "verify_code";
    // 验证码过期时间
    public static final int EXPIRATION_TIME = 60;

    public boolean mail(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        String code = CodeGeneratorUtil.generatorCode(6);
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("KeySpace验证码");

        redisService.set(email + VERIFY_CODE, code, EXPIRATION_TIME);

        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("expiration_time", EXPIRATION_TIME / 60);
        String content = this.templateEngine.process("verify_code_mail", context);
        helper.setText(content, true);

        helper.setTo(email);
        helper.setFrom(senderMailAddress);
        mailSender.send(message);
        return true;
    }

    public boolean verify(String email, String code) {
        if (code == null) return false;
        String verify_code = (String) redisService.get(email + VERIFY_CODE);
        return code.equals(verify_code);
    }
}
