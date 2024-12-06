package org.example.controller;

import org.example.configuration.JwtUtil;
import org.example.model.LoginRequestDTO;
import org.example.model.Utente;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @Autowired
UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody LoginRequestDTO authRequest) {

        if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
            return "user already exists";
        }


        Utente newUser = new Utente();
        newUser.setUsername(authRequest.getUsername());

        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
        newUser.setPassword(encodedPassword);
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        newUser.setRoles(roles);



        userRepository.save(newUser);

        return "user registered successfull";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        System.out.println("cajofadmfda"+ loginRequestDTO.getUsername()+ loginRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        System.out.println(loginRequestDTO.getUsername()+ loginRequestDTO.getPassword());
      
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);  
        System.out.println(token);

        return ResponseEntity.ok("Bearer " + token);  
    }
}
