package br.com.heitor.postapp.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.heitor.postapp.dto.PostDTO;
import br.com.heitor.postapp.service.PostService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/posts")
public class PostController {

    @Autowired
    private PostService postService;


    @CrossOrigin
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> listPosts() {
        List<PostDTO> list = postService.listPosts();
        if(list.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @CrossOrigin
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insertPost(@Valid @RequestBody PostDTO dto) {
    	try {
	        PostDTO postDTO = postService.insert(dto);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postDTO.getId()).toUri();
	        return ResponseEntity.created(uri).build();
    	} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
    }

    @CrossOrigin
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> updateVotes(@PathVariable Integer id, @RequestBody PostDTO dto) { 
        try {
            postService.update(id, dto);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }

}
