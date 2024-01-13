package uz.pdp.g30spingbootsecurity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestParam;

public record PostCreationDto(
        @NotNull Long publisherId,
        @RequestParam(required = false) String title,
        @NotBlank String content
) {
}
