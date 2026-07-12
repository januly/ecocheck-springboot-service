package lk.ijse.cmjd114_115.ecocheck.service.impl;

import lk.ijse.cmjd114_115.ecocheck.IDGenerate;
import lk.ijse.cmjd114_115.ecocheck.dao.UserDAO;
import lk.ijse.cmjd114_115.ecocheck.dto.AuthResponseDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.LoginDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.UserDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.enums.Role;
import lk.ijse.cmjd114_115.ecocheck.entity.UserEntity;
import lk.ijse.cmjd114_115.ecocheck.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    @Value("${app.jwt.expiration-seconds}")
    private long expirationSeconds;

    @Override
    public UserDTO register(UserDTO userDTO) {
        if (userDAO.existsByEmail(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUserId(userDTO.getUserId() == null ? IDGenerate.userId() : userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole() == null ? Role.USER : userDTO.getRole());

        return toDTO(userDAO.save(user));
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserEntity user = userDAO.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid login"));

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("ecocheck-service")
                .subject(user.getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationSeconds))
                .claim("userId", user.getUserId())
                .claim("role", user.getRole().name())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

        return new AuthResponseDTO(
                token,
                "Bearer",
                expirationSeconds,
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    private UserDTO toDTO(UserEntity user) {
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                null,
                user.getRole()
        );
    }
}

