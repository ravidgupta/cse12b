// Ravi Gupta
// radgupta
// CMPS12B/M
// 4/14/19
// Subset.java
// Retrieves the subsets of the two numbers inputted by the user. Prints all
// of the different possible subsets and will ive usage if wrong.
// 
class Subset{
   public static void main( String[] args ){
      int i, k;
      try{ // to parse args[i] as int
         i = Integer.parseInt(args[0]);
      }catch(NumberFormatException e){
         System.out.println("Usage: Java Subset <int> <int>");
         return;
      }
      catch(ArrayIndexOutOfBoundsException e){
         System.out.println("Usage: Java Subset <int> <int>");
         return;
      }
      try{  // to parse args[i] as 
         k = Integer.parseInt(args[1]);
      }catch(NumberFormatException e){
         System.out.println("Usage: Java Subset <int> <int>");
         return;
      }
      catch(ArrayIndexOutOfBoundsException e){
         System.out.println("Usage: Java Subset <int> <int>");
         return;
      }
      int[] B = new int[i+1];
      if( i < k ){
         System.out.println("Usage: Java Subset <int> <int>");
         return;
      }
      printSubsets( B, k, 1);
      
   }
   
   static String setToString(int[] B){
      String output = new String("{");
      for( int i = 1; i < B.length; i++){
         if( B[i] == 1){
             output = output + i + ", ";
         }
      }
      if( output.length() > 1){
         output = output.substring(0, output.length() - 2) + "}";
      }
      else{
         output = output + "}";
      }
      return output;
   }
   static int check;
   static boolean check1 = false;
   static void printSubsets(int[] B, int k, int i){
      if( k == check || check1 ){
         B[i-1]=0;
         check1 = false;
      }
      else{
         B[i-1]=1;
      }
      check = k;            
      if( k == 0 ){
         System.out.println(setToString(B));
         check1 = true;
         return;
      }
      if( i >= B.length ){
         check1 = true;
         return;
      }
      if( k > B.length - i + 1 ){
         check1 = true;
         return;
      }
      printSubsets(B, k-1, i+1);
      printSubsets(B, k, i+1); 
   }
}
