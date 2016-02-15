package main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.service.OkapiService;

import java.util.Collection;
import java.util.List;

/**
 * PackALunch
 * Created by Sadra on 1/18/16.
 */
@RestController

public class OkapiController {

 
    private OkapiService okapiService;
    
    public OkapiController () {
    	okapiService = new OkapiService();
    }



    @RequestMapping(value = "/database", method= RequestMethod.GET)
    public ResponseEntity<String> getDataBase() {
        String response = okapiService.listDatabase();
        return new ResponseEntity<> (response, HttpStatus.OK);

    }

    @RequestMapping(value = "/database", method=RequestMethod.POST)
    public ResponseEntity<String> setDatabase(@RequestParam(value="dbname") String name) {
        System.out.println("in create " + name);
        String response = okapiService.setDatabase(name);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @RequestMapping(value = "/parseterms", method=RequestMethod.POST)
    public ResponseEntity<String> parseTerms(@RequestParam(value="parseTerms") String parseTerms) {
        String response = okapiService.parseTerms(parseTerms);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/stemterm",  method=RequestMethod.POST)
    public ResponseEntity<String> stemTerm(@RequestParam(value="stemTerm") String stemTerm) {
        String response = okapiService.stemTerm(stemTerm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchterm", method=RequestMethod.POST)
    public ResponseEntity<String> searchTerm(@RequestParam(value="searchTerm") String searchTerm) {
        String response = okapiService.searchTerm(searchTerm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getweigh", method=RequestMethod.POST)
    public ResponseEntity<String> getWeigh(@RequestParam(value="term") String term,
                                           @RequestParam(value="searchResult") String searchResult) {
        String response = okapiService.getWeigh(term, searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/result", method= RequestMethod.GET)
    public ResponseEntity<String> displayResultSet() {
        String response = okapiService.displayResultSet();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
