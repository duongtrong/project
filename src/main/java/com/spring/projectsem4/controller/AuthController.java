package com.spring.projectsem4.controller;

import com.spring.projectsem4.entrypoint.ApiResponse;
import com.spring.projectsem4.entrypoint.JwtAuthenticationResponse;
import com.spring.projectsem4.exception.AppException;
import com.spring.projectsem4.model.Role;
import com.spring.projectsem4.model.RoleName;
import com.spring.projectsem4.model.User;
import com.spring.projectsem4.payload.LoginDTO;
import com.spring.projectsem4.payload.SignUpDTO;
import com.spring.projectsem4.repository.role.RoleRepository;
import com.spring.projectsem4.repository.user.UserRepository;
import com.spring.projectsem4.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @RequestMapping(method = RequestMethod.POST, value = "signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        if (userRepository.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseEntity<>(
                    new ApiResponse.SimpleError()
                            .setCode(HttpStatus.BAD_REQUEST.value())
                            .setMessage("Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseEntity<>(
                    new ApiResponse.SimpleError()
                            .setCode(HttpStatus.BAD_REQUEST.value())
                            .setMessage("Email is already taken!").build(),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(
                signUpDTO.getUsername(),
                signUpDTO.getFullName(),
                signUpDTO.getPassword(),
                signUpDTO.getEmail(),
                signUpDTO.getPhone(),
                signUpDTO.getBirthday(),
                signUpDTO.getAddress());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(role));
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse.Success()
                        .setStatus(HttpStatus.CREATED.value())
                        .setMessage("Success")
                        .addData(new SignUpDTO(result))
                        .build());
    }
}
