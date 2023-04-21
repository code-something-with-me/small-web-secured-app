package code.with.me.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author andong@xiaomalixing.com
 */
@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

    private final JpaUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        if (userDetailsService.loadUserByUsername(username) instanceof CustomUserDetails customUserDetails) {
            switch (customUserDetails.getUser().getAlgorithm()) {
                case BCRYPT -> {
                    return checkPassword(password, customUserDetails, bCryptPasswordEncoder);
                }
                case SCRYPT -> {
                    return checkPassword(password, customUserDetails, sCryptPasswordEncoder);
                }
            }
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    private UsernamePasswordAuthenticationToken checkPassword(String rawPassword,
                                                              CustomUserDetails user,
                                                              PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Bad Credentials");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
