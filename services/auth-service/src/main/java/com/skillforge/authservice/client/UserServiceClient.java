package com.skillforge.authservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class UserServiceClient {

    private final RestClient restClient;
    private final String baseUrl;

    public UserServiceClient(
            RestClient.Builder restClientBuilder,
            @Value("${services.user-service.base-url:http://user-service}") String baseUrl
    ) {
        this.restClient = restClientBuilder.build();
        this.baseUrl = baseUrl.replaceAll("/+$", "");
    }

    public void createProfile(UUID authAccountId, String email, String firstName, String lastName) {
        restClient.post()
                .uri(baseUrl + "/api/users")
                .body(new CreateUserRequest(authAccountId, email, firstName, lastName))
                .retrieve()
                .toBodilessEntity();
    }

    private record CreateUserRequest(
            UUID authAccountId,
            String email,
            String firstName,
            String lastName
    ) {
    }
}
