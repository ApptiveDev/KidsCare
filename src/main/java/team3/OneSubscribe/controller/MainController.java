package team3.OneSubscribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.domain.WritingContent;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/contents")
    public String contents(Model model){
        return "posts";
    }

    @PostMapping("/contents/writing/new")
    public String contentsFromWriting(WritingForm form){
        Writing writing = new Writing();

        return "redirect:/contents";
    }

    @GetMapping("/contents/writing")
    public String writing(){
        return "write";
    }

}
