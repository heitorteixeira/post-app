package br.com.heitor.postapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.heitor.postapp.dto.PostDTO;
import br.com.heitor.postapp.model.Post;
import br.com.heitor.postapp.respository.PostRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    ModelMapper modelMapper = new ModelMapper();
    
    public List<PostDTO> listPosts() {
        List<Post> posts = repo.findAll();
        return posts.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    public PostDTO insert(PostDTO dto) {
        Post post = convertToEntity(dto);
        post.setId(null);
        return convertToDto(repo.save(post));
    }

    public PostDTO findById(Integer id) throws ObjectNotFoundException {
    	Post post = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Not found"));
        return convertToDto(post);
    }

    public Post update(Integer id, PostDTO postDTO) throws ObjectNotFoundException{
    	PostDTO dbPostDTO = findById(id);
    	dbPostDTO.setUpvotes(postDTO.getUpvotes());
        return repo.save(convertToEntity(postDTO));
    }

    private PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private Post convertToEntity(PostDTO dto) {
        return modelMapper.map(dto, Post.class);
    }
}
