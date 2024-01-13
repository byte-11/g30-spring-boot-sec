package uz.pdp.g30spingbootsecurity.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.g30spingbootsecurity.config.jwt.JwtTokenProvider;
import uz.pdp.g30spingbootsecurity.domain.User;
import uz.pdp.g30spingbootsecurity.dto.request.UserRegisterDto;
import uz.pdp.g30spingbootsecurity.dto.web.JwtDto;
import uz.pdp.g30spingbootsecurity.repo.UserRepository;
import uz.pdp.g30spingbootsecurity.repo.UserRoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public User getUserByUsername(@NonNull String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public JwtDto register(UserRegisterDto userRegisterDto) {
        User user = new User(userRegisterDto.username(), passwordEncoder.encode(userRegisterDto.password()));
        user.setRoles(List.of(
                roleRepository.findByName("ROLE_USER")
                        .orElseThrow(() -> new RuntimeException("Role not found"))
        ));
        user = userRepository.save(user);
        String token = jwtTokenProvider.generateToken(user);
        return new JwtDto(token);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUsersById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User not found with id: " + id)
                );
    }
}
