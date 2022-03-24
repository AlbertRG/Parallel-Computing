package NorthPole;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Elf implements Runnable{

    private final Stack elves;
    private final Stack reindeer;
    
    public Elf(Stack elves, Stack reindeer){
        this.elves = elves;
        this.reindeer = reindeer;
    }
    
    @Override
    public void run() {
        while (reindeer.size() < 9) {        
            try {
                add();
            } catch (InterruptedException ex) {
                Logger.getLogger(Elf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Santa has already left to deliver gifts");
    }//run

    private void add() throws InterruptedException {
       
        Random rand = new Random();
        int add;
        System.out.println("Checking for new elves or reindeers...");
        
        switch (rand.nextInt(5)) {
            
            //Add REINDEER 40%
            case 0,1:
                synchronized (reindeer) {
                    System.out.println("New reindeer returned from holidays");
                    add = reindeer.size();
                    reindeer.add( add + 1 );
                    System.out.println("Total reindeer that have arrived: " + reindeer.size());
                    if(reindeer.size() == 9){
                        synchronized (elves) {
                            elves.notifyAll();
                        }
                    }
                    reindeer.wait(2000);
                }
                break;
            
            //Add ELF 60%
            case 2,3,4:
                synchronized (elves){
                    System.out.println("New elf needs helps with a toy");
                    add = elves.size();
                    elves.add( add + 1 );
                    System.out.println("Total elves waiting: " + elves.size());
                    if(elves.size() >= 3){
                        elves.notifyAll();
                    }
                    elves.wait(2000);
                }
                break;
                
            default:
                throw new AssertionError();
        }
        
    }//work
}//class
