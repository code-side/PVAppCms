package com.codeside.pvapp.web.rest;

import com.codeside.pvapp.security.jwt.JWTConfigurer;
import com.codeside.pvapp.security.jwt.TokenProvider;
import com.codeside.pvapp.web.rest.vm.LoginVM;
import com.codeside.pvapp.web.rest.vm.LoginMobile;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;

import com.codeside.pvapp.domain.PVAppUser;
import com.codeside.pvapp.repository.PVAppUserRepository;
import com.codeside.pvapp.service.dto.PVAppUserDTO;
import com.codeside.pvapp.service.mapper.PVAppUserMapper;
import io.github.jhipster.web.util.ResponseUtil;
import java.util.Optional;
/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final Logger log = LoggerFactory.getLogger(UserJWTController.class);

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final PVAppUserRepository pVAppUserRepository;

    private final PVAppUserMapper pVAppUserMapper;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager,
    PVAppUserRepository pVAppUserRepository,  PVAppUserMapper pVAppUserMapper) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.pVAppUserRepository = pVAppUserRepository;
        this.pVAppUserMapper = pVAppUserMapper;
    }

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
        } catch (AuthenticationException ae) {
            log.trace("Authentication exception trace: {}", ae);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/authenticateUser")
      @Timed
      public ResponseEntity<PVAppUserDTO> authenticateUser(@Valid @RequestBody LoginMobile login, HttpServletResponse response) {

            PVAppUser pvAppUser = pVAppUserRepository.findOneByEmailAndPassword(login.getUsername(), login.getPassword());
            PVAppUserDTO pVAppUserDTO = pVAppUserMapper.toDto(pvAppUser);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pVAppUserDTO));
        }




    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
