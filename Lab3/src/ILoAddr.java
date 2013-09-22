import tester.*;

// to represent a list of addresses
public interface ILoAddr {
  
    // count Addresses in given state
    public int countInState(String state);
    
    // produce list of addresses in given state
    public ILoAddr citiesInState(String state);
    
    // produce list of addresses sorted by state
    public ILoAddr sortStates();
    
    // produce list of addresses sorted by city
    public ILoAddr sortCities();
    
    // insert given address to this list
    public ILoAddr insertSortedState(Address a);

    // insert given address to this list
    public ILoAddr insertSortedCity(Address a);
    
}


// to represent an empty list of addresses
class MtLoAddr implements ILoAddr {
    public MtLoAddr(){}

    // count Addresses in given state
    public int countInState(String state) {
        return 0;
    }

    // produce list of addresses in given state
    public ILoAddr citiesInState(String state) {
        return this;
    }

    // produce list of addresses sorted by state
    public ILoAddr sortStates() {
        return this;
    }

    // produce list of addresses sorted by city
    public ILoAddr sortCities() {
        return this;
    }
    
    // insert given address in the correct location in this sorted list
    public ILoAddr insertSortedState(Address a) {
        return new ConsLoAddr(a, this);
    }
    
    // insert given address in the correct location in this sorted list
    public ILoAddr insertSortedCity(Address a) {
        return new ConsLoAddr(a, this);
    }
}

// to represent a non-empoty list of addresses
class ConsLoAddr implements ILoAddr {
    Address first;
    ILoAddr rest;
    
    public ConsLoAddr(Address first, ILoAddr rest) {
        this.first = first;
        this.rest = rest;
    }

    // count Addresses in given state
    public int countInState(String state) {
        if (this.first.state == state) {
            return 1 + this.rest.countInState(state);
        } else {
            return this.rest.countInState(state);
        }
    }

    // produce list of addresses in given state
    public ILoAddr citiesInState(String state) {
        if (this.first.state == state) { 
            return new ConsLoAddr(this.first, this.rest.citiesInState(state));
        } else {
            return this.rest.citiesInState(state);
        }
    }

    // produce list of addresses sorted by state
    public ILoAddr sortStates() {
        return this.rest.sortStates().insertSortedState(this.first);
        
    }

    // produce list of addresses sorted by city
    public ILoAddr sortCities() {
        // TODO Auto-generated method stub
        return null;
    }

    // insert given address to this list sorted by state
    public ILoAddr insertSortedState(Address a) {
        if (this.first.state.compareTo(a.state) > 0) {
            return new ConsLoAddr(a, this);
        } else {
            return new ConsLoAddr(this.first, this.rest.insertSortedState(a));
        }
                
    }
    
    // insert given address to this list sorted by city
    public ILoAddr insertSortedCity(Address a) {
        if (this.first.state.compareTo(a.city) > 0) {
            return new ConsLoAddr(a, this);
        } else {
            return new ConsLoAddr(this.first, this.rest.insertSortedCity(a));
        }
                
    }
    
}

// to represent an address
class Address {
    String city;
    String state;
    public Address(String city, String state){
        this.city = city;
        this.state = state;
    }
}


class ExamplesAddress {
    
    Address boston = new Address("Boston", "MA");
    Address memphis = new Address("Memphis", "TN");
    Address charlotte = new Address("Charlotte", "NC");
    Address austin = new Address("Austin", "TX");
    
    ILoAddr empty = new MtLoAddr();
    
    ILoAddr northEast = new ConsLoAddr(this.boston, this.empty);
    ILoAddr eastCoast = new ConsLoAddr(this.boston, new ConsLoAddr(this.charlotte, this.empty));
    
    ILoAddr usa = new ConsLoAddr(this.boston,
                      new ConsLoAddr(this.memphis,
                          new ConsLoAddr(this.charlotte,
                              new ConsLoAddr(this.austin, this.empty))));
    
   
    boolean testCountInState(Tester t){
        return
        t.checkExpect(this.empty.countInState("NC"), 0) &&
        t.checkExpect(this.usa.countInState("MA"), 1) &&
        t.checkExpect(this.usa.countInState("CA"), 0);
        
    }
    
    boolean testInsert(Tester t){
        return
        t.checkExpect(this.empty.insertSortedState(this.boston),
                      new ConsLoAddr(this.boston, this.empty)) &&
        t.checkExpect(this.northEast.insertSortedState(this.charlotte), this.eastCoast);
        
    }
    
}
