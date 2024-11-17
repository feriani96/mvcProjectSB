package com.example.demomvc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demomvc.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("SELECT p FROM Produit p WHERE p.libelle LIKE :libelle AND p.prix >= :prixMin AND p.prix <= :prixMax")
    List<Produit> filterProduits(
        @Param("libelle") String libelle, 
        @Param("prixMin") double prixMin, 
        @Param("prixMax") double prixMax
    );
}
