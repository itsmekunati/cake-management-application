package com.cakemgr.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cakemgr.model.Cake;
import com.cakemgr.service.CakeService;

@RestController
public class CakeController {
    @Autowired
    CakeService CakeService;

    @GetMapping("/")
    private String getCakesFromSource() throws IOException {
      CakeService.getCakesFromSource();
      return "success";
    }

    @GetMapping("/cakes")
    private List<Cake> getAllCakes() {
        return CakeService.getAllCakes();
    }

    @GetMapping("/Cake/{id}")
    private Cake getCake(@PathVariable("id") int id) {
        return CakeService.getCakeById(id);
    }

    @PostMapping("/api/cakes")
    private String saveCake(@RequestBody Cake Cake) {
        CakeService.saveOrUpdate(Cake);
        return "Cake is added with id " + Cake.getId();
    }
}
