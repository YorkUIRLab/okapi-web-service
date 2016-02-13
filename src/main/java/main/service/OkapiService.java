package main.service;


import main.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * PackALunch
 * Created by Sadra on 1/18/16.
 */
@Service
public class OkapiService {

    //TODO: load okapi interface from Okapi package;

    public OkapiService() {

        try{

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public String setDatabase(String dbName) {
        // TODO: call native Okapi to get results
        return "database set to: " + dbName;
    }

    public String parseTerms(String parseTerms) {
        return null;
    }

    public String stemTerm(String term) {
        return null;
    }

    public String searchTerm(String term) {
        return null;
    }

    public String getWeigh(String term, String searchResult) {
        return null;
    }

    public List <ResultDto> displayResultSet() {
        List <ResultDto> output = new ArrayList<>();
        ResultDto resultDto = new ResultDto();
        output.add(resultDto);
        return  output;
    }


    public List <String> listDatabase () {
        List <String> output = new ArrayList<>();
        return  output;
    }


}
