package com.cakemgr.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cakemgr.model.Cake;
import com.cakemgr.repository.CakeRepository;

//defining the business logic
@Service
public class CakeService {
    @Autowired
    CakeRepository CakeRepository;

    List<Cake> cakesList = new ArrayList<>();
    Random random = new Random();

    //getting all Cake records
    public List<Cake> getAllCakes() {
        List<Cake> Cakes = new ArrayList<>();
        CakeRepository.findAll().forEach(Cakes::add);
        return Cakes;
    }

    public void getCakesFromSource() throws IOException {
        URL url = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json");

        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }

            Gson gson = new Gson();
            Cake[] cakes = gson.fromJson(String.valueOf(json), Cake[].class);

            cakesList.addAll(List.of(cakes));
            for (Cake k : cakesList) {
               k.setId(Math.abs(random.nextInt()));
                CakeRepository.save(k);
            }

        }
    }

    //getting a specific record
    public Cake getCakeById(int id) {
        return CakeRepository.findById(id).get();
    }

    public void saveOrUpdate(Cake Cake) {
        Cake.setId(Math.abs(random.nextInt()));
        CakeRepository.save(Cake);
    }

    //deleting a specific record
    public void delete(int id) {
        CakeRepository.deleteById(id);
    }
}