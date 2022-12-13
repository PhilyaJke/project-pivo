package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.ArticleEntity;
import com.obshaga.zapivkom.Entity.CommentEntity;
import com.obshaga.zapivkom.Repo.CommentRepository;
import com.obshaga.zapivkom.Repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class blogPages {

    private Long num;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<ArticleEntity> articleEntity = postRepository.findById(id);
        ArrayList<ArticleEntity> list1 = new ArrayList<>();
        articleEntity.ifPresent(list1::add);
//        List<CommentEntity> commentEntity = commentRepository.findAll();
//        List<CommentEntity> commentEntities = new ArrayList<>();
//        for(int i = 0; i < commentEntity.size(); i++){
//            if(commentEntity.get(i).getArticle_entity_id()==id){
//                commentEntities.add(commentEntity.get(i));
//            }
//        }
//        model.addAttribute("comments", commentEntities);
        model.addAttribute("article", list1);
        return "blog-details";
    }

//    @PostMapping("/blog/{id}")
//    public String addComment( @PathVariable(value = "id") long id, Model model) {
//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setText(text);
////        commentEntity.setArticle_entity_id(id);
//        commentRepository.save(commentEntity);
//        return "redirect:/";
//    }
}
