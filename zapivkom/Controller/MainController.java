package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.ArticleEntity;
import com.obshaga.zapivkom.Entity.CommentEntity;
import com.obshaga.zapivkom.Entity.SelectionsEntity;
import com.obshaga.zapivkom.Repo.CommentRepository;
import com.obshaga.zapivkom.Repo.PostRepository;
import com.obshaga.zapivkom.Repo.SelectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SelectionsRepository selectionsRepository;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Iterable<ArticleEntity>iterable = postRepository.getAll();
        Iterable<SelectionsEntity>iterable1 = selectionsRepository.findAll();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        switch (dayOfWeek.name()){
            case "MONDAY":
                model.addAttribute("day","4 дня до пятницы!");
                break;
            case "TUESDAY":
                model.addAttribute("day","3 дня до пятницы!");
                break;
            case "WEDNESDAY":
                model.addAttribute("day","2 дня до пятницы!");
                break;
            case "THURSDAY":
                model.addAttribute("day","1 день до пятницы!");
                break;
            case "FRIDAY":
                model.addAttribute("day","Время пить пиао!!!");
                break;
            case "SATURDAY":
                model.addAttribute("day","6 дней до пятницы!");
                break;
            case "SUNDAY":
                model.addAttribute("day","5 дней до пятницы!");
                break;
        }
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        model.addAttribute("articles", iterable);
        model.addAttribute("selections", iterable1);
        return "index";
    }

}
