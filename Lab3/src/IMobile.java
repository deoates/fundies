import tester.*;

// to represent mobiles
public interface IMobile {
    
    // calculate weight of this mobile
    public int totalWeight();
    
    // calculate height of this mobile
    public int totalHeight();
   
    // is this mobile balanced?
    public boolean isBalanced();

}

// to represent a simple mobile
class Simple implements IMobile {
    int length;
    int weight;
    String color;
    
    public Simple(int length, int weight, String color) {
        this.length = length;
        this.weight = weight;
        this.color = color;
    }

    // calculate weight of this mobile
    public int totalWeight() {
        return this.weight;
    }

    // calculate height of this mobile
    public int totalHeight() {
        return this.length + this.weight;
    }

    // is this mobile balanced?
    public boolean isBalanced() {
        return true;
    }
    
    /* Template for Simple
     * 
     * Fields:
     * ...this.length...            --int
     * ...this.weight...            --int
     * ...this.color...             --String
     */
    
}


// to represent a complex mobile
class Complex implements IMobile {
    int length;
    int leftside;
    int rightside;
    IMobile left;
    IMobile right;
    
    public Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
        this.length = length;
        this.leftside = leftside;
        this.rightside = rightside;
        this.left = left;
        this.right = right;
    }

    // calculate weight of this mobile
    public int totalWeight() {
        return this.left.totalWeight() + this.right.totalWeight();
    }

    // calculate height of this mobile
    public int totalHeight() {
        return this.length +
               this.left.totalHeight() + this.right.totalHeight();
    }

    // is this mobile balanced?
    public boolean isBalanced() {
        return this.left.isBalanced() &&
               this.right.isBalanced() &&
               (this.left.totalWeight() * this.rightside) == 
               (this.right.totalWeight() * this.leftside);
    }
    
    /* Template for Complex
     * 
     * Fields
     * ...this.length...            --int
     * ...this.leftside...          --int
     * ...this.rightside...         --int
     * ...this.left...              --IMobile
     * ...this.right...             --IMobile
     * 
     */
}


class ExamplesMobile {
    
    IMobile blueSimple = new Simple(2, 10, "blue");
    IMobile redSimple = new Simple(1, 10, "red");
    IMobile greenSimple = new Simple(3, 40, "green");
    
    IMobile redBlueComplex = new Complex(1, 6, 6, redSimple, blueSimple);
    IMobile rgbComplex = new Complex(3, 12, 5, redBlueComplex, greenSimple);
    
    // tests for method totalWeight
    boolean testTotalWeight(Tester t) {
        return
        t.checkExpect(blueSimple.totalWeight(), 10) &&
        t.checkExpect(redBlueComplex.totalWeight(), 20) &&
        t.checkExpect(rgbComplex.totalWeight(), 60);
    }
    
    // tests for method totalHeight
    boolean testTotalHeight(Tester t) {
        return
        t.checkExpect(blueSimple.totalHeight(), 12) &&
        t.checkExpect(redBlueComplex.totalHeight(), 24) &&
        t.checkExpect(rgbComplex.totalHeight(), 70);
    }
    
    // tests for method isBalanced
    boolean testIsBalanced(Tester t) {
        return
        t.checkExpect(blueSimple.isBalanced(), true) &&
        t.checkExpect(redBlueComplex.isBalanced(), true) &&
        t.checkExpect(rgbComplex.isBalanced(), false);
    }
    
}
