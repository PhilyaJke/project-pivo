package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.CommentEntity;
import com.obshaga.zapivkom.Entity.Rank;
import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Models.CommPlusAuthor;
import com.obshaga.zapivkom.Repo.CommentRepository;
import com.obshaga.zapivkom.Repo.SignRepository;
import com.obshaga.zapivkom.Repo.SpecificBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class SpecificBeer {

    @Autowired
    private SpecificBeerRepository specificBeerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SignRepository signRepository;

    @GetMapping("/categories/.../{nameOfThebeer}")
    public String specificBeers(@PathVariable(value = "nameOfThebeer") String nameOfThebeer,
                                @AuthenticationPrincipal User user, Model model) {
        com.obshaga.zapivkom.Entity.SpecificBeer specificBeer = specificBeerRepository.findByNameOfThebeer(nameOfThebeer);
        List<CommentEntity> commentEntityList = commentRepository.findByBeer_id(specificBeerRepository.findByNameOfThebeer(nameOfThebeer).getId());
        List<CommPlusAuthor>list = new ArrayList();
        for(int i = 0; i< commentEntityList.size(); i++) {
            CommPlusAuthor commPlusAuthorList = new CommPlusAuthor(
                    commentEntityList.get(i).getText(), commentEntityList.get(i).getData(), commentEntityList.get(i).getUsersEntity().getUsername(), commentEntityList.get(i).getUsersEntity().getRank()
            );
            list.add(commPlusAuthorList);
        }
        Collections.reverse(list);
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        model.addAttribute("comment", list);
        model.addAttribute("specificbeer", specificBeer);
        model.addAttribute("nameOfThebeer", nameOfThebeer);
        System.out.println(nameOfThebeer);
        return "specificBeer";
    }

    @PostMapping("/categories/.../{nameOfThebeer}")
    public String specificBeers(@PathVariable(value = "nameOfThebeer") String nameOfThebeer,
                              @RequestParam String text, @AuthenticationPrincipal User user){
        if(user == null){
            return "redirect:{nameOfThebeer}#okno3";
        }else {
            Optional<UsersEntity> usersEntity = signRepository.findByUsername(user.getUsername());
            UsersEntity usersEntity1 = usersEntity.get();
            com.obshaga.zapivkom.Entity.SpecificBeer specificBeer = specificBeerRepository.findByNameOfThebeer(nameOfThebeer);
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setText(text);
            commentEntity.setSpecificBeer(specificBeer);
            usersEntity1.addUserToComment(commentEntity);
            signRepository.save(usersEntity1);
            List<CommentEntity> commentEntityList = commentRepository.findByUsername(usersEntity1.getId());
            if ((long) commentEntityList.size() >= 4 & (long) commentEntityList.size() <= 8) {
                usersEntity1.setRank(Rank.Middler);
            }
            if ((long) commentEntityList.size() > 8 & (long) commentEntityList.size() <= 100) {
                usersEntity1.setRank(Rank.Seinor);
            }
            return "redirect:{nameOfThebeer}#okno3";
        }
    }
}
