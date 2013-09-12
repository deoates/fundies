
// inferface for a pizza

interface IPizza { } 

// to represent a plain pizza
class Plain implements IPizza {
    String crust;
    String cheese;
    
    // constructor 
    public Plain(String crust, String cheese) {
        this.crust = crust;
        this.cheese = cheese;
    }
}


// to represent a fancy pizza
class Fancy implements IPizza {
    IPizza base;
    String topping;
    
    public Fancy(IPizza base, String topping) {
        this.base = base;
        this.topping = topping;
    }
}


class ExamplesPizza {
    
    // examples for thin crust pizza
    Plain thinCrustBase = new Plain("thin crust", "mozzarella");
    Fancy addMushrooms = new Fancy(thinCrustBase, "mushrooms");
    Fancy order1 = new Fancy(addMushrooms, "olives");
    
    // examples for deep dish pizza
    Plain deepDishBase = new Plain("deep dish", "mixed");
    Fancy addPepperoni = new Fancy(deepDishBase, "pepperoni");
    Fancy order2 = new Fancy(addMushrooms, "onions");
    
}


