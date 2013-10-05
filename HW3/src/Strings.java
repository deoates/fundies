import tester.*;

// to represent a list of Strings
interface ILoS {
        
    // combine all Strings in this list into one
    String combine();
    
    // is this list of strings sorted in alphabetical order?
    boolean isSorted();
    
    // helper method, does the given String
    // lexicographically precede the next item in this list
    boolean precedesList(String s);
    
    // merge two given lists of Strings
    ILoS merge(ILoS that);
    
}

// to represent an empty list of Strings
class MtLoS implements ILoS {
        
    MtLoS() {
        // empty constructor
    }
    
    /* 
     * Template
     * 
     * Methods
     * ...this.combine()...                     -- String
     * ...this.isSorted()...                    -- boolean
     * ...this.precedesList(String s)...        -- boolean
     * ...this.merge(ILoS that)                 -- ILoS
     * 
     */
    
    
    // combine all Strings in this list into one
    public String combine() {
        return "";
    }
    
    
    // is this list of strings sorted in alphabetical order?
    public boolean isSorted() {
        return true;
    }

    // helper method, does the given String
    // lexicographically precede the next item in this list
    public boolean precedesList(String s) {
        return true;
    }
    
    // merge this list with the given list
    public ILoS merge(ILoS that) {
        return that;
    }
    
    
}

// to represent a nonempty list of Strings
class ConsLoS implements ILoS {
        
    String first;
    ILoS rest;
    
    ConsLoS(String first, ILoS rest) {
        this.first = first;
        this.rest = rest;    
    }

 /*
    TEMPLATE
    FIELDS:
    ... this.first ...                   -- String
    ... this.rest ...                    -- ILoS
    
    METHODS
    ... this.combine() ...               -- String
    ... this.isSorted() ...              -- boolean
    ... this.precedesList(String s) ...  -- boolean
    ... this.merge(ILoS that) ...        -- boolean
    
    METHODS FOR FIELDS
    ... this.first.concat(String) ...    -- String
    ... this.first.compareTo(String) ... -- int
    ... this.rest.combine() ...          -- String
    ... this.rest.isSorted() ...         -- boolean
    ... thsi.rest.precedesList(string)   -- boolean
    
    */
    
    // combine all Strings in this list into one
    public String combine() {
        return this.first.concat(this.rest.combine());
    }
    
    
    // is this list of strings sorted in alphabetical order?
    public boolean isSorted() {
        return this.rest.precedesList(this.first) &&
               this.rest.isSorted();
    }
    
    // helper method, does the given String
    // lexicographically precede the next item in this list
    public boolean precedesList(String s) {
        return this.first.compareTo(s) >= 0;
    }
    
    // merge this list with the given list
    public ILoS merge(ILoS that) {
        if (that.precedesList(this.first)) {
            return new ConsLoS(this.first, this.rest.merge(that));
        }
        else {
            return that.merge(this);
        }
    }

}

// to represent examples for lists of strings
class ExamplesStrings {

    ExamplesStrings() {
        // empty constructor
    }

    ILoS empty = new MtLoS();

    ILoS mary = new ConsLoS("Mary ",
                    new ConsLoS("had ",
                        new ConsLoS("a ",
                            new ConsLoS("little ",
                                new ConsLoS("lamb.", this.empty)))));

    ILoS sorted = new ConsLoS("a",
                      new ConsLoS("b",
                          new ConsLoS("c", this.empty)));
    
    ILoS sorted2 = new ConsLoS("c",
                       new ConsLoS("g",
                           new ConsLoS("h", this.empty)));
    
    
    ILoS notSorted = new ConsLoS("d",
                         new ConsLoS("f", 
                             new ConsLoS("e", this.empty)));
    
    ILoS merged = new ConsLoS("a",
                      new ConsLoS("b",
                          new ConsLoS("c",
                              new ConsLoS("c",
                                  new ConsLoS("g",
                                      new ConsLoS("h", this.empty))))));


    // test for method combine()
    boolean testCombine(Tester t) {
        return 
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.") &&
        t.checkExpect(this.sorted.combine(), "abc");
    }
    
    // tests for method isSorted()
    boolean testIsSorted(Tester t) {
        return
        t.checkExpect(this.mary.isSorted(), false) &&
        t.checkExpect(this.sorted.isSorted(), true) &&
        t.checkExpect(this.notSorted.isSorted(), false);
    }
    
    // tests for helper method precedesList(String s)
    boolean testPrecedesList(Tester t) {
        return
        t.checkExpect(this.sorted.precedesList("b"), false) &&
        t.checkExpect(this.sorted.precedesList("a"), true) &&
        t.checkExpect(this.mary.precedesList("Mary "), true) &&
        t.checkExpect(this.notSorted.precedesList("a"), true);
    }
    
    // tests for method merge()
    boolean testMerge(Tester t) {
        return
        t.checkExpect(this.empty.merge(this.sorted), this.sorted) &&
        t.checkExpect(this.sorted.merge(this.empty), this.sorted) &&
        t.checkExpect(this.sorted.merge(this.sorted2), this.merged);
    }

}