

// to represent a person

public class Person {
    String name;
    int yob;
    String state;
    boolean citizen;
    
    // constructor for a Person
    Person(String name, int yob, String state, boolean citizen) {
        this.name = name;
        this.yob = yob;
        this.state = state;
        this.citizen = citizen
    }
    
    
}


// examples of class Person

class ExamplesPerson {
    Person david = new Person("David", 1993, "NC", true);
    Person mary = new Person("Mary", 1994, "NY", true);
    Person alonzo = new Person("Alonzo", 1995, "CA", false);
}
    





