package main.controller;

import main.dto.ResultDto;
import main.service.OkapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * PackALunch
 * Created by Sadra on 1/18/16.
 */
@RestController

public class OkapiController {

    @Autowired
    private OkapiService okapiService;

    @RequestMapping(value = "/database", method= RequestMethod.GET)
    public ResponseEntity<List <String>> getDataBase() {
        List <String> response = okapiService.listDatabase();
        return new ResponseEntity<> (response, HttpStatus.OK);

    }

    @RequestMapping(value = "/database",
            method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setDatabase(@RequestParam(value="dbname") String name) {
        System.out.println("in create " + name);
        String response = okapiService.setDatabase(name);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @RequestMapping(value = "/parseterms",
            method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> parseTerms(@RequestParam(value="parseTerms") String parseTerms) {
        String response = okapiService.parseTerms(parseTerms);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/stemterm",
            method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> stemTerm(@RequestParam(value="stemTerm") String stemTerm) {
        String response = okapiService.stemTerm(stemTerm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchterm",
            method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchTerm(@RequestParam(value="searchTerm") String searchTerm) {
        String response = okapiService.searchTerm(searchTerm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getweigh",
            method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getWeigh(@RequestParam(value="term") String term,
                                           @RequestParam(value="searchResult") String searchResult) {
        String response = okapiService.getWeigh(term, searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/result", method= RequestMethod.GET)
    public ResponseEntity<List <ResultDto>> displayResultSet() {
        List <ResultDto> response = okapiService.displayResultSet();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
