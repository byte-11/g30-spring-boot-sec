package uz.pdp.g30spingbootsecurity.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.g30spingbootsecurity.repo.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        uz.pdp.g30spingbootsecurity.domain.User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found")
                );
        return User.builder().username(user.getUsername()).password(user.getPassword()).build();
    }
}
