package main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.javokapi;

public class OkapiInterfaceTest {


	public static void main(String[] args) {
		javokapi okapiInterface = new javokapi();
		
		String searchWord = "survive";
		String stemmedSearchWord = okapiInterface.stem("psstem",searchWord); //FINDS THE ROOT OF THE WORD (i.e. 'running' becomes 'run')
		String trimString = stemmedSearchWord.replace( "t=", "");  //Trims the t= produced from the stem() function
		System.out.println("Stremmed: "+ stemmedSearchWord);
		String found = okapiInterface.find("1","0","DEFAULT", stemmedSearchWord); //QUERIES OKAPI WITH THE SEARCH WORD ENTERED BY USER
		System.out.println("Results: "+ found);
		

		
	}

}
