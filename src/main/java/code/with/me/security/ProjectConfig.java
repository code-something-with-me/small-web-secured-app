package code.with.me.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author andong@xiaomalixing.com
 */
@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder() {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProviderService authenticationProviderService) throws Exception {
        return http.authenticationProvider(authenticationProviderService)
                .authorizeHttpRequests(a -> a.anyRequest().authenticated())
                .formLogin(flc -> flc.defaultSuccessUrl("/main", true))
                .build();
    }
}
