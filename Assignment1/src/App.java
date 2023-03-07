import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class App 
{
    //declares functions that will be used in the prgram
    public static void main(String[] args) throws Exception {
        System.out.println("Assignment 1!");
        ArrayList<String> words = readWords("res\\words.txt"); //readWords function
        HashMap<String, Integer> wordCounter = buildHashMap(words); // count number of worrds function
        sortWords(wordCounter); // This creates the sorted word file for words.txt
        createHTMLFile(wordCounter); // this creates the unsorted file for words.txt

        //part 3 step 14
        ArrayList<String> paragraph = readWords("res\\paragraph.txt"); 
        //part 3 step 15
        HashMap<String, Integer> wordsCounter = buildHashMap(paragraph);
        sortParagraph(wordsCounter); // creates sorted paragraph.txt file
        //part 3 step 16
    }

    //read words puts each word in an array
    private static ArrayList<String> readWords(String fileName)
    {
        File file = new File(fileName);
        ArrayList<String> wordList =  new ArrayList<>();
        
            try {
                // readers
                FileReader reader = new FileReader(file); 
                BufferedReader bufferedReader = new BufferedReader(reader); 

                String line = bufferedReader.readLine();
                //while loop looks for spaces and puncuation and splits the string when they are encountered
                while(line != null)
                {
                    String[] words = line.split("[ .,]+");
                    for(String word: words)
                    {
                        if(word.trim().length() > 0)
                        {
                            wordList.add(word.toLowerCase());
                        }
                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
            return wordList;
    }
    // count word occurences function 
    private static HashMap<String, Integer> buildHashMap(ArrayList<String> words){
        HashMap<String, Integer> wordCounter = new HashMap<>();
        //goes through all words and either sets the counter to 1 if not encountered before 
        //      or raises the number by one if the program has seen it
        for(String word: words)
        {
            Integer count = wordCounter.get(word);
            if(count == null)
            {
                wordCounter.put(word,1 );
            }
            else
            {
                wordCounter.put(word, count + 1);
            }
        }   
        return wordCounter;
    }
     

    //PART 2: Sorting the Table by Frequency in ASC order
    private static void sortWords(HashMap<String, Integer> wordCounter)
        {
            //initializes counter for id / creates a new array
            int id = 0;
            System.out.println("sortwords!");
            ArrayList<wordSort> wordsList = new ArrayList<>();
            //This for loop creates an array adding an id tag to every entry to the table data
            for(String key: wordCounter.keySet())
            {
            wordsList.add(new wordSort(id,key,wordCounter.get(key)));
            id++;
            }
            //this sorts the array is ascending order
            Collections.sort(wordsList);

            //the following code is a repeat of CreateHTMLFile but using the new array instead
            File file = new File("sorted.html");

            try {
                FileWriter fileWriter = new FileWriter(file);
                StringBuilder builder = new StringBuilder();

                //adds CSS to the table
                final String css = "<style>"
                    + "td,tr { border: groove} "
                    + "table, td,tr { border-collapse: collapse} "
                    +"</style>";
                    builder.append(css).append("\n");


                builder.append("<h1>Word Count Table</h1>");
                //Builds Table
                builder.append("<table>");
                for(wordSort wordSort: wordsList)
                {
                    builder.append("<tr>");
                    builder.append("<td>"+ wordSort.word +"</td>");
                    builder.append("<td>"+ wordSort.frequency +"</td>");
                    builder.append("</tr>");
                }
                builder.append("</table>");

                fileWriter.append(builder.toString());
                fileWriter.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

       //part 3 step 17
    private static void sortParagraph(HashMap<String, Integer> wordCounter)
        {
           //initializes counter for id / creates a new array
           int id = 0;
           System.out.println("Paragraph!");
           ArrayList<wordSort> wordsList = new ArrayList<>();
           //This for loop creates an array adding an id tag to every entry to the table data
           for(String key: wordCounter.keySet())
           {
           wordsList.add(new wordSort(id,key,wordCounter.get(key)));
           id++;
           }
           //this sorts the array is ascending order
           Collections.sort(wordsList);
   
           //the following code is a repeat of CreateHTMLFile but using the new array instead
           File file = new File("paragraph.html");
   
           try {
               FileWriter fileWriter = new FileWriter(file);
               StringBuilder builder = new StringBuilder();
   
               //adds CSS to the table
               final String css = "<style>"
                   + "td,tr { border: groove} "
                   + "table, td,tr { border-collapse: collapse} "
                   +"</style>";
                   builder.append(css).append("\n");
   
   
               builder.append("<h1>Word Count Table</h1>");
               //Builds Table
               builder.append("<table>");
               for(wordSort wordSort: wordsList)
               {
                   builder.append("<tr>");
                   builder.append("<td>"+ wordSort.word +"</td>");
                   builder.append("<td>"+ wordSort.frequency +"</td>");
                   builder.append("</tr>");
               }
               builder.append("</table>");
   
               fileWriter.append(builder.toString());
               fileWriter.close();
           } catch (IOException e) {
   
               e.printStackTrace();
           }
        }


    //Create an HTML file output with a table
    private static void createHTMLFile(HashMap<String, Integer> wordCounter)
    {
        File file = new File("words.html");

        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();

            //adds CSS to the table
            final String css = "<style>"
                + "td,tr { border: groove} "
                + "table, td,tr { border-collapse: collapse} "
                +"</style>";
                builder.append(css).append("\n");


            builder.append("<h1>Word Count Table</h1>");
            //Builds Table
            builder.append("<table>");
            for(String key: wordCounter.keySet())
            {
                builder.append("<tr>");
                builder.append("<td>"+ key +"</td>");
                builder.append("<td>"+ wordCounter.get(key) +"</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");

            fileWriter.append(builder.toString());
            fileWriter.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        /*// prints data result in console
        for(String keyWord: wordCounter.keySet())
        {
            System.out.println(keyWord + ": " + wordCounter.get(keyWord));
        }*/
    }
}