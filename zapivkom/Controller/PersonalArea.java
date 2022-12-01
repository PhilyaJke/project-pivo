package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.SpecificBeer;
import com.obshaga.zapivkom.Repo.SpecificBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class PersonalArea {

    @GetMapping("/personal-area")
    @PreAuthorize("hasAuthority('developers:write')")
    public String PersonalArea() {
        return "personal-area";
    }
}

