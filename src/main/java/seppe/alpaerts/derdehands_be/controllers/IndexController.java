package seppe.alpaerts.derdehands_be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import seppe.alpaerts.derdehands_be.model.ProductData;
import seppe.alpaerts.derdehands_be.model.ProductDataDAO;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    ProductDataDAO dao;

    @ModelAttribute(value = "alleProducten")
    public Iterable<ProductData> getAllProducten () {
        return dao.findAll();
    }
    @ModelAttribute(value = "nProduct")
    public ProductData newProduct (){
        return new ProductData();
    }



    @RequestMapping(value = {"","/","/index"}, method = RequestMethod.GET)
    public String showIndex(ModelMap map){
        return "index";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("nProduct")@Valid ProductData nProduct, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        return "new";
        dao.save(nProduct);
        return "redirect:/index";
    }
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String showForm(ModelMap map){
        return "new";
    }
}
