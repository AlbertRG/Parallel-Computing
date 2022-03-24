package Cannibals;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cannibal implements Runnable{

    private final Stack cookingpot;
    private final String name;

    public Cannibal (Stack cookingpot, String name) {
        this.cookingpot = cookingpot;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                eating();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cannibal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//run

    private void eating() throws InterruptedException {
        
        //If the cookingpot IS empty notify cook and wait
        synchronized (cookingpot) {
            if (cookingpot.isEmpty()) {
                System.out.println("The pot is empty, " + name + " will notify the cook and wait");
                cookingpot.notifyAll();
                cookingpot.wait();
            }else{
                System.out.println(name + " eat: " + cookingpot.pop());
            }
        }
        
    }//eating
}//class