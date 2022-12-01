package com.obshaga.zapivkom.Controller;


import com.obshaga.zapivkom.Entity.CategoriesEntity;
import com.obshaga.zapivkom.Entity.SpecificBeer;
import com.obshaga.zapivkom.Repo.CategoriesRepository;
import com.obshaga.zapivkom.Repo.SpecificBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SpecificCategories {

    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    SpecificBeerRepository specificBeerRepository;

    @GetMapping("/categories/{title}")
    public String specificCategori(@AuthenticationPrincipal User user, @PathVariable(value = "title") String title, Model model) {
        CategoriesEntity categoriesEntity = categoriesRepository.findByTitle(title);

//        ЗАМЕНИТЬ НА SET

        List<SpecificBeer>list = categoriesEntity.getSpecificBeerList();
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getId());
        }


        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        model.addAttribute("review", categoriesEntity);
        model.addAttribute("beers", list);
        return "specific-categories";
    }
}
