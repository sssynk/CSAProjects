import java.util.*;

public class SpeedCode5 {

   public static void main(String[] args) {
     int[] a1 = {12,34,56};
     int[] a2 = firstToLast(a1);
     System.out.println(Arrays.toString(a2)); 
   }
   
   public static int[] firstToLast(int[] a1) {
      int[] swapped = a1;
      int current = swapped[0];
      for(int i=0; i<=swapped.length-2; i++) {
         swapped[i] = swapped[i+1];
      }
      swapped[swapped.length-1] = current;
      return swapped;
   }
}
