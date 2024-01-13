package uz.pdp.g30spingbootsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.g30spingbootsecurity.domain.Post;
import uz.pdp.g30spingbootsecurity.dto.response.PostDto;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
                SELECT new uz.pdp.g30spingbootsecurity.dto.response.PostDto(p) FROM Post p WHERE p.publisher.id = :publisherId
            """)
    List<PostDto> findAllByPublisher_Id(@Param("publisherId") Long publisherId);
}