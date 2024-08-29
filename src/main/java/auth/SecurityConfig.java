package auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
       http.authorizeHttpRequests(auth -> auth
               .requestMatchers(HttpMethod.POST, "/login").permitAll()
               .requestMatchers(HttpMethod.POST, "/passwords").authenticated()
       );
       http.csrf(csrf -> csrf.disable());
       http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }

}
