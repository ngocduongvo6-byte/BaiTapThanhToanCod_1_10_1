package gk2026.com.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class MailUtil_24110188 {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_USERNAME = "ngocduongvo6@gmail.com";
    // Gmail App Password: nhập liền 16 ký tự, không có khoảng trắng.
    private static final String SMTP_PASSWORD = "kvwf cfzn ynrh gjli";

    private MailUtil_24110188() {
    }

    public static void send(String to, String otp) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", String.valueOf(SMTP_PORT));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.connectiontimeout", "10000");
        properties.put("mail.smtp.timeout", "10000");
        properties.put("mail.smtp.writetimeout", "10000");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SMTP_USERNAME));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Mã OTP đăng ký tài khoản", StandardCharsets.UTF_8.name());
        message.setText("Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong phiên đăng ký hiện tại.",
                StandardCharsets.UTF_8.name());
        Transport.send(message);
    }
}
