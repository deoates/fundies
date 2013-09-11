
interface IPizza {}

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


// Ð a "deep dish" pizza with "mixed" cheese, with topping of "pepperoni", and "onions"

class ExamplesPizza {
    
    // examples for thin crust pizza
    IPizza thinCrustBase = new Plain("thin crust", "mozzarella");
    IPizza addMushrooms = new Fancy(thinCrustBase, "mushrooms");
    IPizza order1 = new Fancy(addMushrooms, "olives");
    
    // examples for deep dish pizza
    IPizza deepDishBase = new Plain("deep dish", "mixed");
    IPizza addPepperoni = new Fancy(deepDishBase, "pepperoni");
    IPizza order2 = new Fancy(addMushrooms, "onions");
    
}
