package ge.find.findjob.services;

import ge.find.findjob.config.FindJobProperties;
import ge.find.findjob.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private static final String USER = "user";
    private static final String BASE_URL = "url";

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final FindJobProperties findJobProperties;

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(findJobProperties.getEmail());
            message.setSubject(subject);
            message.setText(content, isHtml);

            mailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }


    @Async
    public void sendEmailFromTemplate(List<User> users, String templateName, String titleKey) {
        users.forEach(user -> {
            if (user.getEmail() == null) {
                log.debug("Email doesn't exist for user '{}'", user.getUsername());
            } else {
                Context context = new Context();
                context.setVariable(USER, user);
                String content = templateEngine.process(templateName, context);
                sendEmail(user.getEmail(), titleKey, content, false, true);
            }
        });
    }

    @Async
    public void sendActivationEmail(User user, String activationToken, String localeKey) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getUsername());
            return;
        }
        Locale locale = localeKey == null ? Locale.getDefault() : Locale.forLanguageTag(localeKey);

        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, findJobProperties.getBaseUrl());
        context.setVariable("activationToken", activationToken);
        String content = templateEngine.process("mail/activationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);

        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendPasswordResetMail(User user) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getUsername());
            return;
        }

        Context context = new Context();
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, findJobProperties.getBaseUrl());
//        context.setVariable("activationToken", activationToken);
        String content = templateEngine.process("mail/passwordResetEmail", context);

        sendEmail(user.getEmail(), "email.reset.title", content, false, true);
    }

}
