package Cannibals;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cook implements Runnable{

    private final Stack cookingpot;

    public Cook(Stack cookingpot) {
        this.cookingpot = cookingpot;
    }

    @Override
    public void run() {
        while (true) {        
            try {
                cooking();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cook.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//run

    private void cooking() throws InterruptedException {
        
        //If the cookingpot ISN'T empty wait
        synchronized (cookingpot) {
            while (!cookingpot.empty()) {
                System.out.println("The pot isn't empty, the cook waits...");

                cookingpot.wait();
            }
        }

        //The cook fills the pot, tells the others and waits for it to empty again
        synchronized (cookingpot) {
            
            System.out.println("Filling the pot for 8 servings...");
            
            for(int i = 1; i < 9; i++){
                cookingpot.add(i);
                System.out.println("Filling the pot... " + i );
                Thread.sleep(1000);
            }      
            cookingpot.notifyAll();
            cookingpot.wait();
        }
        
    }//cooking
}//class