package NorthPole;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

//Parallel Computing

//Santa Claus spends his downtime sleeping in his North Pole home.
//In order to wake it up, one of the following two conditions must be met:
//1. That all the reindeer you have, nine in all, have returned from holidays.
//2. That some of his elves need his help to make a toy.
//To allow Santa Claus to rest, the elves have agreed wake him up if three of them have trouble making a toy. 
//On the case that a group of three elves are being helped by Santa, 
//the rest of the troubled elves will have to wait for Santa to finish help the first group.
//In case there are elves waiting and all the reindeer have returned from holidays, 
//then Santa Claus will decide to prepare the sleigh and distribute the gifts, 
//since their delivery is more important than the manufacture of other toys that could wait for the following year.
//The last reindeer to arrive has to wake up Santa while the rest of the reindeer wait before being hitched to the sleigh.

import java.util.Stack;

public class NorthPole {

    public static void main(String[] args) {
        
        Stack elves = new Stack();
        Stack reindeer =  new Stack(); 
        
        Thread santa = new Thread (new Santa (elves, reindeer) );
        Thread add = new Thread (new Elf (elves, reindeer) );
        
        santa.start();
        add.start();
    }//main
}//class
