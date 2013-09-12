
// inferface for a pizza

interface IPizza { } 

// to represent a plain pizza
class Plain implements IPizza {
    String crust;
    String cheese;
    
    // constructor for a Plain
    Plain(String crust, String cheese) {
        this.crust = crust;
        this.cheese = cheese;
    }
}


// to represent a fancy pizza
class Fancy implements IPizza {
    IPizza base;
    String topping;
   
    // constructor for a Fancy
    Fancy(IPizza base, String topping) {
        this.base = base;
        this.topping = topping;
    }
}


// to hold examples for Pizza
class ExamplesPizza {
    
    // examples for thin crust pizza
    IPizza thinCrustBase = new Plain("thin crust", "mozzarella");
    IPizza addMushrooms = new Fancy(thinCrustBase, "mushrooms");
    IPizza order1 = new Fancy(addMushrooms, "olives");
    
    // examples for deep dish pizza
    IPizza deepDishBase = new Plain("deep dish", "mixed");
    IPizza addPepperoni = new Fancy(deepDishBase, "pepperoni");
    IPizza order2 = new Fancy(addPepperoni, "onions");
    
}


