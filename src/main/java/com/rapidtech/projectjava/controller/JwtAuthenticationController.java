package com.rapidtech.projectjava.controller;

import com.rapidtech.projectjava.config.JwtTokenUtil;
import com.rapidtech.projectjava.dto.UserDto;
import com.rapidtech.projectjava.model.JwtRequest;
import com.rapidtech.projectjava.model.JwtResponse;
import com.rapidtech.projectjava.service.impl.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Operation(summary = "Mendaftarkan Pengguna Baru",description = "Mendaftarkan pengguna baru")
    @ApiResponses(value={@ApiResponse(responseCode = "200",
            description = "Berhasil menambahkan pengguna baru",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDetails.class))}),
            @ApiResponse(responseCode = "401",description = "Gagal menambahkan user baru",content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userDetailsService.save(userDto));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception{
        authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username,String password) throws Exception{
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username,password);
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch(DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
