package com.ecommerce.auth.service;


import com.ecommerce.commons.dto.authentication.AuthenticationRequest;
import com.ecommerce.commons.dto.authentication.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
