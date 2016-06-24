
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Digital Reasoning Coding Test
 * Created by ZachSloane
 */
public class WordTokenizer {
    public static void main(String[] args) {


            try {
                //Creates new File and Scanner objects. Opens nlp_data.txt using Scanner
                File file = new File("nlp_data.txt");
                Scanner sc = new Scanner(file);

                //Creates new File and Scanner objects. Opens NER.txt using Scanner
                File namefile = new File("NER.txt");
                Scanner namessc = new Scanner(namefile);


                //initializes counter variable
                int count = 0;


                //ArrayList to store words and location from input txt file
                List<String> wordArrayList = new ArrayList<>();

                //ArrayList to store punctuation and location from input txt file
                List<String> puncArrayList = new ArrayList<>();

                //ArrayList to store named entities from text file
                List<String> entityArrayList = new ArrayList<>();

                //ArrayList to store output named entities
                List<String> nameOutputArrayList = new ArrayList<>();


                String[] nArray = new String[150];
                //ArrayList<String> namesArrayList = new ArrayList<>();


                /*starts loop for writing tokenized text to wordArrayList and puncArrayList
                loops through the file until there are no more elements. Default delimiter is white space.*/
                while (sc.hasNext()) {
                    String element = sc.next();

                    /*This loop iterates through the Scanner object(the file to be parsed) and checks for punctuation.
                     *Any punctuation that is found is added to the puncArrayList, in the corresponding location to
                     * match the wordArrayList. The punctuation is then removed from the word, and it is added to the
                     * wordArrayList.
                    * */
                    if (element.contains("\"")) {
                        if (element.startsWith("\"")) {
                            puncArrayList.add("\"");
                            element = element.replace("\"", "");
                            wordArrayList.add(element);
                        } else if (element.endsWith("\"")) {
                            element = element.replace("\"", "");
                            wordArrayList.add(element);
                            puncArrayList.add("\"");
                        } else {
                            element = element.replace("\"", "");
                            wordArrayList.add(element);
                            puncArrayList.add("\"");
                        }
                    } else if (element.contains(".")) {
                        if ((element.startsWith(".") && (!element.equals("...")))) {
                            puncArrayList.add(".");
                            element = element.replace(".", "");
                            wordArrayList.add(element);
                        } else if ((element.startsWith(".") && (element.equals("...")))) {
                            puncArrayList.add("...");
                            element = element.replace("...", "");
                            wordArrayList.add(element);
                        } else if (element.endsWith(".")) {
                            element = element.replace(".", "");
                            wordArrayList.add(element);
                            puncArrayList.add(".");
                        } else {
                            element = element.replace(".", "");
                            wordArrayList.add(element);
                            puncArrayList.add(".");
                        }
                    } else if (element.contains(",")) {
                        if (element.startsWith(",")) {
                            puncArrayList.add(",");
                            element = element.replace(",", "");
                            wordArrayList.add(element);
                        } else {
                            element = element.replace(",", "");
                            wordArrayList.add(element);
                            puncArrayList.add(",");
                        }
                    } else if (element.contains("'")) {
                        if (element.startsWith("'")) {
                            puncArrayList.add("'");
                            element = element.replace("'", "");
                            wordArrayList.add(element);
                        } else {
                            element = element.replace("'", "");
                            wordArrayList.add(element);
                            puncArrayList.add("'");
                        }
                    } else if ((element.contains("?"))) {
                        if (element.startsWith("?")) {
                            puncArrayList.add("?");
                            element = element.replace("?", "");
                            wordArrayList.add(element);
                        } else {
                            element = element.replace("?", "");
                            wordArrayList.add(element);
                            puncArrayList.add("?");
                        }
                    } else if (element.contains("(")) {
                        if (element.startsWith("(")) {
                            puncArrayList.add("(");
                            element = element.replace("(", "");
                            wordArrayList.add(element);
                        } else {
                            element = element.replace("(", "");
                            wordArrayList.add(element);
                            puncArrayList.add("(");
                        }
                    } else if (element.contains(")")) {
                        if (element.startsWith(")")) {
                            puncArrayList.add(")");
                            element = element.replace(")", "");
                            wordArrayList.add(element);
                        } else {
                            element = element.replace(")", "");
                            wordArrayList.add(element);
                            puncArrayList.add(")");
                        }
                    } else if (element.contains("-")) {
                        if (element.startsWith("-")) {
                            puncArrayList.add("-");
                            element = element.replace("-", "");
                            wordArrayList.add(element);
                        } else {
                            element = element.replace("-", "");
                            wordArrayList.add(element);
                            puncArrayList.add("-");
                        }
                    } else {
                        puncArrayList.add(" ");
                        wordArrayList.add(element);
                    }

                    //OUTPUT for part 1
                    System.out.println("WordCount: " + count + " Word: " +  wordArrayList.get(count));
                    System.out.println("Location: " + count + " Punctuation: " + puncArrayList.get(count));


                    //loop for adding named entities(pronouns) from text file to entityArrayList
                    for (int ncount = 0; ncount <= count; ncount++) {
                        while (namessc.hasNext()) {
                            entityArrayList.add(namessc.next());
                        }
                    }
                    count++;
                }

                int i = 0;
                //loop to check named entities against the text file (in the form of wordArrayList)
                while (i < entityArrayList.size()) {
                    for (String w : wordArrayList) {
                        if (w.equals(entityArrayList.get(i))) {

                            nameOutputArrayList.add(wordArrayList.indexOf(w), w);

                            System.out.println("Entity match for " + w + " at index " + wordArrayList.indexOf(w));
                        } else {
                            nameOutputArrayList.add("");
                        }
                    }
                    i++;
                }

                //when loops are done, close Scanners.
                sc.close();
                namessc.close();

                //catch block for the try- file not found exception for Scanner.
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}
