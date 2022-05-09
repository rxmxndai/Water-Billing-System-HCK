package com.example.waterbillingsystem.Billing;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class ConsumerController {

    static ArrayList<String> provinceList = new ArrayList<String>();

    static {
        provinceList.add("Kathmandu Kshetra");
        provinceList.add("Arun Kshetra");
        provinceList.add("Janakpur Kshetra");
        provinceList.add("Gandak Kshetra");
        provinceList.add("Karnali Kshetra");
        provinceList.add("Kapilavastu Kshetra");
        provinceList.add("Mahakali Kshetra");
    }


    @RequestMapping(value = "/", method=RequestMethod.POST)
    public String viewTraf(ModelMap model, @RequestParam String userName, @RequestParam String password){
        if(userName.equals("admin")&& password.equals("admin")){
            return "redirect:/viewHomePage";
        }
        else if(userName.equals("traffic")&& password.equals("traffic")){
            return "redirect:/viewTraffic";
        }
        else{
            return "homepage";
        }
    }


//    @RequestMapping(value = "/login", method=RequestMethod.POST)
//    public String adminDashboard(Model model, @RequestParam String userName, @RequestParam String password) {
//
//        if (userName.equals("admin") && password.equals("admin")) {
//            List<Consumer> consumerList = service.listAll();
//            model.addAttribute("consumer", new Consumer());
//            return "Dashboard";
//        } else {
//            return "redirect:/admin/login";
//        }
//    }



    @GetMapping("/admin/dash")
    public String showDashboard(Model model) {
        List<Consumer> consumerList = service.listAll();
        model.addAttribute("consumer", new Consumer());
        return "Dashboard";
    }

    @GetMapping("/consumer/dash/{id}")
    public String showConsumerDash(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) {
        try {
            Consumer consumer = service.get(id);
            model.addAttribute("consumer", consumer);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("pageTitle", "Edit Consumer details (ID: " + id + ")");
            return "ConsumerDashboard";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
            return "redirect:/login";
        }
    }


    @PostMapping("/search")
    public String doSearch(@ModelAttribute("keyword") Consumer consumerdata, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {

        if (consumerdata.getId() == null) {
            rr.addFlashAttribute("message", "Search field is empty!");
            return "redirect:/admin/dash";
        } else {
            try {
                Consumer consumer = service.get(consumerdata.getId());
                model.addAttribute("consumer", consumer);
                return "Dashboard";
            } catch (ConsumerNotFoundException e) {
                rr.addFlashAttribute("message", e.getMessage());
                return "redirect:/admin/dash";
            }
        }
    }

    @PostMapping("/total")
    public String doTotal(@ModelAttribute("keyword") Consumer consumerdata, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {
        Consumer consumer = service.get(consumerdata.getId());
        model.addAttribute("consumer", consumer);
        return "billingForm";
    }


    @Autowired
    private ConsumerService service;

    @GetMapping("/consumer/details")
    public String showConsumerList(Model model) {

        List<Consumer> consumerList = service.listAll();
        model.addAttribute("consumerList", consumerList);

        return "ConsumerDetails";
    }

    @GetMapping("/consumer/newform")
    public String showConsumerForm(Model model) {
        model.addAttribute("consumer", new Consumer());
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("pageTitle", "Register Consumer");
        return "consumerForm";
    }


    @PostMapping("/mydetails/save")

    public String saveMyDetails(Consumer consumer, Model model, RedirectAttributes rr) {

        service.save(consumer);

        rr.addFlashAttribute("message", "Details changed Successfully");

        return "redirect:/consumer/dash/" + consumer.getId();
    }


    @GetMapping("/consumer/settings/{id}")
    public String settingsForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) {
        try {
            Consumer consumer = service.get(id);
            model.addAttribute("consumer", consumer);
            model.addAttribute("pageTitle", "Edit Consumer details (ID: " + id + ")");
            return "ConsumerSettings";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
            return "redirect:/consumer/details";
        }
    }


    @PostMapping("/consumer/save")

    public String saveConsumer(Consumer consumer, RedirectAttributes rr) {


        service.save(consumer);

        rr.addFlashAttribute("message", "Consumer details saved Successfully");

        return "redirect:/consumer/details";
    }


    @GetMapping("/consumer/viewIndividualDetail/{id}")
    public String showEditConsumerForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) {
        try {
            Consumer consumer = service.get(id);
            model.addAttribute("consumer", consumer);
            return "ConsumerDetailsIndividual";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
            return "redirect:/consumer/details";
        }

    }

    @GetMapping("/consumer/billing/null")
    public String errorMsg1(RedirectAttributes rr) throws ConsumerNotFoundException {
        rr.addFlashAttribute("message", "Please Search for a consumer before editing bill");
        return "redirect:/admin/dash";
    }

    @GetMapping("/consumer/invoice/null")
    public String errorMsg2(RedirectAttributes rr) throws ConsumerNotFoundException {
        rr.addFlashAttribute("message", "Please Search for a consumer before checking invoice");
        return "redirect:/admin/dash";
    }


    @GetMapping("/consumer/billing/{id}")
    public String showEditBillinghForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) {
        try {
            Consumer consumer = service.get(id);
            model.addAttribute("consumer", consumer);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("pageTitle", "Edit Consumer details (ID: " + id + ")");
            return "billingForm";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
            return "redirect:/consumer/details";
        }


    }


    @GetMapping("/consumer/remove/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes rr) {

        try {
            service.delete(id);
            rr.addFlashAttribute("message", "User id: " + id + " Successfully Deleted");
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/consumer/details";
    }


    @GetMapping("/consumer/billingdetails")
    public String showBill(Model model, RedirectAttributes rr) {
        List<Consumer> consumerList = service.listAll();
        model.addAttribute("consumerList", consumerList);

        return "BillingDetails";
    }


    @GetMapping("/consumer/invoice/{id}")
    public String showInvoice(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {

        try {
            Consumer consumer = service.get(id);
            model.addAttribute("consumer", consumer);
            return "innvoicevoice";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
            return "redirect:/consumer/printbill";
        }
    }

    @GetMapping("/consumer/myinvoice/{id}")
    public String showMyInvoice(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {

        try {
            Consumer consumer = service.get(id);
            model.addAttribute("consumer", consumer);
            return "singleConsumerInvoice";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", "Your login do not match");
            return "redirect:/login";
        }
    }


    @GetMapping("/")
    public String error404() {
        return "hello";
    }


    @GetMapping("/admin/login")
    public String adminLogin() {
        return "loginpage";
    }

    @GetMapping("/consumer/welcome")
    public String welcome() {
        return "Welcome";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }





}

