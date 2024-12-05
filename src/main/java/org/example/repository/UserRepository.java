package org.example.repository;

import org.example.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utente,Long> {
    Optional<Utente> findByUsername(String Username);
}
