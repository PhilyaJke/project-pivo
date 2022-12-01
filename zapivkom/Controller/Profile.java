package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.CommentEntity;
import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Models.UserProfileInfo;
import com.obshaga.zapivkom.Repo.CommentRepository;
import com.obshaga.zapivkom.Repo.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Transactional
@Controller
public class Profile {

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PreAuthorize("hasAuthority('developers:read')")
    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model){
        Optional<UsersEntity> usersEntity = signRepository.findByUsername(user.getUsername());
        UsersEntity userE = usersEntity.get();
        List<CommentEntity> commentEntityList = commentRepository.findByUsername(userE.getId());
        UserProfileInfo userProfileInfo = new UserProfileInfo(userE.getUsername(), userE.getData(), userE.getRank(), (long)(commentEntityList.size()));
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }
        model.addAttribute("userProfileInfo", userProfileInfo);
        return "profile";
    }

    @PostMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, @RequestParam String password){
        Optional<UsersEntity> usersEntity = signRepository.findByUsername(user.getUsername());
        UsersEntity userE = usersEntity.get();
        userE.setPassword(passwordEncoder().encode(password));
        return "redirect:/";
    }
}
