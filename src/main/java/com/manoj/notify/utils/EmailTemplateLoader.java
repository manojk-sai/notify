package com.manoj.notify.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class EmailTemplateLoader {

    public String loadTemplate(String templateName) {
        try {
            ClassPathResource resource =
                    new ClassPathResource("templates/" + templateName);
            byte[] bytes = resource.getInputStream().readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load template", e);
        }
    }
}
