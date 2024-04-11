package com.axial.modules.commons.core.message;

import com.axial.modules.commons.core.message.constants.CommonMessageConstants;
import com.axial.modules.commons.core.message.enums.Severity;
import com.axial.modules.commons.core.request.RequestUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.LocaleResolver;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonMessageUtils {

    public static String getMessage(String messageKey, Object... args) {
        return getMessage(messageKey, CommonMessageUtils.getLocale(), args);
    }

    public static String getMessage(String messageKey, Locale locale, Object... args) {
        return getLocalizedMessage(messageKey, locale, args);
    }

    public static Locale getLocale() {

        final LocaleResolver localeResolver = RequestUtils.getBean(LocaleResolver.class);
        final Locale locale = localeResolver.resolveLocale(RequestUtils.getCurrentRequest());
        return locale;
    }

    private static String getLocalizedMessage(String errorCode, Locale locale, Object... args) {

        if (StringUtils.isBlank(errorCode)) {
            return null;
        } else {
            Map<String, MessageSource> messageSources = RequestUtils.getBeansOfType(MessageSource.class);
            return messageSources.values().stream().map((messageSource) ->
                    getLocalizedMessageOrNull(messageSource, errorCode, args, locale)).filter(Objects::nonNull).findFirst().orElse(null);
        }
    }

    private static String getLocalizedMessageOrNull(MessageSource messageSource, String code, Object[] args, Locale locale) {

        try {
            return messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException var5) {
            return null;
        }
    }

    public static String generateExternalMessage(String messageText, Object... args) {

        final List<Object> argList = Arrays.asList(args);

        if (CollectionUtils.isEmpty(argList)) {
            return messageText;
        }

        String resultText = messageText;

        for (int i = 0; i < args.length; i++) {
            resultText = resultText.replace("{" + i + "}", args[i].toString());
        }

        return resultText;
    }

    public static final String generateMessageCode(Severity severity, String messageCode) {

        return generateMessageCode(severity, CommonMessageConstants.COMMONS_MODULE_MESSAGE_CODE, messageCode);
    }

    public static final String generateMessageCode(Severity severity, String moduleCode, String messageCode) {

        if (StringUtils.isBlank(messageCode)) {
            return null;
        }

        final String severityCode = switch (severity) {
            case INFO -> "1";
            case WARNING -> "2";
            case ERROR -> "3";
            default -> "0";
        };

        final String messageCodeChecked = StringUtils.leftPad(messageCode, 4, "0");
        return StringUtils.join(severityCode, moduleCode, messageCodeChecked);
    }

}
