package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Repo.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//добавить проверку, существует ли пользователь
@Controller
public class SignIn {

    @Autowired
    SignRepository signRepository;

    @GetMapping("/sign/in")
    public String singIn(@AuthenticationPrincipal User user, Model model){
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        return "sign-in";
    }

}
