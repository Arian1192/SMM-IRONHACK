package com.ssm.systemmeetmanagement.controller.auth;


import com.ssm.systemmeetmanagement.service.interfaces.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
   @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PatchMapping("/promote")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
        public ResponseEntity<PromoteResponse> promote(@RequestBody RegisterRequest request ){
        return ResponseEntity.ok(authService.promote(request));
    }

}
