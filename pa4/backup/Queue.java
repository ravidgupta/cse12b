public class Queue implements QueueInterface{

  private class Node {
      private Object item;
      Node next;

      Node(Object x){
         item = x;
         next = null;
      }
   }
   // Fields for the Queue class
   private Node head;  // reference to first Node in Queue
   private Node tail;  // reference to last Node in Queue
   private int numItems;  // number of items in this Queue
   // Queue()
   // constructor for the Queue class
   public Queue(){
      head = null;
      tail = null;
      numItems = 0;
   }                                
   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }
   
   // length()
   // pre: none
   // post: returns the length of this Queue
   public int length(){
      return numItems;
   }
   
   // enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
      Node N = new Node( newItem );
      if( numItems == 0 ){
         head = N;
         tail = N;
      }
      else {
         tail.next = N;
         tail = N;
      }
      numItems++;
   }
   
   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element 
   public Object dequeue() throws QueueEmptyException{
      Object s;
      if (numItems == 0){
         throw new QueueEmptyException("cannot dequeue() empty queue");
      }
      else if (numItems == 1){
         s = head.item;
         head = null;
         tail = null;
      }
      else{
         s = head.item; 
         head = head.next;
      }
      numItems--;
      return s;
   }
   
   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if ( numItems == 0){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      return head.item;
   }
   
   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if ( numItems == 0){
         throw new QueueEmptyException("cannot dequeueAll() empty queue");
      }
      head = null;
      tail = null;
      numItems = 0;
   }
   
   // toString()
   // overrides Object's toString() method
   public String toString(){
      Node N = head;
      String s = "";
      while( N != null ){
         s = s + N.item + " ";
         N = N.next;
      }
      return s;
   }
}
