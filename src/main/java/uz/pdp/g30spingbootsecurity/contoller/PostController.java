package uz.pdp.g30spingbootsecurity.contoller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g30spingbootsecurity.dto.request.PostCreationDto;
import uz.pdp.g30spingbootsecurity.dto.response.PostDto;
import uz.pdp.g30spingbootsecurity.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/posts")
@RequiredArgsConstructor
@Tag(name = "Post APIs")
@SecurityRequirement(name = "BearerAuth")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody @Valid PostCreationDto postCreationDto){
        return ResponseEntity.ok(postService.save(postCreationDto));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<List<PostDto>> getAllPostByPublisherId(@PathVariable Long publisherId){
        return ResponseEntity.ok(postService.getAllPostByPublisherId(publisherId));
    }
}
