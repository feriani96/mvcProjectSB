package com.example.demomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demomvc.model.Produit;
import com.example.demomvc.service.ProduitService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/ajouter")
    public String showAddProduitForm(Model model) {
        model.addAttribute("produit", new Produit());
        return "ajouter-produit";
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
    public String updateProduit(@PathVariable Long id,
                                @RequestParam String libelle,
                                @RequestParam Double prix,
                                @RequestParam Integer qteStock) {
        Produit produit = productService.findById(id);
        if (produit != null) {
            produit.setLibelle(libelle);
            produit.setPrix(prix);
            produit.setQteStock(qteStock);
            productService.save(produit);
        }
        isEditing.remove(id);
        return "redirect:/produits";
    }

    @PostMapping("/cancel-edit/{id}")
    public String cancelEdit(@PathVariable Long id) {
        isEditing.remove(id);
        return "redirect:/produits";
    }

    @GetMapping("/confirm-edit/{id}")
    public String confirmEdit(@PathVariable Long id) {
        isEditing.remove(id);
        return "redirect:/produits";
    }
}
