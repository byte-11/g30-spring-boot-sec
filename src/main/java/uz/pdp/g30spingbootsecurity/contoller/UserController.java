package uz.pdp.g30spingbootsecurity.contoller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g30spingbootsecurity.service.UserService;

@RestController
@RequestMapping("/api/${api.version}/users")
@Tag(name = "User APIs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
         return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId){
        return ResponseEntity.ok(userService.getUsersById(userId));
    }

}
