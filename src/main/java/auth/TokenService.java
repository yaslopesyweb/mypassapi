package auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import user.User;
import user.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final UserRepository userRepository;
    Algorithm algorithm = Algorithm.HMAC256("assinatura");

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Token create(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        var token = JWT.create()
                .withSubject(credentials.username())
                .withClaim("role", "ADMIN")
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        return new Token(token, credentials.username());
    }

    public User getUserFromToken(String token) {
        var email = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}