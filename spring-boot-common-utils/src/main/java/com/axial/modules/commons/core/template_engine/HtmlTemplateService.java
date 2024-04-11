package com.axial.modules.commons.core.template_engine;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

@Service
@AllArgsConstructor
public class HtmlTemplateService {

    private static TemplateEngine templateEngine;

    @PostConstruct
    private void initOps() {

        templateEngine = new TemplateEngine();

        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("src/main/resources/templates/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);
    }


    public TemplateEngine getTemplateEngine() {

        return templateEngine;
    }

    /*

        EXAMPLE SYNTAX:

        <!DOCTYPE html>
        <html lang="tr" xmlns:th="http://www.thymeleaf.org">
            <head>
                <meta charset="UTF-8">
                <title>Title</title>
            </head>
            <head>
                <title>My Thymeleaf Template</title>
            </head>
            <body>
                <p th:text="${message}">Default Message</p>

                <p><strong>Dummy</strong></p>

            </body>
        </html>


        CALLING:

        @GetMapping(value = "/demo10-html-template")
        public ResponseEntity<String> demo10(@RequestParam String message) {

            final Context context = new Context();
            context.setVariable("message", message);

            final String processedStr = htmlTemplateService.processTemplate("demo-page", context);

            return ResponseEntity.ok(processedStr);
        }

     */
    public String processTemplate(String templateName, Context context) {

        return getTemplateEngine().process(templateName, context);
    }



}
