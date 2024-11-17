package org.example.product_microservice.services;

import org.example.product_microservice.entities.Product;
import org.example.product_microservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // Injection du repository

    // Retourne tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retourne un produit par son ID
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null); // Retourne null si le produit n'existe pas
    }

    // Crée un nouveau produit
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Met à jour un produit existant
    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id); // Assurez-vous que l'ID du produit est mis à jour
            return productRepository.save(product);
        }
        return null; // Retourne null si le produit n'existe pas
    }

    // Supprime un produit par son ID
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true; // Retourne vrai si la suppression réussit
        }
        return false; // Retourne faux si le produit n'existe pas
    }
}
