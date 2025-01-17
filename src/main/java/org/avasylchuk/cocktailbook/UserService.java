package org.avasylchuk.cocktailbook;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new CustomException("Username already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(requestDto.getUsername());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);

        return jwtUtils.generateToken(user);
    }

    public String authenticateAndGenerateToken(LoginRequestDto requestDto) {
        UserEntity user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new CustomException("Invalid credentials"));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid credentials");
        }
        return jwtUtils.generateToken(user);
    }
}
