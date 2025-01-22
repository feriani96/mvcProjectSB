package com.example.demomvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demomvc.model.Produit;
import com.example.demomvc.repository.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit findById(Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElse(null);
    }

    public void delete(Long id) {
        produitRepository.deleteById(id);
    }

    public List<Produit> filterProduits(String libelle, double prixMin, double prixMax) {
        return produitRepository.filterProduits(libelle, prixMin, prixMax);
    }
}
