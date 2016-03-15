package main.service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by sonic on 3/13/16.
 */
public class OkapiParser {

//    <DOC>
//    <DOCNO>WT01-B01-1</DOCNO>
//    <DOCOLDNO>IA073-000475-B029-48</DOCOLDNO>
//    <DOCHDR>
//    http://www.city.geneva.ny.us:80/index.htm 192.108.245.124 19970121041510 text/html 2407
//    HTTP/1.0 200 OK
//    Date: Tue, 21 Jan 1997 04:14:08 GMT
//    Server: Apache/1.1.1
//    Content-type: text/html
//    Content-length: 2236
//    Last-modified: Fri, 18 Oct 1996 17:33:56 GMT
//    </DOCHDR>
//    <html>
//    <head>
//    <META name="keywords" content="waste water, biosolids, waste treatment, compost, waste stream, waste water treatment, water pollution,seneca, geneva, marsh creek">
//
//    <title>Marsh Creek Waste Water Treatment Plant</title>
//    </head>
//    <body>
//      CONTENT
//    </bod>
//    </html>
//    </DOC>
    public static void main(String[] args) throws Exception {


        //getFileList();

        //parseTrecDataset();

        parseTrecTopic ();


    }

    private static ArrayList<String> getFileList() {
        ArrayList<String> fileList = new ArrayList<String>();

        File[] files = new File("/home/sonic/TREC/WT2G/dataset/WT01").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file.getAbsolutePath());
            }

        }
        return fileList;
    }


    private static void parseTrecDataset() throws IOException {
        InputStream in = new FileInputStream(new File("/home/sonic/TREC/WT2G/dataset/WT01/B01"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        StringBuilder html = new StringBuilder();
        String line;
        boolean isHTML = false;
        while ((line = reader.readLine()) != null) {

            if (line.contains("<DOCNO>")) {
                out.append(line.replace("<DOCNO>", "").replace("</DOCNO>", ""));
            }

            if ( line.contains("</DOCHDR>") ) {
                isHTML = true;
            }

            if (line.contains("</DOC>") ) {
                isHTML = false;
                //System.out.println(html.toString());
                out.append(html.toString()).append("\n");

                html.setLength(0);
            }

            if (isHTML && !line.contains("</DOCHDR>") && !line.contains("</DOC>") )
                html.append(line);

        }

        System.out.println(out.toString());   //Prints the string content read from input stream
        reader.close();
    }

//    <top>
//    <num> Number: 401
//    <title> foreign minorities, Germany
//    <desc> Description:
//    What language and cultural differences impede the integration
//    of foreign minorities in Germany?
//    <narr> Narrative:
//    TEXT
//    </top>
    private static void parseTrecTopic() throws IOException {
        InputStream in = new FileInputStream(new File("/home/sonic/TREC/WT2G/topics/topics.wt2g"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        StringBuilder html = new StringBuilder();
        String line;
        boolean isDescription = false;
        boolean isNarrative = false;
        while ((line = reader.readLine()) != null) {

            if (line.contains("<num>")) {
                out.append(line.replace("<num>", ""));
            }

            if (line.contains("<title>")) {
                out.append(line.replace("<title>", ""));
            }

            if (line.contains("<desc>")) {
                isDescription = true;
            }

            if ( line.contains("<narr>") ) {
                isDescription = false;
                isNarrative = true;
            }

            if ( line.contains("</top>")) {
                isNarrative = false;
                out.append("\n");
            }

            if (isDescription || isNarrative ) {
                //System.out.println(html.toString());
                out.append(line);
            }
        }

        System.out.println(out.toString());   //Prints the string content read from input stream
        reader.close();
    }
}
