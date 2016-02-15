package main.model;

/**
 * Sadra
 * Created by Sadra on 1/19/16.
 */
public interface OkapiInterface {

    String listDatabase ();
    String setDatabase (String dbName);
    String parseTerms (String parseTerms);
    String stemTerm (String term);
    String searchTerm (String term);
    String getWeigh (String term, String searchResult);
    String displayResultSet ();
}

