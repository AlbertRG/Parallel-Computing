package NorthPole;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Santa implements Runnable{

    private final Stack elves;
    private final Stack reindeer;
    
    public Santa(Stack elves, Stack reindeer){
        this.elves = elves;
        this.reindeer = reindeer;
    }
    
    @Override
    public void run() {
        while (reindeer.size() < 9) {        
            try {
                work();
            } catch (InterruptedException ex) {
                Logger.getLogger(Santa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Santa wakes up and leaves to deliver gifts");
    }//run
    
    private void work() throws InterruptedException {
        
        synchronized(elves){    
            if(elves.size() == 3){
                System.out.println("Santa wakes up and helps 3 elves with toys");
                Thread.sleep(1000);
                System.out.println("Repairing bike...");
                elves.pop();
                Thread.sleep(1000);
                System.out.println("Sewing doll...");
                elves.pop();
                Thread.sleep(1000);
                System.out.println("Wearing teddy bear...");
                elves.pop();
                Thread.sleep(1000);
                System.out.println("Santa comes back to bed");   
            } 
            elves.wait();
        }
        
    }//work
}//class
