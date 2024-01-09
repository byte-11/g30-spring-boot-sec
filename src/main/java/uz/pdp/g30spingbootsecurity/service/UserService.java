package uz.pdp.g30spingbootsecurity.service;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.g30spingbootsecurity.domain.User;
import uz.pdp.g30spingbootsecurity.dto.request.UserRegisterDto;
import uz.pdp.g30spingbootsecurity.repo.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(@NonNull String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public User register(UserRegisterDto userRegisterDto) {
        return userRepository.save(new User(userRegisterDto.username(),
                passwordEncoder.encode(userRegisterDto.password())));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
