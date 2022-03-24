package Bridge;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Left implements Runnable{

    private final Stack left;
    private final Stack right;
    private final boolean semaphore [];
    
    public Left (Stack leftstack, Stack rightstack, boolean semaphore []){
        this.left = leftstack;
        this.right = rightstack;
        this.semaphore = semaphore;
    }
    
    @Override
    public void run() {
        while (true) {        
            try {
                crossing();
            } catch (InterruptedException ex) {
                Logger.getLogger(Right.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//run
    
    private void crossing() throws InterruptedException {
        
        synchronized (left){
            while(!semaphore[0]){
                if(left.empty()){
                    System.out.println("Changing traffic lights to right...");
                    semaphore[0]=true;
                    synchronized (right){
                        right.notifyAll();
                    }
                }else{
                    left.pop();
                    System.out.println("Car on the left crossing...");
                    Thread.sleep(2000);
                    System.out.println("Car on the left crossed");
                    System.out.println("Total cars in the left lane: " + left.size());
                    left.wait(1000); 
                }
            } 
            left.wait();
        }
        
    }//driving
}//class