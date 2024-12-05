package org.example.service;

import org.example.model.Seller;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    public Seller updateSeller(Long id, Seller updatedSeller) {

        if (sellerRepository.existsById(id)) {
            updatedSeller.setId(id);
            return sellerRepository.save(updatedSeller);
        } else {
            return null;
        }
    }

    public boolean deleteSeller(Long id) {
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
