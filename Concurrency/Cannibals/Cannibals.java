package Cannibals;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

//Parallel Computing

//In a tribe of cannibals everyone eats from the same pot, which can hold 8 servings of food
//When a cannibal wants to eat, he simply served himself to the common pot, unless it is empty
//In that case, the cannibal wakes up the tribe's cook and waits until he has filled the pot

import java.util.Stack;

public class Cannibals {

    public static void main (String args[]) {
        
        Stack cookingpot = new Stack ();
        
        //Create the threads for 1 cook and 4 cannibals
        Thread cook = new Thread (new Cook (cookingpot) );
        Thread cannibal1 = new Thread (new Cannibal (cookingpot, "Cannibal 1") );
        Thread cannibal2 = new Thread (new Cannibal (cookingpot, "Cannibal 2") );
        Thread cannibal3 = new Thread (new Cannibal (cookingpot, "Cannibal 3") );
        Thread cannibal4 = new Thread (new Cannibal (cookingpot, "Cannibal 4") );
        cook.setPriority(8);
        
        cook.start ();
        cannibal1.start ();
        cannibal2.start ();
        cannibal3.start ();
        cannibal4.start ();
        
    }//main
}//class