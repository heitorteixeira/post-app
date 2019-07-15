package br.com.heitor.postapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testListSuccess() throws Exception {
        String url = "/posts";
        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("My first post")))
                .andExpect(content().string(containsString("My second post")))
                .andExpect(content().string(containsString("My third post")));
    }

    @Test
    public void testInsertSuccess() throws Exception {
        String url = "/posts";
        this.mockMvc.perform(post(url)
                .content("{\"title\": \"Testing posts\", \"description\": \"we type the description here\", \"upvotes\": 7}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("http://localhost/posts/4")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testInsertFail() throws Exception {
        String url = "/posts";
        this.mockMvc.perform(post(url)
                .content("{\"t1tle\": \"Testing posts\", \"description\": \"testing 123\", \"upvotes\": 6}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateUpvotesSuccess() throws Exception {
        String url = "/posts/1";
        this.mockMvc.perform(put(url)
                .content("{\"id\": 1, \"title\": \"My first post 1\", \"description\": \"Updating the first post\", \"upvotes\": 77}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateUpvotesFail() throws Exception {
        String url = "/posts/99";
        this.mockMvc.perform(put(url)
                .content("{\"id\": 99, \"title\": \"Title here\", \"description\": \"here goes the description\", \"upvotes\": 3}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }



}
