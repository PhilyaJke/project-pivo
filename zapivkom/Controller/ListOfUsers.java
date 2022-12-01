package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.Roles;
import com.obshaga.zapivkom.Entity.Status;
import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Repo.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ListOfUsers {

    @Autowired
    private SignRepository signRepository;

    @GetMapping("/listOfUsers")
    @PreAuthorize("hasAuthority('developers:write')")
    public String users(Model model){
       List<UsersEntity>users =  signRepository.findAll();
       model.addAttribute("user", users);
       return "listOfUsers";
    }

    @PostMapping("/listOfUsers")
    public String users(@RequestParam Long id, @RequestParam String username, @RequestParam Status status){
        Optional<UsersEntity> usersEntityOptional = signRepository.findById(id);
        UsersEntity usersEntity = usersEntityOptional.get();
        usersEntity.setUsername(username);
        usersEntity.setStatus(status);
        signRepository.save(usersEntity);
        return "redirect:/listOfUsers";
    }
}
