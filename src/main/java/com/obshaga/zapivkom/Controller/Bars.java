package com.obshaga.zapivkom.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Bars {

    @GetMapping("/bars")
    public String BarsPage(Model model){
        return "/bars";
    }
}
