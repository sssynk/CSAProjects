/*

Weather Lab
By James
Tells you about the weather

*/


import java.util.*;

public class WeatherLab {

   public static void main(String[] args) {
      int[][] temps = new int[12][31];// create a 12 month, 31 days dimensional array
      int offset = 0; // no offset (yet) :)
      
      for(int i=0; i<temps.length; i++) { // loop through months
         for(int j=0; j<temps[i].length; j++) { // loop through days
         
            temps[i][j] = (int)(Math.random()*40)+offset;     // calculate random temps       

         
         }
         if (i < 6) {
               offset += 8; // change based on month
            } else {
               offset -= 8;
         }
      }
      
    System.out.println("Welcome to the Weather Lab");

      
      ArrayList<ArrayList<String>> allmonths = new ArrayList<ArrayList<String>>(); // create an arraylist of all months with no data yet
      
      //for(int i=0;i<temps.length;i++) {
        // System.out.println(Arrays.toString(temps[i]));
      //}
      while (true) { // repeat forever
      
      Scanner input = new Scanner(System.in); 
      
      
      System.out.println();
      System.out.print("Type the name of the month or \"Quit\" to quit:  ");
      
      String monthInput = input.next();
      
      if (monthInput.equalsIgnoreCase("quit")) { // test if quit
         break; // break the while true loop
      }
      
      String[] months = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
      int month = -1; // so we can tell if a month is valid
      
      for(int i=0;i<months.length;i++) { // loop through months array above
         if (monthInput.equalsIgnoreCase(months[i])) { // if month = input,
            month = i; // the month is this
            break; // break the for loop since we have the month now
         }
      }
      
      if(month == -1) { // if -1, (set earlier) default to january
         System.out.println("Incorrect month! Defaulting to january.");
         month = 0;
      }
      
      System.out.println("You chose "+months[month]+"!\n The temparatures for "+months[month]+" are as follows: "+Arrays.toString(temps[month]));
      System.out.println();
      System.out.println("The average of temps for "+months[month]+" is "+avg(temps[month])+" degrees.\nThere were "+aboveAvg(temps[month], avg(temps[month]))+" days above the average.\nThe range of temperatures is "+range(temps[month])+".\nThe mode of "+months[month]+" is "+mode(temps[month])[0]+". This temp occurred "+mode(temps[month])[1]+" times.");
      System.out.println("The highest temp in "+months[month]+" is "+findHighest(temps[month])+".\nThe lowest temp is "+findLowest(temps[month])+".");
      
      ArrayList<String> monthlist = new ArrayList<String>(); // create a new arraylsit of this month
      
      monthlist.add(months[month]); // add month name
      monthlist.add(Integer.toString(findHighest(temps[month]))); // add highest temp
      monthlist.add(Integer.toString(findLowest(temps[month]))); // add lowest temp
      monthlist.add(Double.toString(avg(temps[month]))); // add avg
      
      allmonths.add(monthlist); // add this month to all months
      }
       System.out.println();
      System.out.println("Thanks for using the Weather Lab! Here is a recap: ");
      
      for(ArrayList<String> value:allmonths) { // loop through all months
         System.out.print(value.get(0)+"     Hi: "+value.get(1)+" Low: "+value.get(2)+" Avg: "+value.get(3)); // print out each months data
          System.out.println();
      }
      
      System.out.println("Complete!");

   }
   
      public static double avg(int[] values) { // loop through all values, and add them all to a int
         int all = 0;
         for(int place:values) {
         all+=place;
         }
         return Math.round(((all/values.length)*100.0)/100.0); // round and divide by # of items in the table
      }
      
      public static int aboveAvg(int[] values, double avg) { // loop through all temps, then add 1 to the final answer if it is above the avg
         int above = 0;
         for(int place:values) {
         if(place>avg) { // if above avg
            above++;
         }
         }
         return above;
      }
   
      public static int range(int[] values) {
      int min = 100;
      int max = 0;
      for(int places:values) { // loop through all temps, check if it is lower or higher than the temps before it returns
         if(places < min) {
            min = places;
         } else if (places>max) {
            max = places;
         }
      }
      
      return ((max-min)+1); // return range calc
   }
   
   public static int[] mode(int[] table) { // tally
      int[] modecalc = new int[100]; // new table with all possible temps
      for(int i=0; i<table.length; i++) { // loop through temps table
         modecalc[table[i]]++; // add 1 to the temperature in the modecalc table; ex if temp is 40, then index of 40 in the modecalc table would be increased
      }
      int max = 0;
      int amax = 0;
      for(int i=0; i<modecalc.length; i++) { // loop through modecalc table
         if (modecalc[i] >= max) { // if the value is greater than the max
            max = modecalc[i]; // set the new max to the number of times the temperature has been seen
            amax = i; // set the actual max to the temperature of the index
         }
         
      }
            
      int[] returnTable = {amax, max}; // make a table of items to return
      return returnTable; 
   }
   
   public static int findHighest(int[] temps) { // loop through temps to find highest
         int min = 100;
      int max = 0;
      for(int places:temps) { // loop through temps
         if(places < min) {
            min = places;
         } else if (places>max) { // if the temp is > max then set max to the temp
            max = places;
         }
      }
      
      return max;
   }
   
   public static int findLowest(int[] temps) { // loop through temps to find lowest
         int min = 100;
      int max = 0;
      for(int places:temps) {// loop through temps
         if(places < min) {// if the temp is < min then set min to the temp
            min = places;
         } else if (places>max) {
            max = places;
         }
      }
      
      return min;
   }
}