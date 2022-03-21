//-----------------------------------------------------------------------------
// Ravi Gupta
// radgupta
// CMPS12B/M
// 5/4/19
// Dictionary.java
// Linked List implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      String key;
      String value;
      Node next;
    
      Node(String x, String y){
         key = x;
         value = y;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private int numValues;  // number of items in this Dictionary
   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary(){
      head = null;
      numValues = 0;
   }
   // private helper function -------------------------------------------------
   
   // findKey()
   // returns a reference to the Node at position index in this Dictionary
   private Node findKey(String x){
      for (Node N = head; N != null; N = N.next) {
         if ( x.equals(N.key)){
            return N;
         }
      }
      return null;   
   }
   
   // ADT operations ----------------------------------------------------------
   
   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numValues == 0);
   }
  
   // size()
   // pre: none
   // post: returns the number of entries in the Dictionary
   public int size() {
      return numValues;
   }
   
   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key){
      Node N = findKey(key);
      if ( N == null ){
         return null;
      } 
      return N.value; 
   }
 
   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
      if( lookup(key) == null ){
         Node N = new Node(key, value);
         if( head == null){
            N.next = head;
            head = N;
         }
         else{
            Node R = head;
            while( R.next != null ){
              R = R.next;
            }           
            R.next = N;
         }
         numValues++;
      }
      else{   
         throw new DuplicateKeyException("cannot insert duplicate keys");
      }
   }
   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
      if( lookup(key) != null ){
         Node N = findKey(key);
         if(N == head){
            head = N.next;
         }
         else{
            Node R = head;
            while( R.next != null ){
               if( N.key.equals(R.next.key)){
                  break;
               }
               R = R.next;
            }
            R.next = N.next;
            N.next = null;
         }
         numValues--;
      }
      else{
         throw new KeyNotFoundException("cannot delete non-existent key");
      }
   } 
   // makeEmpty()
   // pre: none
   public void makeEmpty(){
      head = null;
      numValues = 0;
   }
   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = head;

      for( ; N!=null; N=N.next){
         sb.append(N.key).append(" ").append(N.value).append("\n");
      }
      return new String(sb);      
   }
}
