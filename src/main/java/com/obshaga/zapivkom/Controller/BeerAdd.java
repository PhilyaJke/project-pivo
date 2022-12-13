package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.CategoriesEntity;
import com.obshaga.zapivkom.Entity.SpecificBeer;
import com.obshaga.zapivkom.Repo.CategoriesRepository;
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
import java.util.*;

@Controller
public class BeerAdd {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private SpecificBeerRepository specificBeerRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping("/add-beer")
    @PreAuthorize("hasAuthority('developers:write')")
    public String PersonalArea() {
        return "add-beer";
    }

    @PostMapping("/add-beer")
    public String BeerAdd(@RequestParam String nameofthebeer, @RequestParam String shortreview,
                          @RequestParam(required = false) Long idofthebeer, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        Optional<CategoriesEntity> categoriesEntity = categoriesRepository.findById(idofthebeer);
        SpecificBeer specificBeer;
        if(specificBeerRepository.findByNameOfThebeer(nameofthebeer)!=null){
            specificBeer = specificBeerRepository.findByNameOfThebeer(nameofthebeer);
            specificBeer.addCategoriToberr(categoriesEntity.get());
            specificBeerRepository.save(specificBeer);
        }else {
            specificBeer = new SpecificBeer();
            if (file != null) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadDir + "/" + resultFileName));

                specificBeer.setFilename(resultFileName);
            }
            specificBeer.setNameOfThebeer(nameofthebeer);
            specificBeer.setShortReview(shortreview);
            specificBeer.addCategoriToberr(categoriesEntity.get());
            specificBeerRepository.save(specificBeer);
        }
        return "redirect:/add-beer";
    }
}

