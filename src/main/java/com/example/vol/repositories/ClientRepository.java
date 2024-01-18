package com.example.vol.repositories;

import com.example.vol.models.Proprietaire;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional()
public interface ClientRepository extends JpaRepository<Proprietaire, Integer> {
    Proprietaire findByLoginCltAndPassword(String login_clt, String password_clt);
    Proprietaire findById(int id);
}