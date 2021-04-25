package ge.find.findjob.services.IT;

import ge.find.findjob.Application;
import ge.find.findjob.config.FindJobProperties;
import ge.find.findjob.domain.User;
import ge.find.findjob.services.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Application.class)
class MailServiceITTests {
    private static final String[] languages = {
            "en",
            "ge",
    };

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FindJobProperties findJobProperties;

    @Spy
    private JavaMailSenderImpl javaMailSender;

    @Captor
    private ArgumentCaptor<MimeMessage> messageCaptor;

    private MailService mailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        doNothing().when(javaMailSender).send(any(MimeMessage.class));
        mailService = new MailService(templateEngine, javaMailSender, messageSource, findJobProperties);
    }

    @Test
    void testSendEmail() throws Exception {
        mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", false, false);
        verify(javaMailSender).send(messageCaptor.capture());
        MimeMessage message = messageCaptor.getValue();
        assertThat(message.getSubject()).isEqualTo("testSubject");
        assertThat(message.getAllRecipients()[0]).hasToString("john.doe@example.com");
        assertThat(message.getFrom()[0]).hasToString(findJobProperties.getEmail());
        assertThat(message.getContent()).isInstanceOf(String.class);
        assertThat(message.getContent()).hasToString("testContent");
        assertThat(message.getDataHandler().getContentType()).isEqualTo("text/plain; charset=UTF-8");
    }

    @Test
    void testSendHtmlEmail() throws Exception {
        mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", false, true);
        verify(javaMailSender).send(messageCaptor.capture());
        MimeMessage message = messageCaptor.getValue();
        assertThat(message.getSubject()).isEqualTo("testSubject");
        assertThat(message.getAllRecipients()[0]).hasToString("john.doe@example.com");
        assertThat(message.getFrom()[0]).hasToString(findJobProperties.getEmail());
        assertThat(message.getContent()).isInstanceOf(String.class);
        assertThat(message.getContent()).hasToString("testContent");
        assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
    }

    @Test
    void testSendActivationEmail() throws Exception {
        User user = createUser();
        mailService.sendActivationEmail(user, user.createToken(), null);
        verify(javaMailSender).send(messageCaptor.capture());
        MimeMessage message = messageCaptor.getValue();
        assertThat(message.getAllRecipients()[0]).hasToString(user.getEmail());
        assertThat(message.getFrom()[0]).hasToString(findJobProperties.getEmail());
        assertThat(message.getContent().toString()).isNotEmpty();
        assertThat(message.getDataHandler().getContentType()).isEqualTo("text/html;charset=UTF-8");
    }

    @Test
    void testSendEmailWithException() {
        doThrow(MailSendException.class).when(javaMailSender).send(any(MimeMessage.class));
        try {
            mailService.sendEmail("john.doe@example.com", "testSubject", "testContent", false, false);
        } catch (Exception e) {
            fail("Exception shouldn't have been thrown");
        }
    }

    private User createUser() {
        return User.builder()
                .username("john")
                .email("john.doe@example.com")
                .build();
    }
}
