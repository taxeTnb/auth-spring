package com.example.vol.controllers;


import com.example.vol.models.Proprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.vol.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clts")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/list")
    public List<Proprietaire> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Proprietaire> addClient(@RequestBody Proprietaire c) {
        Proprietaire clt = clientRepository.save(c);
        if (clt == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(clt, HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public Proprietaire updateClient(@RequestBody Proprietaire c) {
        return clientRepository.saveAndFlush(c);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Proprietaire> signInClient(@RequestBody Proprietaire client) {
        Proprietaire result = clientRepository.findByLoginCltAndPassword(client.getLoginClt(), client.getPassword());
        if (result == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Integer id) {
        clientRepository.deleteById(id);
    }

    @GetMapping("/id/{id}")
    public Optional<Proprietaire> findById(@PathVariable Integer id) {
        return clientRepository.findById(id);
    }

    
    
}
