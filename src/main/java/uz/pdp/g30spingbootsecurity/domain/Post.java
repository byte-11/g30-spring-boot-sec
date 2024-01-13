package uz.pdp.g30spingbootsecurity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User publisher;

    @Column(nullable = false, columnDefinition = "default now()")
    private LocalDateTime createdTime = LocalDateTime.now();

    public Post(String title, String content, User publisher) {
        this.title = title;
        this.content = content;
        this.publisher = publisher;
    }
}