package ge.find.findjob.util;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class AppUtil {

    public static String getLanguageCode(HttpServletRequest request) {
        String locale = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

        return locale;
    }
}
