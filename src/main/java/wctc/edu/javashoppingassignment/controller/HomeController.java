package wctc.edu.javashoppingassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wctc.edu.javashoppingassignment.entity.Item;
import wctc.edu.javashoppingassignment.service.ItemService;

import javax.validation.Valid;

@Controller
public class HomeController {
    private ItemService bsi;

    @Autowired
    public HomeController (ItemService b){this.bsi = b;}

    @RequestMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("bads", bsi.getItemList());
        return "index";
    }

    @RequestMapping("/items/{item}")
    public String showItemPage(@PathVariable String item, Model model){
        String amt = "";
        model.addAttribute("pageTitle", "Buy "+item);
        model.addAttribute("item", bsi.getItem(item));
        model.addAttribute("amt", amt);
        return "item";
    }

    @PostMapping("/buy-item")
    public String buyItem(Model model, @RequestParam int id, @RequestParam String amt){
        Item i = bsi.getItem(id);
        int preAmt = i.getInventory();
        int am = 0;
        boolean b = false;
        try{am = Integer.parseInt(amt);} catch (Exception ex){
            System.out.println(ex.getMessage());
            b = true;
        }
        if(b){
            String cl = "red";
            String ms = "You did not input a positive number. Please use a number next time";
            model.addAttribute("class",cl);
            model.addAttribute("message", ms);
            return "return";
        }
        bsi.savePurchase(i.getId(), am);
        String cl = preAmt == i.getInventory() ? "red" : "green";
        String ms = preAmt == i.getInventory() ? "Your order was not processed, most likely due to trying to take buy more than we have or buying nothing." : "Your order has been processed. Enjoy your bad!";
        model.addAttribute("class",cl);
        model.addAttribute("message", ms);
        return "return";
    }
}
