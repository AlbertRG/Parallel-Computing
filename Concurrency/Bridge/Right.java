package Bridge;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Right implements Runnable{

    private final Stack right;
    private final Stack left;
    private final boolean semaphore[];
    
    public Right (Stack rightstack, Stack leftstack, boolean semaphore[]){
        this.right = rightstack;
        this.left = leftstack;
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
        
        synchronized (right){
            while(semaphore[0]){
                if(right.empty()){
                    System.out.println("Changing traffic lights to left...");
                    semaphore[0]=false;
                    synchronized (left){
                        left.notifyAll();
                    }
                }else{
                    right.pop();
                    System.out.println("Car on the right crossing...");
                    Thread.sleep(2000);
                    System.out.println("Car on the right crossed");
                    System.out.println("Total cars in the right lane: " + right.size());
                    right.wait(1000); 
                }
            }
            right.wait();
        }
        
    }//driving
}//class