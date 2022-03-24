package Bridge;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewCar implements Runnable{

    private final Stack rightstack;
    private final Stack leftstack;
    
    public NewCar (Stack rightstack, Stack leftstack){
        this.rightstack = rightstack;
        this.leftstack = leftstack;
    }
    
    @Override
    public void run() {
        while (true) {        
            try {
                add();
            } catch (InterruptedException ex) {
                Logger.getLogger(NewCar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//run
    
    private void add() throws InterruptedException {
        
        Random rand = new Random();
        int add;
        System.out.println("Checking for new cars in the queue...");
        
        switch (rand.nextInt(3)) {
            
            //Add LEFT
            case 0:
                synchronized (leftstack) {
                    System.out.println("New car arrived on the left");
                    add = leftstack.size();
                    leftstack.add( add + 1 );
                    System.out.println("Total cars in the left lane: " + leftstack.size());
                    leftstack.wait(2000);
                }
                break;
            
            //Add RIGHT
            case 1:
                synchronized (rightstack){
                    System.out.println("New car arrived on the right");
                    add = rightstack.size();
                    rightstack.add( add + 1 );
                    System.out.println("Total cars in the right lane: " + rightstack.size());
                    rightstack.wait(2000);
                }
                break;
                
            //Add NONE
            case 2:
                System.out.println("No car has arrived");
                Thread.sleep(2000);
                break;
                
            default:
                throw new AssertionError();
        }
        
    }//add
}//class