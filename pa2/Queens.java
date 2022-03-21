// Ravi Gupta
// radgupta
// CMPS12B/M
// 4/24/19
// Queens.java
// Finds the number of solutions of queens fitting on the board without attackin// each other. Prints out the solutions if -v is typed. 
class Queens{
   static String verb;
   static int[][] B;
   static int i;
   public static void main(String[] args){
      try{
         if( args[0].equals("-v")){
            verb = "verbose"; 
            try{ // to parse args[i] as int
               i = Integer.parseInt(args[1]);
            }catch(NumberFormatException e){
               System.out.println("Usage: Queens [-v] number");
               return;
            }catch(ArrayIndexOutOfBoundsException e){
               System.out.println("Usage: Queens [-v] number");
               return;
            }
         }
         else{
            verb = "";
            try{ // to parse args[i] as int
               i = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
               System.out.println("Usage: Queens [-v] number");
               return;
            }catch(ArrayIndexOutOfBoundsException e){
               System.out.println("Usage: Queens [-v] number");
               return;
            }   
         }
      }catch(ArrayIndexOutOfBoundsException e){
         System.out.println("Usage: Queens [-v] number");
         return;
      }
      B = new int[i+1][i+1];
      for( int a = 0; a < i + 1; a++){
         for( int b = 0; b < i + 1; b++){
            B[a][b] = 0;
         }
      }
      int ans = findSolutions(B, 1, verb);
      System.out.println( i + "-Queens has " + ans + " solutions");
   }
   static void placeQueen(int[][] B, int i, int j){
      int a;
      B[i][j] = 1;
      B[i][0] = j;
      for( a = 1; a < B.length; a++){
         B[a][j]--;
      }
      for( a = 1; a < B[i].length; a++){
         B[i][a]--;
      }
      for( a = 1; i + a < B.length && j + a < B[i + a].length; a++)
      {
         B[i + a][j + a]--;       
      }
      for( a = 1; j + a < B[i - a].length  && i - a > 0; a++)
      {
         B[i - a][j + a]--;
      }
      for( a = 1; j - a > 0 && i + a < B.length; a++)
      {
         B[i + a][j - a]--;
      }
      for( a = 1; j - a > 0 && i - a > 0; a++)
      {
         B[i - a][j - a]--;
      }
   }
   static void removeQueen(int[][] B, int i, int j){
      int a;
      B[i][j]--;
      B[i][0] = 0;
      for( a = 1; a < B.length; a++){
         B[a][j]++;
      }
      for( a = 1; a < B[i].length; a++){
         B[i][a]++;
      }
      for( a = 1; i + a < B.length && j + a < B[i + a].length; a++)
      {
         B[i + a][j + a]++;
      }
      for( a = 1; j + a < B[i - a].length && i - a > 0; a++)
      {
         B[i - a][j + a]++;
      }
      for( a = 1; j - a > 0 && i + a < B.length; a++)
      {
         B[i + a][j - a]++;
      }
      for( a = 1; j - a > 0 && i - a > 0; a++)
      {
         B[i - a][j - a]++;
      }

   }
   static void printBoard(int[][] B){
      System.out.print("(" + B[1][0]);
      for( int a = 2; a < B.length; a++){
         System.out.print( ", " + B[a][0]);
      }
      System.out.println(" )"); 
   }

   static int findSolutions(int[][] B, int i, String mode){
      int sum = 0;
      if ( i >= B.length ){
         if ( mode.equals("verbose")){
            printBoard(B);
         }
         return 1;
      }
      else{
         for ( int a = 1; a < B[i].length; a++){
            if ( B[i][a] == 0 ){
               placeQueen(B, i, a);        
               int add = findSolutions(B, i + 1, mode);
               sum = sum + add;
               removeQueen(B, i, a);
            }
         }
      }
      return sum;
   }
}
