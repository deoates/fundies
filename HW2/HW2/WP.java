import tester.*;


// to represent an item on a web page
interface Item {
   
    // get image size of this item
    int getImageSize();
    
    // get text length of this item
    int getTextLength();
    
    // get image title of this item
    String getImageTitle();
    
}

// to represent text on a web page
class Text implements Item {
    
    String contents;

    public Text(String contents) {
        this.contents = contents;
    }
    
    /* Template
     * 
     * Fields:
     * ...this.contents...              --String
     * 
     * Methods:
     * ...this.getImageSize()...        --int
     * ...this.getTextLength()...       --int
     * ...this.getImageTitle()...       --String
     * 
     */

    // get image size of this item
    public int getImageSize() {
        return 0;
    }
    
    // get text length of this item
    public int getTextLength() {
        return this.contents.length();
    }
    
    // get image title of this item
    public String getImageTitle() {
        return "";
    }
    
    
}


// to represent an image
class Image implements Item {
    
    String fileName;
    int size;
    String fileType;
    
    public Image(String fileName, int size, String fileType) {
        this.fileName = fileName;
        this.size = size;
        this.fileType = fileType;
    }
    
    /* Template
     * 
     * Fields:
     * ...this.fileName...              --String
     * ...this.size...                  --int
     * ...this.fileType...              --String
     * 
     * Methods:
     * ...this.getImageSize()...        --int
     * ...this.getTextLength()...       --int
     * ...this.getImageTitle()...       --String
     * 
     */
    
    // get image size of this item
    public int getImageSize() {
        return this.size;
    }
    
    // get text length of this item
    public int getTextLength() {
        return this.fileName.length();
    }
    
    // get image title of this item
    public String getImageTitle() {
        return this.fileName + "." + this.fileType;
    }
    
    
}


// to represent a link
class Link implements Item {
    
    String name;
    WP page;
    
    public Link(String name, WP page) {
        this.name = name;
        this.page = page;
    }
    
    /* Template
     * 
     * Fields:
     * ...this.name...               --String
     * ...this.page...               --WP
     * 
     * Methods:
     * ...this.getImageSize()...        --int
     * ...this.getTextLength()...       --int
     * ...this.getImageTitle()...       --String
     * 
     * Methods for fields:
     * ...this.page.images()...         --String
     * 
     */
    
    
    // get image size of this item
    public int getImageSize() {
        return this.page.totalImageSize();
    }
    
    // get text length of this item
    public int getTextLength() {
        return this.name.length() + 
               this.page.textLength();
    }
    
    // get image title of this item
    public String getImageTitle() {
        return this.page.images();
    }
    
}

// to represent a list of items
interface ILoItem {
    
    // compute size of all images in this list
    int totalImageSizeList();
    
    // compute total number of letters in the items of this list
    int textLengthList();
    
    // return string of images in this list
    String imagesList();
    
}

// to represent an empty list of items
class MtLoItem implements ILoItem {
    
    public MtLoItem() {
        // this is an empty constructor get over yourself Webcat
    }
    
    /* Template
     * 
     * Methods:
     * 
     * ...this.totalImageSizeList()...         --int
     * ...this.textLengthList()...             --int
     * ...this.imagesList()...                 --String
     * 
     */
    
    
    // compute size of all images in this list
    public int totalImageSizeList() {
        return 0;
    }
    
    // compute total number of letters in the items of this list
    public int textLengthList() {
        return 0;
    }
    
    // return string of images in this list
    public String imagesList() {
        return "";
    }
}

// to represent a non-empty list of items
class ConsLoItem implements ILoItem {
    
    Item first;
    ILoItem rest;
    
    public ConsLoItem(Item first, ILoItem rest) {
        this.first = first;
        this.rest = rest;
    }
    
    /* Template
     * 
     * Fields:
     * ...this.first...              --Item
     * ...this.rest...              --ILoItem
     * 
     * Methods:
     * ...this.totalImageSizeList()...         --int
     * ...this.textLengthList()...             --int
     * ...this.imagesList()...                 --String
     * 
     * Methods for fields:
     * ...this.first.getImageSize()...         --int
     * ...this.first.getTextLength()...        --int
     * ...this.first.getImageTitle()...        --String
     * 
     */
    
