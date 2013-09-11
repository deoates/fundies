import tester.*;
import java.util.*;

// 6 September 2013

// a class for defining simple mathematical functions (methods)
class Algorithms {
    
    // compute the location of the car, 
    // given its location at the start 
    // given in pixels on the screen 
    // the speed at which the car is traveling 
    // given in pixels per clock tick
    // and the elapsed time, given in clock ticks
    static int carMove(int startLoc, int speed, int timeElapsed) {
        return startLoc + speed * timeElapsed;                                                               
    }
    
    // compute the height of the rocket after the next tick, 
    // given its current height 
    // and its seed in pixels per clock tick
    static int nextRocket(int startLoc, int speed) {
        if (speed - startLoc >= 0) {
            return 0;
        }
        else {
            return startLoc - speed; 
        }
    }
    
    static double circleArea(int rad) {
        return Math.PI*(rad^2);
    }
    
    static double circlePerimeter(int rad) {
       return 2*rad*Math.PI;
    }
} 

// a class to test the functions(methods) 
// defined in the Algorithms class
class ExamplesAlgorithms {
    ExamplesAlgorithms() { }
    
    // test the method carMove:                   
    boolean testCarLoc(Tester t) {
        return
        t.checkExpect(Algorithms.carMove(20, 5, 50), 270,
                      "starting at 20, at a speed 5, after 50 ticks:") && 
        t.checkExpect(Algorithms.carMove(10, 3, 60), 190,
                      "starting at 10, at a speed 3, after 60 ticks:"); 
    }
    
    // test the method nextRocket:
    boolean testNextRocket(Tester t) {
        return
        t.checkExpect(Algorithms.nextRocket(100, 5), 95,
                      "starts at 100, speed 5, the rocket descends to 95:") &&  
        t.checkExpect(Algorithms.nextRocket(0, 5), 0,
                      "starts at 0, speed 5, the rocket remains at 0:") &&  
        t.checkExpect(Algorithms.nextRocket(20, 20), 0,
                      "starts at 20, speed 20, the rocket lands at 0:") && 
        t.checkExpect(Algorithms.nextRocket(15, 20), 0,
                      "starts at 15, speed 20, the rocket lands at 0:"); 
    }
   
    // test the method circleArea
    boolean testCircleArea(Tester t){
        return
        t.checkInexact(Algorithms.circleArea(1), Math.PI*1, 0) &&
        t.checkInexact(Algorithms.circleArea(2), Math.PI*4, 0);
    }
    
    // test the method CirclePerimeter
    boolean testCirclePerimeter(Tester t){
        return
        t.checkInexact(Algorithms.circlePerimeter(1), Math.PI*2, 0) &&
        t.checkInexact(Algorithms.circlePerimeter(2), Math.PI*4, 0);
    }
}