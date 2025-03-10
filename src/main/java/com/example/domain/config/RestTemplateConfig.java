package com.example.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private final String indicatorApiUrl;
    private final String translateApiUrl;
    private final String apiKey;

    public RestTemplateConfig(@Value("${services.indicator}") String indicatorApiUrl,
                              @Value("${services.translate.url}") String translateApiUrl,
                              @Value("${services.translate.api-key}") String apiKey) {
        this.indicatorApiUrl = indicatorApiUrl;
        this.translateApiUrl = translateApiUrl;
        this.apiKey = apiKey;
    }

    @Bean("indicatorRestTemplate")
    public RestTemplate indicatorRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(indicatorApiUrl).build();
    }

    @Bean("translateRestTemplate")
    public RestTemplate translateRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(translateApiUrl)
                .additionalInterceptors(addApiKeyInterceptor())
                .build();
    }

    private ClientHttpRequestInterceptor addApiKeyInterceptor() {
        return (request, body, execution) -> {
            request.getHeaders().add("X-API-Key", apiKey);
            return execution.execute(request, body);
        };
    }
}
