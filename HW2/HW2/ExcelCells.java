import tester.*;

public class ExcelCells {
    // comment for web-cat
}

// to represent a cartesian point
class CartPt {
    
    int x;
    int y;
    
    public CartPt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /* Template
     * 
     * Fields
     * ...this.x...             --int
     * ...this.y...             --int
     * 
     */
    
}



// to represent data in a cell
interface IData {
    
    // compute the value of this data
    int calculateVal();
    
    // is this data a number?
    boolean isNumber();
    
    // count the args included in the computation
    // of this data
    int countArgsData(ILoCell acc);
    
    
}


// to represent a number in a cell
class Number implements IData {
    
    int num;

    public Number(int num) {
        this.num = num;
    }
    
    /* Template
     * 
     * Fields
     * ...this.num...                   --int
     * 
     * Methods
     * ...this.calculateVal()...                --int
     * ...this.countArgsData(ILoCell acc)...    --int
     * ...this.isNumber()...                    --boolean
     * 
     */
    
    // compute the value of this data
    public int calculateVal() {
        return this.num; 
    }
    
    // is this data a number?
    public boolean isNumber() {
        return true; 
    }
    
    // count the args included in the computation
    // of this data
    public int countArgsData(ILoCell acc) {
        return 0;
    }
    
    
}

// to represent a formula in a cell
class Formula implements IData {
    
    String operator;
    Cell a;
    Cell b;
    
    public Formula(String operator, Cell a, Cell b) {
        this.operator = operator;
        this.a = a;
        this.b = b;
    }
    
    /* Template
     * 
     * Fields
     * 
     * ...this.operator...                  --String
     * ...this.a...                         --Cell
     * ...this.b...                         --Cell
     * 
     * Methods
     * ...this.calculateVal()...                --int
     * ...this.countArgsData(ILoCell acc)...    --int
     * ...this.isNumber()...                    --boolean
     * 
     * Methods for fields
     * ...this.a.value()...                     --int
     * ...this.b.value()...                     --int
     * 
     */
    
    // compute the value of this data
    public int calculateVal() {
        
        if (operator == "min") {
            return Math.min(a.value(), b.value());
        }
        
        else if (operator == "+") {
            return a.value() + b.value();
        }
        
        else if (operator == "*") {
            return a.value() * b.value();
        }
        
        else {
            throw new RuntimeException("Function not supported");
        }
    }
    
    
    // is this data a number? 
    public boolean isNumber() {
        return false;
    }
   
    // count the args included in the computation
    // of this data
    public int countArgsData(ILoCell acc) {
        return this.a.countArgs(acc) + this.b.countArgs(acc);
    }
    
    
    
}


// to represent a cell
class Cell {
    
    String col;
    int row;
    CartPt loc;
    IData data;
    
    public Cell(String col, int row, CartPt loc, IData data) {
        this.col = col;
        this.row = row;
        this.loc = loc;
        this.data = data;
    }
    
    /* Templates
     * 
     * Fields
     * 
     * ...this.row...           --int
     * ...this.col...           --String
     * ...this.loc...           --CartPt
     * ...this.data...          --IData 
     * 
     * Methods
     * ...this.value()...                   --int
     * ...this.countArgs(ILoCell acc)...    --int
     * 
     * Methods for fields
     * ...this.data.calculateVal()...                --int
     * ...this.data.isNumber()...                    --boolean
     * ...this.data.countArgsData(ILoCell acc)...    --int
     * 
     */
    
    // compute the value of this cell
    int value() {
        return this.data.calculateVal();
    }
    
    // compute the number of cells that contain numbers involved
    // in computing the value of this cell
    int countArgs(ILoCell acc) {
        if (this.data.isNumber() && !(acc.contains(this))) {
            return 1;
        }
        else {
            return this.data.countArgsData(acc);
                    
        }
    }
    
}


// to represent a list of cells
interface ILoCell {
    
    // does this list of cells contain the given cell
    boolean contains(Cell c);
    
}

// to represent an empty list of cells
class MtLoCell implements ILoCell {
    
    public MtLoCell() {
        // FU webcat
    }
    
    // does this list of cells contain the given cell
    public boolean contains(Cell c) {
        return false;
    }
    
    /*
     * Template
     * 
     * Methods:
     * ...this.contains(Cell c)...          --boolean
     * 
     * 
     */
}

// to represent a non-empty list of cells
class ConsLoCell implements ILoCell { 
    
    Cell first;
    ILoCell rest;
    
    public ConsLoCell(Cell first, ILoCell rest) {
        this.first = first;
        this.rest = rest;
    }
    
