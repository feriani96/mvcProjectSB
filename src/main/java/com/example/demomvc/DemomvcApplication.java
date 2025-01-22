package com.example.demomvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demomvc.model.Produit;
import com.example.demomvc.service.ProduitService;

@SpringBootApplication
public class DemomvcApplication implements CommandLineRunner {

	@Autowired
    private ProduitService produitService;

    public static void main(String[] args) {
        SpringApplication.run(DemomvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Produits ajoutés avec succès");
    }

}
