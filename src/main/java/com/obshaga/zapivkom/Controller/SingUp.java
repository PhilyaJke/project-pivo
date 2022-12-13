package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.Rank;
import com.obshaga.zapivkom.Entity.Roles;
import com.obshaga.zapivkom.Entity.Status;
import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Repo.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;

//добавить проверку, существует ли пользователь
@Controller
public class SingUp{

    private static final String PASSWORD_REGEX =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_REGEX);

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    SignRepository signRepository;

    @GetMapping("/sign/up")
    public String singIn(@AuthenticationPrincipal User user, Model model){
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        return "sign-up";
    }

    @PostMapping("/sign/up")
    public String singIn(@RequestParam String userName, @RequestParam String password, Model model){
        if(password.isEmpty() || userName.isEmpty()){
            return "redirect:/sign/up";
        }
        if(signRepository.findByUsernameAndPassword(userName,password)!=null){
            return "redirect:/sign/up";
        }
        if (PASSWORD_PATTERN.matcher(password).matches()) {
            System.out.print("The Password " + password + " is valid");
        } else {
            return "redirect:/sign/up";
        }
        UsersEntity users = new UsersEntity();
        users.setUsername(userName);
        users.setPassword(passwordEncoder().encode(password));
        users.setRoles(Roles.USER);
        users.setStatus(Status.ACTIVE);
        users.setRank(Rank.Junior);
        signRepository.save(users);
        return "redirect:/sign/in";
    }

}
