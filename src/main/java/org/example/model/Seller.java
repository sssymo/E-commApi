package org.example.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import org.example.model.Utente;

@Entity
public class Seller extends Utente{

    private String storeName;
    private String password;


    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
    private List<Product> products;
  
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
