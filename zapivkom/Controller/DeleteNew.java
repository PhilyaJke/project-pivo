package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteNew {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/deleteNew")
    @PreAuthorize("hasAuthority('developers:write')")
    public String deletenew(){
        return "/deleteNew";
    }

    @PostMapping("/deleteNew")
    public void deleteNew(@RequestParam String title){
        postRepository.deleteById(postRepository.findIdByTitle(title));
    }
}
