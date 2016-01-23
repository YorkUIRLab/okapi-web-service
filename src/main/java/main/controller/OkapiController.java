package main.controller;

import main.service.OkapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * PackALunch
 * Created by Sadra on 1/18/16.
 */
@RestController

public class OkapiController {

    @Autowired
    private OkapiService okapiService;

    @RequestMapping(value = "/database", method= RequestMethod.GET)
    public String getDataBase() {
        return "not implemented yet.";
    }

    @RequestMapping(value = "/database",
            method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setDatabase(@RequestParam(value="dbname") String name) {
        System.out.println("in create" + name);
        String response = okapiService.setDatabase(name);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }
}
