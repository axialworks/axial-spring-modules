package com.axial.modules.commons.message.config;

import com.axial.modules.commons.constants.CommonConstants;
import com.axial.modules.commons.constants.CommonLangConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageWebConfig implements WebMvcConfigurer {

    @Value( "${axial.message-bundle}")
    private String messageBundleName;

    @Value( "${axial.default-locale}")
    private String defaultLangCode;


    @Bean("commonMessages")
    MessageSource baseMessageSource() {

        final ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename("classpath:CommonMessages");
        bean.setDefaultEncoding(CommonConstants.DEFAULT_ENCODING);
        return bean;
    }

    @Bean
    MessageSource messageSource() {

        if (StringUtils.isBlank(messageBundleName)) {
            return null;
        }

        final ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename("classpath:" + messageBundleName);
        bean.setDefaultEncoding(CommonConstants.DEFAULT_ENCODING);
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(CommonLangConstants.getLocaleByLangCode(defaultLangCode));
        slr.setSupportedLocales(CommonLangConstants.SUPPORTED_LANGUAGES.values().stream().toList());
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("Accept-Language");
        lci.setIgnoreInvalidLocale(true);
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