    /*
     * Template
     * 
     * Fields:
     * ...this.first...                 --Cell
     * ...this.rest...                  --ILoCell
     * 
     * Methods:
     * ...this.contains(Cell c)...      --boolean
     * 
     */
   
    
    // does this list of cells contain the given cell
    public boolean contains(Cell c) {
        return ((this.first.col == c.col) && (this.first.row == c.row)) ||
                 this.rest.contains(c);
    }
    
    
}

// store examples of cells
class ExamplesCells {
    
    CartPt pt1 = new CartPt(1, 1);
    CartPt pt2 = new CartPt(2, 1);
    
    IData nA1 = new Number(8);
    Cell a1 = new Cell("A", 1, this.pt1, new Number(8));
    
    IData nB1 = new Number(3);
    Cell b1 = new Cell("B", 1, this.pt2, new Number(3));
    
    IData nC1 = new Number(4);
    Cell c1 = new Cell("C", 1, new CartPt(3, 1), new Number(4));
    
    IData nD1 = new Number(6);
    Cell d1 = new Cell("D", 1, new CartPt(4, 1), new Number(6));
    
    IData nE1 = new Number(2);
    Cell e1 = new Cell("E", 1, new CartPt(5, 1), new Number(2));
    
    IData fA2 = new Formula("min", this.b1, this.e1);
    Cell a2 = new Cell("A", 2, new CartPt(1, 2), this.fA2);
    
    IData fB2 = new Formula("+", this.a1, this.c1);
    Cell b2 = new Cell("B", 2, new CartPt(2, 2), this.fB2);
    
    IData fE2 = new Formula("*", this.b2, this.d1);
    Cell e2 = new Cell("E", 2, new CartPt(5, 2), this.fE2);
    
    IData fA3 = new Formula("*", this.a1, this.a2);
    Cell a3 = new Cell("A", 3, new CartPt(1, 3), this.fA3);
    
    IData fB3 = new Formula("+", this.b2, this.e1);
    Cell b3 = new Cell("B", 3, new CartPt(2, 3), this.fB3);
    
    IData fE3 = new Formula("min", this.a3, this.d1);
    Cell e3 = new Cell("E", 3, new CartPt(5, 3), this.fE3);
    
    IData fB4 = new Formula("+", this.b3, this.b2);
    Cell b4 = new Cell("B", 4, new CartPt(2, 4), this.fB4);
    
    IData fE4 = new Formula("min", this.b3, this.e3);
    Cell e4 = new Cell("E", 4, new CartPt(5, 4), this.fE4);
    
    IData fB5 = new Formula("*", this.b4, this.b3);
    Cell b5 = new Cell("B", 5, new CartPt(2, 5), this.fB5);
    
    IData fE5 = new Formula("+", this.b5, this.e4);
    Cell e5 = new Cell("E", 5, new CartPt(5, 5), this.fE5);
    
    // examples of lists of cells
    ILoCell empty = new MtLoCell();
    
    ILoCell loc = new ConsLoCell(this.a1,
                                 new ConsLoCell(this.a2, this.empty));
    ILoCell loc2 = new ConsLoCell(this.a1,
                                  new ConsLoCell(this.b1, this.empty));
    
    
    
    // tests for method value()
    boolean testValue(Tester t) {
        return
        t.checkExpect(this.a1.value(), 8) &&
        t.checkExpect(this.b1.value(), 3) &&
        t.checkExpect(this.a2.value(), 2) &&
        t.checkExpect(this.e5.value(), 370) &&
        t.checkExpect(this.b5.value(), 364) &&
        t.checkExpect(this.b3.value(), 14);
    }
    
    
    // tests for method calculateVal()
    boolean testCalculateVal(Tester t) {
        return
        t.checkExpect(this.nA1.calculateVal(), 8) &&
        t.checkExpect(this.nB1.calculateVal(), 3) &&
        t.checkExpect(this.fA2.calculateVal(), 2) &&
        t.checkExpect(this.fB2.calculateVal(), 12) &&
        t.checkExpect(this.fE2.calculateVal(), 72) &&
        t.checkExpect(this.fA3.calculateVal(), 16) &&
        t.checkExpect(this.fB3.calculateVal(), 14) &&
        t.checkExpect(this.fE3.calculateVal(), 6);
    }
    
    
    // test the method contains()
    boolean testContains(Tester t) {
        return
        t.checkExpect(this.loc.contains(this.a1), true) &&
        t.checkExpect(this.loc2.contains(this.a1), true) &&
        t.checkExpect(this.empty.contains(this.a1), false);
    }
    
    // test the method countArgs()
    boolean testCountArgs(Tester t) {
        return
        t.checkExpect(this.a1.countArgs(this.empty), 1) &&
        t.checkExpect(this.a2.countArgs(this.empty), 2) &&
        t.checkExpect(this.a3.countArgs(this.empty), 3) &&
        t.checkExpect(this.b3.countArgs(this.empty), 3);
    }
    
}

