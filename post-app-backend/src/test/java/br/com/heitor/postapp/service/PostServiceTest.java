package br.com.heitor.postapp.service;

import br.com.heitor.postapp.dto.PostDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void testListPostsSuccess(){
        List<PostDTO> listPosts =  postService.listPosts();
        assertThat(listPosts.size()).isGreaterThan(1);
    }

}
