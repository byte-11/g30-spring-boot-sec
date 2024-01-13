package uz.pdp.g30spingbootsecurity.contoller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g30spingbootsecurity.dto.request.UserRegisterDto;
import uz.pdp.g30spingbootsecurity.service.UserService;

@RestController
@RequestMapping("/api/${api.version}/auth")
@Tag(name = "Authentication APIs", description = "apis for authentication purposes")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }
}
