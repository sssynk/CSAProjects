import java.util.*;

public class SpeedCode4 {

   public static void main(String[] args) {
     int[] a1 = {12,34,56};
     int[] a2 = {7,8,9,10};
     int[] a3 = merge(a1,a2);
     System.out.println(Arrays.toString(a3)); 
   }
   
   public static int[] merge(int[] array1, int[] array2) {
      int current[] = new int[array1.length+array2.length];
      for(int i=0; i<=array1.length-1; i++) {
         current[i] = array1[i];
      }  
      for(int i=0; i<=array2.length-1; i++) {
         current[i+array1.length] = array2[i];
      } 
      return current;
   }
   
   
}
