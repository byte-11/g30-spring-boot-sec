package uz.pdp.g30spingbootsecurity.dto.web;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JwtDto {
    private String accessToken;
    private LocalDateTime timestamp = LocalDateTime.now();

    public JwtDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
