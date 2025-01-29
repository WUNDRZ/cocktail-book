package org.avasylchuk.cocktailbook;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.avasylchuk.cocktailbook.UserPrincipal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegisterRequestDto requestDto) {
        return new TokenResponse(userService.registerUser(requestDto));
    }

    @PostMapping("/access-token")
    public TokenResponse login(@RequestBody LoginRequestDto requestDto) {
        String token = userService.authenticateAndGenerateToken(requestDto);
        return new TokenResponse(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getCurrentUser(@AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(new UserProfileDto(principal.getId(), principal.getUsername()));
    }
}
