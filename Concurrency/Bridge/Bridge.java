package Bridge;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

//Parallel Computing

//Suppose a bridge has a single lane road along which cars can go one way or the other
//The width of the lane does it is impossible for two cars to pass simultaneously over the bridge
//The protocol used to traverse the bridge is as follows:
//• If there is no car driving on the bridge, then the first car on arrival you will cross the bridge
//• If a car is crossing the bridge from north to south, then the cars that are at the north end of the bridge 
//  will have priority over those that are go cross it from the south end
//• Likewise, if a car is crossing from south to north, then the cars from the southern end will have priority 
//  over those from the north

import java.util.Stack;

public class Bridge {

    public static void main(String[] args) {
        
        Stack rightstack = new Stack();
        Stack leftstack = new Stack();
        boolean[] semaphore = new boolean[] {true};
        
        //We start the program with 5 cars in each lane road
        for(int i = 1; i < 6; i++){
            rightstack.add(i);
            leftstack.add(i);
        }
        
        //Create the threads for each lane road and one to create new cars
        Thread right = new Thread (new Right (rightstack, leftstack, semaphore) );
        Thread left = new Thread (new Left (leftstack, rightstack, semaphore) );
        Thread newcar = new Thread (new NewCar (rightstack, leftstack) );
        
        right.start();
        left.start();
        newcar.start();
        
    }//main
}//class