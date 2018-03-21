/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.model.SuperPower;
import com.sg.supersighting.service.SSServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author janie
 */
//js calls across domains, crossorigin makes it acceptable
@CrossOrigin
@RestController
public class SuperPowerController {
    
    private SSServiceLayer layer;
    
    @Inject
    public SuperPowerController(SSServiceLayer layer) {
        this.layer=layer;
    }
    
    @PostMapping("/SuperPower")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSuperPower(@Valid SuperPower superPower) {
        layer.addSuperPower(superPower);
    }
    
    @GetMapping("/SuperPower{id}")
    //has to be boxed version in case incoming value is null. primitive types will blow up if null
    public SuperPower getSuperPowerByID(@PathVariable("id") Integer superPowerId) {
        return layer.getSuperPowerByID(superPowerId);
    }
    
    @PutMapping("/SuperPower/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuperPower(@PathVariable("id") Integer id, @Valid SuperPower superPower) throws Exception {
        if (id != superPower.getSuperPowerID()) {
            throw new Exception("There is no existing SuperPower");
        }
        
        layer.updateSuperPower(superPower); 
    }
    
    @DeleteMapping("/SuperPower/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuperPower(@PathVariable("id")int superPowerID){
        layer.deleteSuperPower(superPowerID);
    }
    
    @GetMapping("/SuperPowers")
    public List<SuperPower> getAllSuperPowers() {
        return layer.getAllSuperPowers();
    }
    
    
}
