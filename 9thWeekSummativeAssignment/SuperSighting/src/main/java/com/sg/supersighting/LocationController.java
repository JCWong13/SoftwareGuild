/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.service.SSServiceLayer;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author janie
 */
@CrossOrigin
@RestController//at response body no longer needed 
public class LocationController {
    
    private SSServiceLayer layer;
    
    @Inject
    public LocationController(SSServiceLayer layer){
        this.layer=layer;
    }
    
    
}
