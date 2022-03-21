//-----------------------------------------------------------------------------
// Dictionary.java
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------
public class Dictionary{
   private class Node {
      String key;
      String value;
      Node left;
      Node right;
      Node( String x, String y){
         key = x; 
         value = y;
         left = null;
         right = null;
      }
   }
   private Node root;
   private int numPairs; 
   public Dictionary(){
      root = null;
      numPairs = 0;
   }
   private Node findKey(Node R, String k){
      if( R == null || k.equals( R.key ) ) 
         return R;
      if ( k.compareTo(R.key) < 0 )
         return findKey(R.left, k);
      else
         return findKey(R.right, k);
   }
   private Node findParent(Node N, Node R){
      Node P = null;
      if( N != R ){
         P = R;
         while( P.left!=N && P.right!=N ){
            if(N.key.compareTo(P.key) < 0)
               P = P.left;
            else
               P = P.right;
         }
      }
      return P;
   }
   private Node findLeftmost(Node R){
      Node L = R;
      if( L!=null ) for( ; L.left!=null; L=L.left) ;
      return L;
   }
   private String printInOrder(Node R){
      if( R==null ){
         return "";
      }else{
         return ( printInOrder(R.left)+ R.key + " " + R.value + "\n" + printInOrder(R.right));
      } 
   }
   public boolean isEmpty(){
      return (numPairs == 0);
   }
   public int size(){
      return(numPairs);
   }
   public String lookup( String key){
      Node N;
      
      N = findKey(root, key);
      if ( N == null ){
         return null;
      }
      else{
         return N.value;
      }
   }
   public void insert( String key, String value) throws DuplicateKeyException{
      Node N, A, B;
      if( findKey(root, key)!= null ){
         throw new DuplicateKeyException("Dictionary Error: cannot insert() duplicate key: " + key);
      }
      N = new Node(key, value);
      B = null;
      A = root;
      while( A!=null ){
         B = A;
         if( key.compareTo(A.key)<0 ) A = A.left;
             else A = A.right;
      }
      if( B==null ) root = N;
      else if( key.compareTo(B.key)<0 ) B.left = N;
      else B.right = N;
      numPairs++;
   }
   public void delete(String key) throws KeyNotFoundException{
      Node N, P, S;
      N = findKey(root, key);
      if( N==null ){
          throw new KeyNotFoundException("Dictionary Error: cannot delete() non-existent key: " + key);
      }
      if( N.left==null && N.right==null ){  // case 1 (no children)
         if( N==root ){
            root = null;
         }else {
            P = findParent(N, root);
            if( P.right==N ) P.right = null;
            else P.left = null; 
         }
      }else if( N.right==null ){  // case 2 (left but no right child)
         if( N==root ){
            root = N.left;
         }else{
            P = findParent(N, root);
            if( P.right==N ) P.right = N.left;
            else P.left = N.left;
         }
      }else if( N.left==null ){  // case 2 (right but no left child)
         if( N==root ){
            root = N.right;
         }else{
            P = findParent(N, root);
            if( P.right==N ) P.right = N.right;
            else P.left = N.right;
         }
      }else{                     // case3: (two children: N->left!=NULL && N->right!=NULL)
         S = findLeftmost(N.right);
         N.key = S.key;
         N.value = S.value;
         P = findParent(S, N);
         if( P.right==S ) P.right = S.right;
         else P.left = S.right;
      }
      numPairs--;
   }
   public void makeEmpty(){
      root = null;
      numPairs = 0;
   }
   public String toString(){
      String s = printInOrder(root);
      return s;
   }

}
