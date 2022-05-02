package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.domain.WritingContent;
import team3.OneSubscribe.repository.WritingRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    WritingRepository writingRepository;

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
        List<Writing> writings = writingRepository.findAll();
        model.addAttribute("writings", writings);
        return "posts";
    }

    @GetMapping("/contents/writing")
    public String writingPage(Model model){
        model.addAttribute("form", new WritingForm());
        return "write";
    }

    @PostMapping("/contents/writing/new")
    public String contentsFromWriting(WritingForm form){
        Writing writing = new Writing();
        writing.setTitle(form.getTitle());
        writing.setContext(form.getContext());

        writingRepository.save(writing);
        return "redirect:/contents";
    }



}
