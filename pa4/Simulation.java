import java.io.*;
import java.util.Scanner;

public class Simulation{
   
   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

   public static void main(String[] args) throws IOException{
      Scanner in = null;
      PrintWriter out = null;
      PrintWriter out1 = null;
      String line = null;
      int m;
      int time = 0; 
      int min;
      int total = 0;
      int max = 0;
      double average = 0.00;
      boolean print = false;
      boolean end = false;
      Job j = null;
      if(args.length < 1){
         System.out.println("Usage: Simulation <input file>");
         System.exit(1);
      }
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[0] + ".rpt"));
      out1 = new PrintWriter(new FileWriter(args[0] + ".trc"));
      m = Integer.parseInt(in.nextLine());
      Queue arri = new Queue();
      Queue comp = new Queue();
      Queue orig = new Queue();
      for( int i = 0; i < m; i++ ){
         j = getJob( in ) ;
         arri.enqueue( j );
         orig.enqueue( j );
      }
      out.println("Report file: " + args[0] + ".rpt");
      out.println(m + " Jobs:");
      out.println(arri);
      out.println();
      out.println("***********************************************************");
      out1.println("Trace file: " + args[0] + ".trc");
      out1.println(m + " Jobs:");
      out1.println(arri);
      out1.println();
      for ( int n = 1; n < m; n++){
         Queue[] pro = new Queue[n];
         
         while(!comp.isEmpty()){
            comp.dequeueAll();
         }
         while( arri.length() <  m){
            j = (Job)orig.peek();
            j.resetFinishTime();
            arri.enqueue(orig.peek());
            orig.enqueue(orig.dequeue());
         }
         end = false;
         time = 0;
         total = 0;
         max = 0;
         for ( int i = 0; i < n; i++){
            pro[i] = new Queue();
         }
         out1.println("*****************************");
         if ( n == 1){
            out1.println(n + " processor:");
         }
         else{
            out1.println(n + " processors:");
         }
         out1.println("*****************************");
         out1.println("time = " + time);
         out1.println("0: " + arri + comp);
         for ( int i = 0; i < n; i++){
            out1.println((i+1) + ": " + pro[i]);
         }
         out1.println();
         while( m > comp.length() ){
            time++;
            print = false;
            
            for ( int i = 0; i < n; i++){
               if( !pro[i].isEmpty()){ 
                  j = (Job)pro[i].peek();
                  if ( j.getFinish() == time){
                     if ( j.getWaitTime() > max){
                        max = j.getWaitTime();
                     }
                     comp.enqueue(pro[i].dequeue());
                     print = true;
                  }
               }
               
            }
            if( !arri.isEmpty()){
               j = (Job)arri.peek();
               while ( j.getArrival() == time && !end ){
                  min = 0;
                  for ( int i = 0; i < n; i++){
                     if( pro[i].length() < pro[min].length()){
                         min = i;
                     }
                  }
                  pro[min].enqueue(arri.dequeue());
                  print = true;
                  if ( !arri.isEmpty()){
                     j = (Job)arri.peek();
                  }
                  else{
                     end = true;
                  }
               }
            }
            for ( int i = 0; i < n; i++){
               if(!pro[i].isEmpty()){
                  j = (Job)pro[i].peek();
                  if(j.getFinish() == -1){
                     j.computeFinishTime(time);
                  }
                  if(pro[i].length() > 1){
                     total = total + pro[i].length() - 1;
                  }
               }
            }
            if ( print ){
               out1.println("time = " + time);
               out1.println("0: " + arri + comp);
               for ( int i = 0; i < n; i++){
                  out1.println((i+1) + ": " + pro[i]);  
               }
               out1.println();
            }
         }
         average = total * 1.00 / m;
         average = Math.round(average * 100)/100.0;
         if ( n == 1 ){
            out.printf("%d processor: totalWait=%d, maxWait=%d, averageWait=%.2f", n, total, max, average ) ;
            out.println();
         }
         else{
            out.printf("%d processors: totalWait=%d, maxWait=%d, averageWait=%.2f", n, total, max, average ) ;
            out.println();
         }
      }
      in.close();
      out.close();
      out1.close();
   }
}
