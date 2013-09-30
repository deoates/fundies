import tester.*;

public interface IBook {
    
    // number of days this book is overdue
    int daysOverdue(int today);
    
    // is this book overdue?
    boolean isOverdue(int date);

}


abstract class ABook implements IBook {
    
    String title;
    int dayTaken;
    
    public ABook(String title, int dayTaken) {
        this.title = title;
        this.dayTaken = dayTaken;
    }
    
    // number of days this book is overdue
    public abstract int daysOverdue(int today);
    
    // is this book overdue?
    public abstract boolean isOverdue(int date);
    
}

// to represent a book
class Book extends ABook {
    
    String author;
    private int borrowLength = 14;
    private double fine = .10;

    public Book(String title, int dayTaken, String author) {
        super(title, dayTaken);
        this.author = author;
    }
    
    // number of days this book is overdue
    public int daysOverdue(int today) {
        return today - (this.dayTaken + this.borrowLength);
    }

    // is this book overdue?
    public boolean isOverdue(int date) {
        return this.daysOverdue(date) > 0;
    }
    
}

// to represent a reference book
class RefBook extends ABook {
    
    private int borrowLength = 2;
    private double fine = .10;
    
    public RefBook(String title, int dayTaken) {
        super(title, dayTaken);
    }

    // number of days this reference book is overdue
    public int daysOverdue(int today) {
        return today - (this.dayTaken + this.borrowLength);
    }
    
    // is this book overdue?
    public boolean isOverdue(int date) {
        return this.daysOverdue(date) > 0;
    }

    
}

// to represent an audiobook
class AudioBook extends ABook {
    
    String author;
    private int borrowLength = 14;
    private double fine = .20;
    
    public AudioBook(String title, int dayTaken, String author) {
        super(title, dayTaken);
        this.author = author;
    }

    // number number of days this audiobook is overdue
    public int daysOverdue(int today) {
        return today - (this.dayTaken + this.borrowLength);
    }
    
    // is this book overdue?
    public boolean isOverdue(int date) {
        return this.daysOverdue(date) > 0;
    }
    
}

    


class ExamplesBooks {
    
    IBook gatsby = new Book("Great Gatsby", 4000, "Fitzgerald");
    IBook harryPotter = new AudioBook("Harry Potter", 4000, "Rowling");
    IBook encyclopedia = new RefBook("Encyclopedia", 4000);
    
    boolean testDaysOverdue(Tester t) {
        return
        t.checkExpect(this.gatsby.daysOverdue(4000), -14) &&
        t.checkExpect(this.gatsby.daysOverdue(4001), -13) &&
        t.checkExpect(this.gatsby.daysOverdue(4003), -11) &&
        t.checkExpect(this.harryPotter.daysOverdue(4020), 6) &&
        t.checkExpect(this.encyclopedia.daysOverdue(4020), 18);
    }
    
    boolean testIsOverdue(Tester t) {
        return 
        t.checkExpect(this.gatsby.isOverdue(5000), true) &&
        t.checkExpect(this.gatsby.isOverdue(4000), false);
    }
    
    
    
}


    



