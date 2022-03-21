public class QueueTest{
   public static void main( String[] args ){
      Queue a = new Queue();
      a.enqueue("love");
      System.out.println(a);
      a.dequeue();
      System.out.println(a);
      a.enqueue("love");
      a.enqueue("heart");
      System.out.println(a);
      System.out.println(a.peek());
      a.dequeueAll();
      System.out.println(a);
   }
}
