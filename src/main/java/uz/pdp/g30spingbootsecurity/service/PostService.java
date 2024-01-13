package uz.pdp.g30spingbootsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.g30spingbootsecurity.domain.Post;
import uz.pdp.g30spingbootsecurity.domain.User;
import uz.pdp.g30spingbootsecurity.dto.request.PostCreationDto;
import uz.pdp.g30spingbootsecurity.dto.response.PostDto;
import uz.pdp.g30spingbootsecurity.exception.UserNotFoundException;
import uz.pdp.g30spingbootsecurity.repo.PostRepository;
import uz.pdp.g30spingbootsecurity.repo.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto save(PostCreationDto postCreationDto) {
        final User user = userRepository.findById(postCreationDto.publisherId())
                .orElseThrow(
                        () -> UserNotFoundException.byId(postCreationDto.publisherId())
                );
        Post post = postRepository.save(new Post(
                postCreationDto.title(),
                postCreationDto.content(),
                user
        ));
        return new PostDto(post);
    }

    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream()
                .map(PostDto::new)
                .toList();
    }

    public List<PostDto> getAllPostByPublisherId(Long publisherId){
        return postRepository.findAllByPublisher_Id(publisherId);
    }

}
