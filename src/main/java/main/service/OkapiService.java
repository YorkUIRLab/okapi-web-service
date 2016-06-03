package main.service;

import java.util.Arrays;
import java.util.List;

import org.javokapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import main.factory.OkapiFactory;



/**
 * Sadra
 * Created by Sadra on 1/18/16.
 */
@Service
public class OkapiService {
	
	javokapi okapiInterface = null;
	Utils okapiUtils = null;

	public OkapiService ()  {
		OkapiFactory okapiFactory = new OkapiFactory ();
		okapiInterface = okapiFactory.getInstance();
		okapiUtils = new Utils();		
	}
	

    public List<String> listDatabase() {
    	//System.out.println("in list Database...");
        String dbList = okapiInterface.infoDB();
        System.out.println(dbList);
        String[] response = dbList.split("name");
        return Arrays.asList(response);
    }


    public String setDatabase(String dbName) {
        //System.out.println("DB name: " + dbName);
        System.out.println(okapiInterface.displayStemFunctions());
        okapiInterface.chooseDB(dbName); //CHOOSE THE DATABASE UESR INPUTTED
        okapiInterface.deleteAllSets(); //CLEAR ALL EXISTING DATASETS IF ANY
        return "database name successfully set to " + dbName;
    }


    public String parseTerms(String parseTerms) {
        String okapiParsedTerms = okapiInterface.superParse("", parseTerms);
        System.out.println("Parsed term(s): " + okapiParsedTerms);  //OUTPUT THE PARSED TERMS
        return "parsed terms: " + okapiParsedTerms;
    }


    public String stemTerm(String term) {
        String stemmedWord = okapiInterface.stem("psstem", term); //FINDS THE ROOT OF THE WORD (i.e. 'running' becomes 'run')
        System.out.println("Stemmed word: " + stemmedWord);
        return stemmedWord.replace("t=", "");  //Trims the t= produced from the stem() function
    }

 
    public String searchTerm(String term) {
        String searchResult = okapiInterface.find("1", "0", "DEFAULT", term); //QUERIES OKAPI WITH THE SEARCH WORD ENTERED BY USER
        System.out.println("search result: " + searchResult);
        return searchResult;
    }


    public String displayResultSet(String searchWord) {
        System.out.println("Your query returned the following results");
        String results = "";

        String stemmedSearchWord=okapiInterface.stem("psstem",searchWord); //FINDS THE ROOT OF THE WORD (i.e. 'running' becomes 'run')
        String trimString = stemmedSearchWord.replace( "t=", "");  //Trims the t= produced from the stem() function
        stemmedSearchWord = trimString;
        System.out.println("Stemmed Search Word: " + stemmedSearchWord);
        String found = null;
        String np = null;
        found = okapiInterface.find("1","0","DEFAULT", stemmedSearchWord); //QUERIES OKAPI WITH THE SEARCH WORD ENTERED BY USER
        System.out.println("Results: "+ found);
        String splitFound = null;

        np = okapiUtils.findNP(found,stemmedSearchWord);  //SEE METHOD findNP() BELOW
        int npInt = Integer.parseInt(np);

        String weight = okapiInterface.weight("2",np,"0","0","4","5");
        System.out.println("weight()= "+ weight);  //OUTPUT THE WEIGHT FOUND BY THE weight() FUNCTION
        System.out.println("setFind()= " + okapiInterface.setFind("0", weight,"")); //OUTPUT THE SET
        System.out.println(okapiInterface.displayStemFunctions());

        //DISPLAYS ALL THE RECORDS FOUND
        System.out.println("\n\nYour query returned the following results");
        //for (int i=0; i<npInt; i++){
        for (int i=0; i<99; i++) {
            //System.out.println("Record #" + i + ": "+okapiInterface.showSetRecord("100", "1", Integer.toString(i),"0"));
            System.out.println("Record #" + i + ": "+okapiInterface.showSetRecord("1", "1", Integer.toString(i),"0"));
            results += "Record #" + i + ": " +okapiInterface.showSetRecord("1", "1", Integer.toString(i),"0") + "\n";
        }

        System.out.println("Record #" + results);
        return results;
    }


    public String getWeigh(String term) {

        String results = "";
        String stemmedSearchWord=okapiInterface.stem("psstem",term); //FINDS THE ROOT OF THE WORD (i.e. 'running' becomes 'run')
        String trimString = stemmedSearchWord.replace( "t=", "");  //Trims the t= produced from the stem() function
        stemmedSearchWord = trimString;
        System.out.println("Stemmed Search Word: " + stemmedSearchWord);
        String found = null;
        String np = null;
        found = okapiInterface.find("1","0","DEFAULT", stemmedSearchWord); //QUERIES OKAPI WITH THE SEARCH WORD ENTERED BY USER
        System.out.println("Results: "+ found);
        String splitFound = null;

        np = okapiUtils.findNP(found,stemmedSearchWord);  //SEE METHOD findNP() BELOW
        int npInt = Integer.parseInt(np);

        String weight = okapiInterface.weight("2",np,"0","0","4","5");
        System.out.println("weight()= "+ weight);  //OUTPUT THE WEIGHT FOUND BY THE weight() FUNCTION
        System.out.println("setFind()= " + okapiInterface.setFind("0", weight,"")); //OUTPUT THE SET
        System.out.println(okapiInterface.displayStemFunctions());
        return weight;
    }



}
