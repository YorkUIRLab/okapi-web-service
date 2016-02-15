package main.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sadra
 * Created by Sadra on 1/19/16.
 */
public class Utils {

    public String findNP(String toSplit, String queryTerm) //THIS METHOD REMOVES THE EXCESS OUTPUT GENERATED BY OKAPI AND RETURNS ONLY THE VALUE OF NP
    {
        //--------------THIS CODE REMOVES THE "SO np=" PART GENERATED BY OKAPI
        //Create a pattern to match cat
        Pattern p = Pattern.compile("S\\d np=");
        // Create a matcher with an input string
        Matcher m = p.matcher(toSplit);
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        // Loop through and create a new String
        // with the replacements
        while (result) {
            m.appendReplacement(sb, "");
            result = m.find();
        }
        // Add the last segment of input to
        // the new String
        m.appendTail(sb);
        //System.out.println(sb.toString());
        String secondSplit = sb.toString();

        //--------------THIS CODE REMOVES THE "t=" PART GENERATED BY OKAPI
        Pattern p2 = Pattern.compile("t=");
        // Create a matcher with an input string
        Matcher m2 = p2.matcher(secondSplit);
        StringBuffer sb2 = new StringBuffer();
        boolean result2 = m2.find();
        // Loop through and create a new String
        // with the replacements
        while (result2) {
            m2.appendReplacement(sb2, "");
            result2 = m2.find();
        }
        // Add the last segment of input to
        // the new String
        m2.appendTail(sb2);
        String thirdSplit = sb2.toString();

        //--------------THIS CODE REMOVES THE "term searched" GENERATED BY OKAPI
        Pattern p3 = Pattern.compile(queryTerm);
        // Create a matcher with an input string
        Matcher m3 = p3.matcher(thirdSplit);
        StringBuffer sb3 = new StringBuffer();
        boolean result3 = m3.find();
        // Loop through and create a new String
        // with the replacements
        while (result3) {
            m3.appendReplacement(sb3, "");
            result3 = m3.find();
        }
        // Add the last segment of input to
        // the new String
        m3.appendTail(sb3);
        String finalResult = sb3.toString();
        finalResult = finalResult.trim();
        return finalResult;

    }
}