    // compute size of all images in this list
    public int totalImageSizeList() {
        return this.first.getImageSize() +
               this.rest.totalImageSizeList();
    }
    
    // compute total number of letters in the items of this list
    public int textLengthList() {
        return this.first.getTextLength() +
               this.rest.textLengthList();
    }
    
    // return string of images in this list
    public String imagesList() {
        
        if (this.rest.imagesList() == "") {
            return this.first.getImageTitle();
        }
        
        else if (this.first.getImageTitle() == "") {
            return this.rest.imagesList();
        }
        
        else {
            return this.first.getImageTitle() + ", " + this.rest.imagesList();
        }
                
    }
}


// to represent a web page
public class WP {
    
    String url;
    String title;
    ILoItem items;
    
    public WP(String url, String title, ILoItem items) {
        this.url = url;
        this.title = title;
        this.items = items;
    }
    
    /* Template
     * 
     * Fields:
     * ...this.url...               --String
     * ...this.title...             --String
     * ...this.items...             --ILoItems
     * 
     * Methods:
     * ...this.totalImageSize()...  --int
     * ...this.textLength()...      --int
     * ...this.images()...          --String
     * 
     * Methods for fields:
     * ...this.items.totalImageSizeList()...    --int
     * ...this.title.length()...                --int
     * ...this.items.textLengthList()...        --int
     * ...this.items.imagesList()...            --String
     * 
     */
   
    
    // compute total size of all images in this web page
    int totalImageSize() {
        return this.items.totalImageSizeList();
    }
    
    // compute total number of letters in this web page
    int textLength() {
        return this.title.length() +
               this.items.textLengthList();
    }
    
    // return string list of images in this web page
    String images() {
        return this.items.imagesList();
    }
    
}



// to represent examples of webpages

class ExamplesWP {
    
    ILoItem empty = new MtLoItem();
    
    
    // our examples
   
    Item babyMonkeyText = new Text("Image not found");
    ILoItem babyMonkeyItems = new ConsLoItem(this.babyMonkeyText, this.empty);
    WP babyMonkeyPage = new WP("babyMonkey.html", "Baby Monkey Website",
                                                   this.babyMonkeyItems);
    
    Item babyMonkeyLink = new Link("See baby monkeys", this.babyMonkeyPage);
    Item monkeyPic2 = new Image("monkeys2", 12, "png");
    
    ILoItem monkeyPageItems = new ConsLoItem(this.monkeyPic2,
                              new ConsLoItem(this.babyMonkeyLink, this.empty));
    
    WP monkeyPage = new WP("monkeys.html", "Monkeys", this.monkeyPageItems);
    
    Item monkeyPic = new Image("monkey", 10, "jpeg");
    Item monkeyCaption = new Text("Monkeys at the Zoo");
    Item monkeyPageLink = new Link("See more monkeys", this.monkeyPage);
    
    ILoItem monkeyWPItems = new ConsLoItem(this.monkeyPic, 
                                  new ConsLoItem(this.monkeyCaption,
                                      new ConsLoItem(this.monkeyPageLink,
                                          this.empty)));
    
    WP monkeyWP = new WP("index.html", "Monkeys Home", this.monkeyWPItems);
    
    
    // provided examples
    
    Item jackiePic = new Image("jackie", 300, "png");
    Item jackieText = new Text("My friend Jackie");
    
    ILoItem bobsFriendsItems = new ConsLoItem(this.jackieText, 
                               new ConsLoItem(this.jackiePic, this.empty));
    
    WP bobsFriends = new WP("bob-friends.org", "Bob's Friends",
                                               this.bobsFriendsItems);
    
    Item bobLink = new Link("Here are Bob's Friends", this.bobsFriends);
    Item kevinPic = new Image("kevin", 400, "png");
    Item kevinText = new Text("This is Kevin");
    Item anniePic = new Image("annie", 230, "jpeg");
    Item annieText = new Text("This is Annie");
    
