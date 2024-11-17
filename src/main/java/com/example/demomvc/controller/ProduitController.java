package com.example.demomvc.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demomvc.model.Produit;
import com.example.demomvc.service.ProduitService;

@Controller
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService productService;

    private Set<Long> isEditing = new HashSet<>();

    @GetMapping
    public String getAllProducts(Model model) {
        List<Produit> produits = productService.findAll();
        model.addAttribute("produits", produits);
        model.addAttribute("isEditing", isEditing);
        return "index";
    }

    @PostMapping("/add")
    public String addProduit(@ModelAttribute Produit produit) {
        productService.save(produit);
        return "redirect:/produits";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/produits";
    }

    @PostMapping("/edit/{id}")
    public String enterEditMode(@PathVariable Long id) {
        isEditing.add(id);
        return "redirect:/produits";
    }

    @PostMapping("/update/{id}")
    public String updateProduit(@PathVariable Long id, @ModelAttribute Produit produit) {
        Produit existingProduit = productService.findById(id);
        if (existingProduit != null) {
            existingProduit.setLibelle(produit.getLibelle());
            existingProduit.setPrix(produit.getPrix());
            existingProduit.setQteStock(produit.getQteStock());
            productService.save(existingProduit);
        }
        isEditing.remove(id);
        return "redirect:/produits";
    }

    @PostMapping("/cancel-edit/{id}")
    public String cancelEdit(@PathVariable Long id) {
        isEditing.remove(id);
        return "redirect:/produits";
    }
}
