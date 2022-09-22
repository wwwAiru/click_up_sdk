package ru.egartech.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import ru.egartech.sdk.property.AuthorizationProperties;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationRequestInterceptor implements ClientHttpRequestInterceptor {
    private final AuthorizationProperties properties;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", properties.pullToken());
        return execution.execute(request, body);
    }
}