    ILoItem friendsItems = new ConsLoItem(this.annieText, 
                            new ConsLoItem(this.anniePic,
                              new ConsLoItem(this.kevinText,
                                new ConsLoItem(this.kevinPic,
                                  new ConsLoItem(this.bobLink, this.empty)))));

    WP myWP = new WP("myfriends.org", "My Friends", this.friendsItems);


    // tests for method totalImageSize()
    boolean testTotalImageSize(Tester t) {
        return
        t.checkExpect(this.myWP.totalImageSize(), 930) &&
        t.checkExpect(this.monkeyWP.totalImageSize(), 22) &&
        t.checkExpect(this.babyMonkeyPage.totalImageSize(), 0);
    }
    
    // tests for method getImageSize()
    boolean testGetImageSize(Tester t) {
        return
        t.checkExpect(this.anniePic.getImageSize(), 230) &&
        t.checkExpect(this.annieText.getImageSize(), 0) &&
        t.checkExpect(this.monkeyCaption.getImageSize(), 0) &&
        t.checkExpect(this.monkeyPic.getImageSize(), 10) &&
        t.checkExpect(this.bobLink.getImageSize(), 300);
    }
    
    // tests for method totalImageSizeList()
    boolean testTotalImageSizeList(Tester t) {
        return
        t.checkExpect(this.friendsItems.totalImageSizeList(), 930) &&
        t.checkExpect(this.empty.totalImageSizeList(), 0) &&
        t.checkExpect(this.bobsFriendsItems.totalImageSizeList(), 300);
    }


    // tests for method textLength()
    boolean testTextLength(Tester t) {
        return
        t.checkExpect(this.babyMonkeyPage.textLength(), 34) &&
        t.checkExpect(this.monkeyPage.textLength(), 65) &&
        t.checkExpect(this.bobsFriends.textLength(), 35) &&
        t.checkExpect(this.myWP.textLength(), 103);
    }

    // tests for method textLength()
    boolean testTextLengthList(Tester t) {
        return
        t.checkExpect(this.bobsFriendsItems.textLengthList(), 22) &&
        t.checkExpect(this.babyMonkeyItems.textLengthList(), 15) &&
        t.checkExpect(this.empty.textLengthList(), 0);
    }

    // tests for method getTextLength()
    boolean testGetTextLength(Tester t) {
        return
        t.checkExpect(this.jackiePic.getTextLength(), 6) &&
        t.checkExpect(this.anniePic.getTextLength(), 5) &&
        t.checkExpect(this.kevinText.getTextLength(), 13) &&
        t.checkExpect(this.babyMonkeyLink.getTextLength(), 50);
        
    }
    
    // tests for method images()
    boolean testImages(Tester t) {
        return
        t.checkExpect(this.babyMonkeyPage.images(), "") &&
        t.checkExpect(this.monkeyPage.images(), "monkeys2.png") &&
        t.checkExpect(this.monkeyWP.images(), "monkey.jpeg, monkeys2.png") &&
        t.checkExpect(this.myWP.images(), 
                                        "annie.jpeg, kevin.png, jackie.png");
    }
    
    // tests for method imagesList()
    boolean testImagesList(Tester t) {
        return
        t.checkExpect(this.babyMonkeyItems.imagesList(), "") &&
        t.checkExpect(this.monkeyPageItems.imagesList(), "monkeys2.png") &&
        t.checkExpect(this.monkeyWPItems.imagesList(),
                                            "monkey.jpeg, monkeys2.png") &&
        t.checkExpect(this.friendsItems.imagesList(),
                                        "annie.jpeg, kevin.png, jackie.png");
    }
    
    // tests for method images()
    boolean testGetImageTitle(Tester t) {
        return
        t.checkExpect(this.jackiePic.getImageTitle(), "jackie.png") &&
        t.checkExpect(this.monkeyPic.getImageTitle(), "monkey.jpeg") &&
        t.checkExpect(this.anniePic.getImageTitle(), "annie.jpeg") &&
        t.checkExpect(this.monkeyCaption.getImageTitle(), "");
    }
    
}
