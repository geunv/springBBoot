package com.exam.webservice.springboot.web;

import com.exam.webservice.springboot.config.auth.LoginUser;
import com.exam.webservice.springboot.config.auth.dto.SessionUser;
import com.exam.webservice.springboot.service.posts.PostService;
import com.exam.webservice.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    //private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postService.findAllDesc());

        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        try {
            PostsResponseDto dto = postService.findById(id);

            model.addAttribute("post", dto);
        }catch(Exception e){
            e.getStackTrace();
        }

        return "post-update";
    }
}
