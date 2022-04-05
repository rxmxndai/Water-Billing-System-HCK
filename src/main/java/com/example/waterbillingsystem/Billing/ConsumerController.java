package com.example.waterbillingsystem.Billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ConsumerController {
    @Autowired private ConsumerService service;

    @GetMapping("/consumer/details")
    public String showConsumerList(Model model) {

        List<Consumer> consumerList = service.listAll();
        model.addAttribute("consumerList", consumerList);

        return "ConsumerDetails";
    }

    @GetMapping("/consumer/newform")
    public String showAddConsumerForm(Model model) {
        model.addAttribute("consumer", new Consumer());
        return "consumerForm";
    }

    @PostMapping("/consumer/save")

    public String saveConsumer (Consumer consumer, RedirectAttributes rr) {
        service.save(consumer);

        rr.addFlashAttribute("message", "User details saved Successfully");
        return  "redirect:/consumer/details";
    }
}
