// Ravi Gupta
// radgupta
// CMPS 12B/M
// 4/19/19
// FileReverse.java
// Takes a file and reverses all the strings in the list. Then the strings are
// printed out line by line.
import java.io.*;
import java.util.Scanner;
class FileReverse{
   public static void main(String[] args) throws IOException{
      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] token = null;
      int i, n = 0;
      if(args.length < 2){
         System.out.println("Usage: FileTokens <input file> <output file>");
         System.exit(1);
      }
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));
      while( in.hasNextLine() ){
         line = in.nextLine().trim() + " ";
         token = line.split("\\s+");
         n = token.length;
         for(i=0; i<n; i++){
            System.out.println( stringReverse(token[i], token[i].length() - 1 ));
            count = 0;
         }
      }
      in.close();
      out.close();
   }
   static int count = 0;
   static char temp;
   static char chars[]; 
   public static String stringReverse(String s, int n){
      if( count >= n ){
         return s;
      }
      chars = s.toCharArray();
      temp = chars[n]; 
      chars[n] = chars[count]; 
      chars[count] = temp; 
      count++; 
      s = new String(chars);
      return stringReverse( s, n - 1 );
   }

}    
