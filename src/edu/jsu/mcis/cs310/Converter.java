package edu.jsu.mcis.cs310;

import java.io.*;
import java.util.*;
import com.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    
    /*
        
        Consider the following CSV data:
        
        "ID","Total","Assignment 1","Assignment 2","Exam 1"
        "111278","611","146","128","337"
        "111352","867","227","228","412"
        "111373","461","96","90","275"
        "111305","835","220","217","398"
        "111399","898","226","229","443"
        "111160","454","77","125","252"
        "111276","579","130","111","338"
        "111241","973","236","237","500"
        
        The corresponding JSON data would be similar to the following (tabs and
        other whitespace have been added for clarity).  Note the curly braces,
        square brackets, and double-quotes!  These indicate which values should
        be encoded as strings and which values should be encoded as integers, as
        well as the overall structure of the data!
        
        {
            "colHeaders":["ID","Total","Assignment 1","Assignment 2","Exam 1"],
            "rowHeaders":["111278","111352","111373","111305","111399","111160",
            "111276","111241"],
            "data":[[611,146,128,337],
                    [867,227,228,412],
                    [461,96,90,275],
                    [835,220,217,398],
                    [898,226,229,443],
                    [454,77,125,252],
                    [579,130,111,338],
                    [973,236,237,500]
            ]
        }
        
        Your task for this program is to complete the two conversion methods in
        this class, "csvToJson()" and "jsonToCsv()", so that the CSV data shown
        above can be converted to JSON format, and vice-versa.  Both methods
        should return the converted data as strings, but the strings do not need
        to include the newlines and whitespace shown in the examples; again,
        this whitespace has been added only for clarity.
        
        NOTE: YOU SHOULD NOT WRITE ANY CODE WHICH MANUALLY COMPOSES THE OUTPUT
        STRINGS!!!  Leave ALL string conversion to the two data conversion
        libraries we have discussed, OpenCSV and JSON.simple.  See the "Data
        Exchange" lecture notes for more details, including examples.
        
    */
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
        
        String results = "";
        
        try {
            
            // Initialize CSV Reader and Iterator
            
            CSVReader reader = new CSVReader(new StringReader(csvString));
            List<String[]> full = reader.readAll();
            Iterator<String[]> iterator = full.iterator();
            
            /* INSERT YOUR CODE HERE */
            
            JSONObject JSON_OBJ = new JSONObject();
            JSONArray ROWS = new JSONArray();
            JSONArray COLUMNS = new JSONArray();
            JSONArray DATA = new JSONArray();
            JSONArray STORE;
            String[] INFO = iterator.next();
            
            for(int i = 0; i < INFO.length; i++)
            {
                
                COLUMNS.add(INFO[i]);
                
            }
            
            while(iterator.hasNext())
            {
                
                STORE = new JSONArray();
                INFO = iterator.next();
                ROWS.add(INFO[0]);
                
                for(int i = 1; i < INFO.length; i++)
                {
                    int HOLDER = Integer.parseInt(INFO[i]);
                    STORE.add(HOLDER);
                }
                
                DATA.add(STORE);
                
            }
            
            JSON_OBJ.put("rowHeaders", ROWS);
            JSON_OBJ.put("colHeaders", COLUMNS);
            JSON_OBJ.put("data", DATA);
            
            results = JSONValue.toJSONString(JSON_OBJ);       
            
        }
        catch(Exception e) { e.printStackTrace(); }
        
        // Return JSON String
        
        return results.trim();
        
    }
    
    public static String jsonToCsv(String jsonString) {
        
        String results = "";
        
        try {
            
            // Initialize JSON Parser and CSV Writer
            
            JSONParser parser = new JSONParser();
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer, ',', '"', '\\', "\n");
            
            /* INSERT YOUR CODE HERE */
            
            JSONObject JSON_OBJ = (JSONObject)parser.parse(jsonString);
            JSONArray ROWS = (JSONArray)JSON_OBJ.get("rowHeaders");
            JSONArray COLUMNS = (JSONArray)JSON_OBJ.get("colHeaders");
            JSONArray DATA = (JSONArray)JSON_OBJ.get("data");
            JSONArray STORE;
            String[] INFO = new String[COLUMNS.size()];
            
            for(int i = 0; i < COLUMNS.size(); i++)
            {
                
                INFO[i] = (String)COLUMNS.get(i);
                
            }
            
            csvWriter.writeNext(INFO);
            
            for(int i = 0; i < DATA.size(); i++)
            {
                
                STORE = (JSONArray) DATA.get(i);
                INFO = new String[STORE.size()+1];
                INFO[0] = (String) ROWS.get(i);
                
                for(int j = 0; j < STORE.size(); j++)
                {
                    
                    INFO[j+1] = Long.toString((long)STORE.get(j));
                    
                }
                
                csvWriter.writeNext(INFO);
                
            }
            
            results = writer.toString();
            
        }
        catch(Exception e) { e.printStackTrace(); }
        
        // Return CSV String
        
        return results.trim();
        
    }
	
}