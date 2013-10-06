import tester.*;

// to represent a file

abstract class AFile {
    
    String name;
    String owner;
    
    AFile(String name, String owner) { 
        this.name = name;
        this.owner = owner;
    }
    
    // compute the size of this file
    public abstract int size();
    
    // compute the time (in seconds) to download this file
    // at the given download rate
    public int downloadTime(int rate) {
        return this.size() / rate;
    }
    
    // is the owner of this file the same 
    // as the owner of the given file?
    public boolean sameOwner(AFile that) {
        return this.owner == that.owner;
    }
    
}

// to represent a text file
class TextFile extends AFile {
    
    int length;   // in bytes
    
    public TextFile(String name, String owner, int length) {
        super(name, owner);
        this.length = length;
    }
  
  
    // compute the size of this file
    public int size() {
        return this.length;
    }  
  
    
}

//to represent an image file
class ImageFile extends AFile {
    
    String name;
    String owner;
    int width;   // in pixels
    int height;  // in pixels
  
    ImageFile(String name, String owner, int width, int height) {
        super(name, owner);
        this.width = width;
        this.height = height;
    }
  
    // compute the size of this file
    public int size() {
        return this.width * this.height;
    }  
  
}


//to represent an audio file
class AudioFile extends AFile {
    
    int speed;   // in bytes per second
    int length;  // in seconds of recording time
    
    AudioFile(String name, String owner, int speed, int length) {
        super(name, owner);
        this.speed = speed;
        this.length = length;
    }
  
    // compute the size of this file
    public int size() {
        return this.speed * this.length;
    }  
  
}

// to hold examples of files
class ExamplesFiles {
    
    ExamplesFiles() {
        // empty constructor
    }
  
    // examples of files
    AFile text1 = new TextFile("English paper", "Maria", 1234);
    AFile picture = new ImageFile("Beach", "Maria", 400, 200);
    AFile song = new AudioFile("Help", "Pat", 200, 120);
    
    AFile text2 = new TextFile("Spanish paper", "David", 1000);
    AFile picture2 = new ImageFile("Sunset", "Maria", 300, 300);
    AFile song2 = new AudioFile("Audiobook", "Pat", 400, 150);
  
  
    // test the method size for the classes that represent files
    boolean testSize(Tester t) {
        return
        t.checkExpect(this.text1.size(), 1234) &&
        t.checkExpect(this.picture.size(), 80000) &&
        t.checkExpect(this.song.size(), 24000) &&
        t.checkExpect(this.text2.size(), 1000) &&
        t.checkExpect(this.picture2.size(), 90000) &&
        t.checkExpect(this.song2.size(), 60000);
    }
  
    // test the method downloadTime()
    boolean testDownloadTime(Tester t) {
        return 
        t.checkExpect(this.text1.downloadTime(1), 1234) &&
        t.checkExpect(this.picture.downloadTime(2), 40000) &&
        t.checkExpect(this.song.downloadTime(100), 240) &&
        t.checkExpect(this.text2.downloadTime(10), 100) &&
        t.checkExpect(this.picture2.downloadTime(1), 90000) &&
        t.checkExpect(this.song2.downloadTime(1), 60000);
    }
  
    // test the method sameOwner()
    boolean testSameOwner(Tester t) {
        return 
        t.checkExpect(this.text1.sameOwner(this.text1), true) &&
        t.checkExpect(this.picture.sameOwner(this.text1), true) &&
        t.checkExpect(this.song.sameOwner(this.text1), false) &&
        t.checkExpect(this.text2.sameOwner(this.picture2), false) &&
        t.checkExpect(this.picture2.sameOwner(this.song2), false) &&
        t.checkExpect(this.song2.sameOwner(this.song2), true);
    }
  
}