package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Models.AllStatistic;
import com.obshaga.zapivkom.Repo.CommentRepository;
import com.obshaga.zapivkom.Repo.SignRepository;
import com.obshaga.zapivkom.Repo.SpecificBeerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class Statistic {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private final Date FOUNDING_DATE = format.parse("2022-9-14");

    @Autowired
    private SignRepository signRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private SpecificBeerRepository specificBeerRepository;

    public Statistic() throws ParseException {
    }

    @GetMapping("/statistics")
    public String StatisticPage(Model model) throws ParseException {
        Date NowData = format.parse(String.valueOf(LocalDateTime.now()));
        long delt = FOUNDING_DATE.getTime() - NowData.getTime();
        String result = String.format(String.valueOf(delt / 86400000));
        result = result.replace("-","");

        AllStatistic allStatistic = new AllStatistic(commentRepository.findAll().size(), signRepository.findAll().size(),
                specificBeerRepository.findAll().size(), result);
        model.addAttribute("statistic", allStatistic);
        return "statistic";
    }
}
