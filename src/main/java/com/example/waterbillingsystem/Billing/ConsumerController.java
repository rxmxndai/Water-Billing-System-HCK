package com.example.waterbillingsystem.Billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ConsumerController {

    static ArrayList<String> provinceList = new ArrayList<String>();
    static  {
        provinceList.add("Kathmandu Kshetra");
        provinceList.add("Arun Kshetra");
        provinceList.add("Janakpur Kshetra");
        provinceList.add("Gandak Kshetra");
        provinceList.add("Karnali Kshetra");
        provinceList.add("Kapilavastu Kshetra");
        provinceList.add("Mahakali Kshetra");
    }

    @GetMapping("/admin/dash")
    public String showDashboard(Model model) {
        List<Consumer> consumerList = service.listAll();
        model.addAttribute("consumer", new Consumer());
        return "Dashboard";
    }

    @PostMapping("/search")
    public String doSearch(@ModelAttribute("keyword") Consumer consumerdata, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {
        try {
            Consumer consumer = service.get(consumerdata.getId());
            model.addAttribute("consumer", consumer);
            return "Dashboard";
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/dash";
        }
    }

    @PostMapping("/total")
    public String doTotal(@ModelAttribute("keyword") Consumer consumerdata, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {
            Consumer consumer = service.get(consumerdata.getId());
            model.addAttribute("consumer", consumer);
            return "billingForm";
    }





    @Autowired private ConsumerService service;

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

    @PostMapping("/consumer/save")

    public String saveConsumer (Consumer consumer, RedirectAttributes rr) {


        service.save(consumer);

        rr.addFlashAttribute("message", "Consumer details saved Successfully");

        return  "redirect:/consumer/details";
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


    @GetMapping("/consumer/billing/{id}")
    public String showEditBillinghForm(@PathVariable("id") Integer id, Model model, RedirectAttributes rr) {
        if (id == null) {
            rr.addFlashAttribute("message", "Please Search for a consumer first");
            return "Dashboard";
        }
        else {
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

    }


    @GetMapping("/consumer/remove/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes rr) {

        try {
            service.delete(id);
            rr.addFlashAttribute("message", "User id: " +  id + " Successfully Deleted");
        } catch (ConsumerNotFoundException e) {
            rr.addFlashAttribute("message", e.getMessage());
        }

        return  "redirect:/consumer/details";
    }


    @GetMapping("/consumer/billingdetails")
    public String showBill( Model model, RedirectAttributes rr) {
        List<Consumer> consumerList = service.listAll();
        model.addAttribute("consumerList", consumerList);

        return "BillingDetails";
    }

    @GetMapping("/consumer/billing/null")
    public String errorMsg1( RedirectAttributes rr) throws ConsumerNotFoundException {
            rr.addFlashAttribute("message", "Please Search for a consumer before editing bill");
            return "redirect:/admin/dash";
    }
    @GetMapping("/consumer/invoice/null")
    public String errorMsg2( RedirectAttributes rr) throws ConsumerNotFoundException {
        rr.addFlashAttribute("message", "Please Search for a consumer before checking invoice");
        return "redirect:/admin/dash";
    }

    @GetMapping("/consumer/invoice/{id}")
    public String showInvoice( @PathVariable("id") Integer id, Model model, RedirectAttributes rr) throws ConsumerNotFoundException {

        if (Integer.toString(id) == "null") {
            rr.addFlashAttribute("message", "Please Search for a consumer first");
            return "Dashboard";
        }
        else {
            try {
                Consumer consumer = service.get(id);
                model.addAttribute("consumer", consumer);
                return "Invoice";
            } catch (ConsumerNotFoundException e) {
                rr.addFlashAttribute("message", e.getMessage());
                return "redirect:/consumer/printbill";
            }
        }


    }
}
