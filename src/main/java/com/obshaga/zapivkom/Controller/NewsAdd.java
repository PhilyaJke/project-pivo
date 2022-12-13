package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.ArticleEntity;
import com.obshaga.zapivkom.Repo.PostRepository;
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
public class NewsAdd {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/add-news")
    @PreAuthorize("hasAuthority('developers:write')")
    public String PersonalArea() {
        return "add-news";
    }

    @PostMapping("/add-news")
    public String PostAdd(@RequestParam String title, @RequestParam String anons,
                          @RequestParam String full_text, @RequestParam("file") MultipartFile file, Model model) throws IOException {

        ArticleEntity article = new ArticleEntity();

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadDir + "/" + resultFileName));

            article.setFilename(resultFileName);

        }


        article.setTitle(title);
        article.setAnons(anons);
        article.setFull_text(full_text);
        postRepository.save(article);
        return "redirect:/";
    }
}
