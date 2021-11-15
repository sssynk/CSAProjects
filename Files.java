// James Wilson 2021

import java.io.*;
import java.util.*;

public class Files {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Locating File...");
        Scanner input = new Scanner(new File("file.txt")); // create scanner for file
        ArrayList<String> words = new ArrayList<String>(); // make an arraylist of all of the words in file
        System.out.println("Parsing File...");
        while (input.hasNext()) { // loop through all words in file
            String nouh = input.next(); // get the next word
            if(nouh.indexOf("—") == -1) { // check to see if there isn't a dash
                words.add(nouh.replaceAll("[^a-zA-Z ]", "").replaceAll("-", "").toLowerCase()); // add to words
            } else {
                String[] splitted = nouh.split("—"); // split based on dash
                for (int i = 0; i < splitted.length; i++) { // loop through split	
                    words.add(splitted[i].replaceAll("[^a-zA-Z ]", "").replaceAll("-", "").toLowerCase()); // add each word seperately 
                }
            }
        }

        System.out.println("Complete!");
    
        Scanner consoleinput = new Scanner(System.in); // console scanner
        while (true) { // repeat forever until broken
            System.out.println();
            System.out.println();
            System.out.println("Welcome to FileParse v1.0");
            System.out.println("~~~~~~~~");
            System.out.println("1 - Get Amount of Certain Words");
            System.out.println("2 - Get How Many Times Every Word Occurs");
            System.out.println("3 - Exit");
            System.out.println("~~~~~~~~");
            System.out.print("Choose One: ");
            String inp = consoleinput.next(); // get user input
            if(inp.equals("1")) { // check to see if option 1 is pressed
                System.out.println("~~~~~~~~");
                System.out.println("Please State The Word You Want to Count: ");
                String ninput = consoleinput.next(); // get the word you want to count
                int count = 0;
                for (int i = 0; i < words.size(); i++) { // loop through all words
                    if(words.get(i).equalsIgnoreCase(ninput)) { // check to see if the given word occurs in the words arraylist
                        count++; // increase count of word
                    }	      	
                }   
                System.out.println("~~~~~~~~");
                System.out.println("The word '"+ninput+"' occurs "+count+" times."); // print how much word occurs
            } else if(inp.equals("2")) { // second option
                System.out.println("~~~~~~~~");
                System.out.println("Sorting, this may take up to a minute...");
                ArrayList<String> sorted_words = new ArrayList<String>(); // arraylist for only one of each word
                for (int i = 0; i < words.size(); i++) { // loop through all words
                    if(sorted_words.indexOf(words.get(i)) == -1) { // if the word doesn't exist in sorted_words...
                        sorted_words.add(words.get(i));  // add to sorted words if word isnt added already
                    }
                }  
                String sorted = "";
                for (int i = 0; i < sorted_words.size(); i++) { // for the arraylist with one of each word...
                    int occurrences = Collections.frequency(words, sorted_words.get(i)); // check to see how many times it occurs in the normal words arraylist
                    sorted = sorted + "\n" + (sorted_words.get(i)+" = "+occurrences); //add to sorted string to make it faster
                } 
                System.out.println(sorted); // print all words
            } else {
                System.out.println("~~~~~~~~");
                System.out.println("Thanks for using FileParse");
                System.out.println("~~~~~~~~");
                break; // break while true loop
            }
        }
    }
}