public class ListTest.java{
   public static void main( String[] args){
      List<String> A = new List<String>();
      List<String> B = new List<String>();
      List<List<String>> C = new List<List<String>>();
    :

      A.add(1, "one");
      A.add(2, "two");
      A.add(3, "three");
      B.add(2, "twenty"); 
      C.add(1, A);
      C.add(2, B);

      System.out.println("A: "+A);
      System.out.println("B: "+B);
      System.out.println("C: "+C);
      System.out.println("A.equals(A) is "+A.equals(A));
      System.out.println("A.equals(B) is "+A.equals(B));
   }
}
