package uz.pdp.g30spingbootsecurity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.g30spingbootsecurity.domain.Post;
import uz.pdp.g30spingbootsecurity.domain.User;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Post}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto implements Serializable {
    private Long id;
    private String title;
    private String content;
    private PublisherDto publisher;
    private LocalDateTime createdTime;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.publisher = new PublisherDto(post.getPublisher().getId(), post.getPublisher().getUsername());
        this.createdTime = post.getCreatedTime();
    }

    /**
     * DTO for {@link User}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PublisherDto {
        private Long id;
        private String username;
    }
}