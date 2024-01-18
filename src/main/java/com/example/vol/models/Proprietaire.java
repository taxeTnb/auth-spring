package com.example.vol.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Proprietaire implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String loginClt;
    private String password;
    private String cinClt;
    private String nom;
    private String prenom;
    private String CIN;
    private int ageClt;
    private String adresseClt;
    private String numTel;
    private String mailClt;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String cIN) {
        CIN = cIN;
    }

    public Proprietaire() {
    }

    public Proprietaire(String loginClt, String passwordClt) {
        this.loginClt = loginClt;
        this.password = passwordClt;
    }

    public Proprietaire(String cinClt, String nomClt, String prenomClt, int ageClt, String adresseClt, String numTel, String mailClt) {
        this.cinClt = cinClt;
        this.nom = nomClt;
        this.prenom = prenomClt;
        this.ageClt = ageClt;
        this.adresseClt = adresseClt;
        this.numTel = numTel;
        this.mailClt = mailClt;
    }

    

    public String getLoginClt() {
        return loginClt;
    }

    public void setLoginClt(String loginClt) {
        this.loginClt = loginClt;
    }

    

    public String getCinClt() {
        return cinClt;
    }

    public void setCinClt(String cinClt) {
        this.cinClt = cinClt;
    }

    

    

    public int getAgeClt() {
        return ageClt;
    }

    public void setAgeClt(int ageClt) {
        this.ageClt = ageClt;
    }

    public String getAdresseClt() {
        return adresseClt;
    }

    public void setAdresseClt(String adresseClt) {
        this.adresseClt = adresseClt;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMailClt() {
        return mailClt;
    }

    public void setMailClt(String mailClt) {
        this.mailClt = mailClt;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClt=" + id +
                ", loginClt='" + loginClt + '\'' +
                ", passwordClt='" + password + '\'' +
                ", cinClt='" + cinClt + '\'' +
                ", nomClt='" + nom + '\'' +
                ", prenomClt='" + prenom + '\'' +
                ", ageClt=" + ageClt +
                ", adresseClt='" + adresseClt + '\'' +
                ", numTel='" + numTel + '\'' +
                ", mailClt='" + mailClt + '\'' +
                '}';
    }
}
