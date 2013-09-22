import tester.Tester;

// to represent a pet owner
class Person {
    String name;
    Pet pet;
    int age;
 
    Person(String name, Pet pet, int age) {
        this.name = name;
        this.pet = pet;
        this.age = age;
    }
    
    /* TEMPLATE FOR PERSON
     * 
     * Fields:
     * ... this.name ...                 -- String
     * ... this.pet ...                  -- Pet
     * ... this.age ...                  -- int
     * 
     * */
    
    // is this person older than the given person?
    boolean isOlder(Person other) {
       return this.age > other.age;
    }
}

// to represent a pet
interface Pet {
    boolean isCalled(String name);
}
 
// to represent a pet cat
class Cat implements Pet {
    String name;
    String kind;
    boolean longhaired;
 
    Cat(String name, String kind, boolean longhaired) {
        this.name = name;
        this.kind = kind;
        this.longhaired = longhaired;
    }

    public boolean isCalled(String guessName) {
        return this.name.equals(guessName);
    }
    
    /* TEMPLATE FOR CAT
     * 
     * Fields:
     * ... this.name ...                     -- String
     * ... this.kind ...                     -- String
     * ... this.longhaired ...               -- Boolean
     * 
     * Methods for fields:
     * 
     * ... this.isCalled(String name) ..     -- Boolean
     * */

}
 
// to represent a pet dog
class Dog implements Pet {
    String name;
    String kind;
    boolean male;
 
    Dog(String name, String kind, boolean male) {
        this.name = name;
        this.kind = kind;
        this.male = male;
    }

    public boolean isCalled(String guessName) {
        return this.name.equals(guessName);
    }


    /* TEMPLATE FOR DOG
     * 
     * Fields:
     * ... this.name ...                 -- String
     * ... this.kind ...                 -- String
     * ... this.male ...                 -- Boolean
     * 
     * Methods for fields:
     * ... this.isCalled() ...           -- Boolean
     * 
     * */

}

class NoPet implements Pet {
    NoPet(){}

    public boolean isCalled(String name) {
        return false;
    };
}


class ExamplesPetOwners {
    ExamplesPetOwners() {}
    
    Pet daisy = new Dog("Daisy", "Lab", false);
    Pet matty = new Dog("Matthew", "Golden", true);
    Pet sammy = new Cat("Sammy", "Tiger", true);
    Pet fluffy = new Cat("Fluffy", "Maine Coon", false);
    
    Person daisyOwner = new Person("David", this.daisy, 53);
    Person mattyOwner = new Person("Miranda", this.matty, 54);
    Person sammyOwner = new Person("Roger", this.sammy, 55);
    Person fluffyOwner = new Person("Jacob", this.fluffy, 56);
    
    // tests for method isOlder
    
    boolean testIsOlder(Tester t) {
        return
        t.checkExpect(this.daisyOwner.isOlder(this.sammyOwner), false) &&
        t.checkExpect(this.sammyOwner.isOlder(this.daisyOwner), true);
    }
    
    boolean testIsOlder(Tester t) {
        return
        t.checkExpect(this.daisyOwner.isOlder(this.sammyOwner), false) &&
        t.checkExpect(this.sammyOwner.isOlder(this.daisyOwner), true);
    }
}

