import tester.*;


// Lab 1: Data definitions for simple classes and classes with containment


/*
 +--------------+ 
 | Circle       | 
 +--------------+ 
 | CartPt loc   |--+
 | int rad      |  |
 | String color |  |
 +--------------+  |
                   v
              +--------+
              | CartPt | 
              +--------+
              | int x  |
              | int y  |
              +--------+
 */

// to represent a circle
class Circle {
    CartPt loc;
    int rad;
    String color;
    
    Circle(CartPt loc, int rad, String color) {
        this.loc = loc;
        this.rad = rad;
        this.color = color;
    }
}   

// to represent a Cartesian point in a plane
class CartPt {
    int x;
    int y;
    
    CartPt(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// to represent a rectangle
class Rectangle {
    int width;
    int height;
    String color;
    CartPt loc;
    
    //constructor for Rectangle
    Rectangle(int width, int height, String color, CartPt loc) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.loc = loc;
    }
}

// examples for the classes CartPt and Circle
class ExamplesShapes {
    
    // make examples of CartPt-s
    CartPt p50x50y = new CartPt(50, 50);
    CartPt p20x40y = new CartPt(20, 40);
    CartPt p30x40y = new CartPt(30, 40);
    
    // make examples of Circle-s
    Circle circle1 = new Circle(new CartPt(50, 50), 50, "red");
    Circle circle2 = new Circle(new CartPt(20, 40), 10, "green");
    Circle circle3 = new Circle(new CartPt(30, 40), 20, "blue");
    
    Rectangle rect1 = new Rectangle(10, 10, "green", new CartPt(50, 50));
    Rectangle rect2 = new Rectangle(10, 20, "red", new CartPt(20, 40));
       
    
    // test the data for the class Circle
    boolean testCircles(Tester t) {
        return 
        t.checkExpect(this.circle1, new Circle(this.p50x50y, 50, "red")) &&
        t.checkExpect(this.circle2, new Circle(this.p20x40y, 10, "green")) &&
        t.checkExpect(this.circle3, new Circle(this.p30x40y, 20, "blue"));
    }
    
    // test the data for the class Rectangle
    boolean testRectangles(Tester t) {
        return 
        t.checkExpect(this.rect1, new Rectangle(10, 10, "green",
                                                            this.p50x50y)) &&
        t.checkExpect(this.rect2, new Rectangle(10, 20, "red", this.p20x40y));
    }

}