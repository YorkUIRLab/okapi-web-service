package main.service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sadra
 * Created by Sadra on 1/19/16.
 */
public class Utils {

    /**
     * Given a valid command runnable on a Unix shell and the current working Unix directory,
     * runs the command as a new thread.
     *
     * @param cmd represents the Unix command
     * @param dir represents the directory where this command should be executed
     * @return the output generated by the Unix shell
     */
    public String runCommand(String cmd, String dir) {
        String output ="";
        try{
            Process proc = Runtime.getRuntime().exec(cmd, null, new File(dir));
            InputStream inputStream = proc.getInputStream();
            InputStream errorStream = proc.getErrorStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            InputStreamReader errorStreamReader = new InputStreamReader(errorStream);

            BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
            BufferedReader errorBufferedReader = new BufferedReader(errorStreamReader);

            // read the output

            String line;
            while ((line = inputBufferedReader.readLine()) != null) {
                output = output + line+ "\n";
            }
            while ((line = errorBufferedReader.readLine()) != null) {
                output = output + line + "\n";
            }

            // check for command failure

            try {
                if (proc.waitFor() != 0) {
                    System.err.println("exit value = " + proc.exitValue());
                }
            }
            catch (InterruptedException e) {
                System.err.println(e);
            }
            System.out.println(output);
        }catch(IOException e){
            System.err.println(e);
        }
        return output;
    }

    /**
     * Given the name of the Java program responsible for generating exchange format of the given corpus,
     * compiles the Java program and generates the exchange format using Piccolo XML parser named output into the parse directory.
     *
     * @param parser name of the Java parser program without extension
     * @param corpus name of the data collection
     * @param output name of the parsed output without extension i.e. no ".exch"
     * @return the output generated by the Unix shell
     */
    public String exchangeFormat(String parser, String corpus, String output){
        String parsePath = System.getenv("OKAPI_PARSE");
        String out = "";
        out = out + runCommand("javac "+ parser +".java", parsePath);
        out = out + runCommand("rm -rf " + output +".exch ge2sent1.exch", parsePath);
        out = out + runCommand("java -cp .:Piccolo.jar " + parser + " " + corpus +  " " + output, parsePath);
        return out;
    }
    /**
     * Given the name of main parameter file responsible for generating runtime format,
     * converts the already parsed data and generates the runtime format into the bibfiles directory.
     *
     * @param param name of the main parameter file
     * @param parsed name of the parsed data with no extension i.e. no ".exch"
     * @return the output generated by the Unix shell and Okapi
     */

    public void runtimeFormat(String param, String parsed){
        String paramPath= System.getenv("BSS_PARMPATH")+"";
        String parsePath = System.getenv("OKAPI_PARSE");
        String binPath= System.getenv("OKAPI_BINDIR")+"";
        System.out.println(binPath);
        //"./convert_runtime -c " + paramPath + "/ " + param + " < " + parsePath + "/" + parsed+".exch";
        runCommand("sh runtime.sh 2004gendoc gen_exch", binPath);
    }
    /**
     * Given the name of the main parameter file responsible for generating index of the
     * runtime format, converts the already parsed data and generates the runtime format into the bibfiles directory.
     *
     * @param param name of the main parameter file
     * @param mem the unit of memory used to create the database, the unit is 1 MB, default 4
     * @param delfinal deletes/doesn't delete the temporary files after final merge.
     * @param doclens contributes/doesn't contribute to the doclength file
     * @param indexNumber Set in param.search_groups file in databases directory. Must be within the range in the database parameter.
     * @return the output generated by the Unix shell and Okapi
     */

    public void createIndex(String param, long mem, boolean delfinal, boolean doclens, long indexNumber){
        String binPath= System.getenv("OKAPI_BINDIR")+"";
        if (delfinal && doclens)
            runCommand("sh create_index.sh " + mem +" delfinal doclens " +param + " " + indexNumber, binPath);
        else if (delfinal)
            runCommand("sh create_index.sh " + mem +" delfinal nodoclens " +param + " " + indexNumber, binPath);
        else if (doclens)
            runCommand("sh create_index.sh " + mem +" nodelfinal doclens " +param + " " + indexNumber, binPath);
        else
            runCommand("sh create_index.sh " + mem +" nodelfinal nodoclens " +param + " " + indexNumber, binPath);
    }

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