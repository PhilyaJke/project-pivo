package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.CategoriesEntity;
import com.obshaga.zapivkom.Repo.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Сategories {

    @Autowired
    CategoriesRepository categoriesRepository;

    @GetMapping("/categories")
    public String categories(@AuthenticationPrincipal User user, Model model){

        Iterable<CategoriesEntity> iterable = categoriesRepository.findAll();
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        model.addAttribute("categories", iterable);
        return "categories";
    }

}
