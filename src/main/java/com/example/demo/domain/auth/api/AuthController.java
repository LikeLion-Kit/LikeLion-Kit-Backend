package com.example.demo.domain.auth.api;

import com.example.demo.domain.auth.application.AuthService;
import com.example.demo.domain.auth.dto.request.JoinRequest;
import com.example.demo.global.base.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Void> join(@RequestBody @Valid JoinRequest request) {
        Boolean joinSuccess = authService.join(request);

        return ApiResponse.ok(joinSuccess);
    }
}