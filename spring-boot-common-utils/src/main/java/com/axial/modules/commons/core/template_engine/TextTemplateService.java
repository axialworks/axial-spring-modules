package com.axial.modules.commons.core.template_engine;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Service
@AllArgsConstructor
public class TextTemplateService {

    private static TemplateEngine templateEngine;

    @PostConstruct
    private void initOps() {

        templateEngine = new TemplateEngine();

        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateEngine.setTemplateResolver(templateResolver);
    }

    public TemplateEngine getTemplateEngine() {

        return templateEngine;
    }

    /*
        Ref: https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#conditional-expressions
        Example Syntax:

        {
          "text": "Merhaba [[${username}]], [[${logged} == '1' ? 'hoşgeldin' : 'güle güle']]",
          "parameters": {
            "username": "Selman",
            "logged": "1"
          }
        }
     */
    public String processTemplate(String strText, Context context) {

        return getTemplateEngine().process(strText, context);
    }

}
