package com.ssm.systemmeetmanagement.controller.auth;


import com.ssm.systemmeetmanagement.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){

        System.out.println("PASO POR AQUI");
        return ResponseEntity.ok(authService.login(request));
    }

   @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        System.out.println("PASO POR AQUI REGISTER");
        return ResponseEntity.ok(authService.register(request));
    }

    @PatchMapping("/promote")
        public ResponseEntity<PromoteResponse> promote(@RequestBody RegisterRequest request ){
        System.out.println("PASO POR AQUI PARA PROMOCIONAR UN USUARIO");
        return ResponseEntity.ok(authService.promote(request));
    }

}
