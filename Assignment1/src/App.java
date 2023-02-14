import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class App 
{
    //declares functions that will be used in the prgram
    public static void main(String[] args) throws Exception {
        System.out.println("Assignment 1!");
        ArrayList<String> words = readWords("res\\words.txt"); //readWords function
        HashMap<String, Integer> wordCounter = buildHashMap(words); // count number of worrds function
        createHTMLFile(wordCounter);
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

        // prints data result in console
        for(String keyWord: wordCounter.keySet())
        {
            System.out.println(keyWord + ": " + wordCounter.get(keyWord));
        }
    }
}