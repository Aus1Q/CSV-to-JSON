Introduction
     In this project the goal is to be able to take in either CSV data or JSON and format it to either respectively. This will allow a system to translate either into whatever the program calls for. Programs tend to have a perference on what format it will use. Between websites, applications, and other outlets this will allow data to be received or sent out in either format.
     
Methodology
     The main idea of this program is to have a Converter.java that will house both the csvToJson() method that accepts a csvString and a jsonToCsv() method that will accept a jsonString. Below is an example of CSV data and its repspective JSON data format. CSV data is linear almost like a table. All the information for total which is at the technical 1 location is procecced by every line having the total at the 1 position this format allows a programmer to segment this data into different locations.
        
        CSV data:
        "ID","Total","Assignment 1","Assignment 2","Exam 1"
        "111278","611","146","128","337"
        "111352","867","227","228","412"
        "111373","461","96","90","275"
        "111305","835","220","217","398"
        "111399","898","226","229","443"
        "111160","454","77","125","252"
        "111276","579","130","111","338"
        "111241","973","236","237","500"
        
       JSON data:
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
                    [973,236,237,500]]
                    
      Above is the JSON data format. As we can see there is a bunch of lists cosisting of column headers row headers and data. Everyone's ID is put into the rowheaders as this is the primary key in the data. The data for the first ID will be stored all inside the first list in the data obeject and so on. In the csvToJson method the csvString will be read into a CSV Reader that will then be put into a String array that will allow us to iterate through and indiviualize the data. We proceed to create JSON Object and Arrays to contain and filter the data. This includes rows, columns, data, and a storage. It will then iterate through the INFO String list and add these to the Columns JSON Array. We know the first line in the CSV data can considered our column headers adn the first item in each line after that will be put into the row headers list. from there the data is segemented into different list depending on the ID it has came from. In the jsonToCsv() it is a very similar approach accept for now we already have out lists outlined for us it is just a task to oragnize these back into their respective locations and back into strings. This is possible by iterating through the row header and data list at the same time as, the data lists share the same location as its repsected ID in the row headers. So we take only the first ID and put the entire first of 8 lists next to the first ID in string form.

Results
    The results are returned as a string so they can either be printed out to the user or used for another reason within a program. This is significant because systems opt for different formats of data all the time and this is a small but very effienent program at allieving that issue. 

Conclusions
      I learned a lot from this project especially about CSV and JSON. Each have their own libraries withing java which helped a lot when translating from one to the other. You can create specific JSON objects and Arrays to hold the JSON information and there are parser intities that can go through this information. The CSV library allows us to have a reader that is able to read csvStrings.
