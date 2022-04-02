package main;

import main.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private ListService listService;

    @GetMapping("/")
    public String index(Model model){
        List<DealDto> deals = listService.findAll();
        model.addAttribute("deals", deals);
        model.addAttribute("dealsCount", deals.size());
        return "index";
    }
}
