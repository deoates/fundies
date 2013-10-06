import java.awt.Color;
import java.util.Random;
 
import tester.*;
 
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;


// to represent a falling brick
class Brick {
    
    int width = 20;
    int height = 10;
    IColor color = new Red();
    Posn center;


    // constructor
    public Brick(Posn center) {
        this.center = center;
    }
    
    /*
     * Template
     * 
     * Fields
     * ...this.width...                 -- int
     * ...this.height...                -- height
     * ...this.color...                 -- IColor
     * ...this.center...                -- Posn
     * 
     * Methods
     * ...this.brickImage()...                         -- WorldImage
     * ...this.moveBrick()...                          -- Brick
     * ...this.isHalfway(int worldHeight)...           -- Boolean
     * 
     * 
     */
    
    
    // produce the image of this brick at its current location and color
    WorldImage brickImage() {
        return
        new RectangleImage(this.center, this.width, this.height, this.color);
    }
    
    // move this brick 20 pixels downward
    Brick moveBrick() {
        return
        new Brick(new Posn(this.center.x, this.center.y + 20));
    }
    
    // is this brick at least halfway down the screen
    boolean isHalfway(int worldHeight) {
        return (this.center.y - (this.height / 2)) >= (worldHeight / 2);
    }
    
    
}

// to represent a list of bricks
interface ILoB {
    
    // produce an image of all bricks in this list
    WorldImage bricksImages();
    
    // move all bricks in this list
    ILoB moveBricks();
    
    // are all bricks in this list at least halfway down the screen?
    boolean bricksHalfway(int worldHeight);
    
}


// to represent an empty list of bricks
class MtLoB implements ILoB {
    
    MtLoB() {
        // empty constructor
    }
    
    /* Templates
     * 
     * Methods
     * ...this.bricksImages()...                               -- WorldImage
     * ...this.moveBricks()...                                 -- ILoB
     * ...this.bricksHalfway(int worldHeight)...               -- boolean
     *
     * 
     */
   
    // produce an image of all bricks in this list
    public WorldImage bricksImages() {
        return new RectangleImage(new Posn(0,0), 0, 0, new White());
    }

    // move all bricks in this list
    public ILoB moveBricks() {
        return this;
    }

    // are all bricks in this list at least halfway down the screen?
    public boolean bricksHalfway(int worldHeight) {
        return true;
    }
    
}



// to represent a non-empty list of bricks
class ConsLoB implements ILoB {
    
    Brick first;
    ILoB rest;
    
    public ConsLoB(Brick first, ILoB rest) {
        this.first = first;
        this.rest = rest;
    }

    
    /* Template
     * 
     * Fields
     * ...this.first...                        -- Brick
     * ...this.rest...                         -- ILoB
     * 
     * Methods
     * ...this.bricksImages()...                              -- WorldImage
     * ...this.moveBricks()...                                -- ILoB
     * ...this.bricksHalfway(int worldHeight)...              -- boolean
     * 
     * Methods for fields
     * ...this.rest.bricksImages()...                         -- WorldImage
     * ...this.moveBricks()...                                -- ILoB
     * ...this.first.isHalfway(int worldHeight)...            -- boolean
     * 
     */
    
    
    // produce an image of all bricks in this list
    public WorldImage bricksImages() {
        return new OverlayImages(this.first.brickImage(),
                                      this.rest.bricksImages());
    }

    // move all bricks in this list
    public ILoB moveBricks() {
        return new ConsLoB(this.first.moveBrick(), 
                           this.rest.moveBricks());
    }
    
    // are all bricks in this list at least halfway down the screen?
    public boolean bricksHalfway(int worldHeight) {
        return this.first.isHalfway(worldHeight);
    }
    
    // addBrick()
    
    
}



// to represent a construction worker
class Person {
    
    Person() {
       // empty constructor 
    }
}

// represent the world for the falling brick game
public class BrickGame extends World {
    
    int width = 400;
    int height = 400;
    ILoB bricks;
    Person person;

    // constructor
    public BrickGame(ILoB bricks, Person person) {
        super();
        this.bricks = bricks;
        this.person = person;
    }
    
    /* Template
     * 
     * Fields
     * ...this.width...                     -- int
     * ...this.height...                    -- int
     * ...this.bricks...                    -- ILoB
     * ...this.person...                    -- Person
     * ...this.background...                -- WorldImage
     * 
     * Methods
     * ...this.makeImage()...               -- WorldImage
     * ...this.onTick()...                  -- World
     * 
     * Methods for fields
     * ...this.bricks.bricksImages()...                         -- WorldImage
     * ...this.bricks.moveBricks()...                           -- ILoB
     * ...this.bricks.bricksHalfway(int worldHeight)...         -- boolean
     * 
     * 
     */
    
    // the background image of this world
    public WorldImage background =
           new RectangleImage(new Posn(200, 200),
                              this.width, this.height, new Blue());

    
    // produce the image of this world by overlaying
    // the bricks and the person on the background
    public WorldImage makeImage() {
        return
        new OverlayImages(this.background, this.bricks.bricksImages());
    }
    
    
    // on tick, move the brick
    public BrickGame onTick() {
        return new BrickGame(this.bricks.moveBricks(), this.person);
    }
    
    
}


class ExamplesBrickGame {
    
    // examples of bricks
    Brick b1 = new Brick(new Posn(200, 0));
    Brick b2 = new Brick(new Posn(100, 0));
    Brick b3 = new Brick(new Posn(300, 210));
    Brick b4 = new Brick(new Posn(20, 250));
    
    Brick movedB1 = new Brick(new Posn(200, 20));
    Brick movedB2 = new Brick(new Posn(100, 20));
    
    // examples of person
    Person p = new Person();
    
    // example of images
    WorldImage bg = new RectangleImage(new Posn(200,200),
                                           400, 400, new Blue());
    
    WorldImage blank = new RectangleImage(new Posn(0, 0), 0, 0, new White());
    WorldImage brick1 = new RectangleImage(new Posn(200, 0), 20, 10, new Red());
    WorldImage brick2 = new RectangleImage(new Posn(100, 0), 20, 10, new Red());
    
    WorldImage bricks1 = new OverlayImages(this.brick1, this.blank);
    WorldImage bricks2 = new OverlayImages(this.brick2,
                             new OverlayImages(this.brick1, this.blank));
    
    WorldImage w1Image = new OverlayImages(this.bg, this.bricks1);
    WorldImage w2Image = new OverlayImages(this.bg, this.bricks2);
    
    
    // examples of lists of bricks
    ILoB empty = new MtLoB();
    
    ILoB lob1 = new ConsLoB(this.b1, this.empty);
    ILoB lob2 = new ConsLoB(this.b2, this.lob1);
    ILoB lob3 = new ConsLoB(this.b3, this.lob2);
    
    ILoB movedLob1 = new ConsLoB(this.movedB1, this.empty);
    ILoB movedLob2 = new ConsLoB(this.movedB2, new ConsLoB(this.movedB1, this.empty));
    
    // examples of worlds
    BrickGame w1 = new BrickGame(this.lob1, this.p);
    BrickGame w2 = new BrickGame(this.lob2, this.p);
    
    BrickGame w1BrickMoved = new BrickGame(this.movedLob1, this.p);
    BrickGame w2BrickMoved = new BrickGame(this.movedLob2, this.p);
    
    
    // tests for method makeImage()
    boolean testMakeImage(Tester t) {
        return
        t.checkExpect(this.w1.makeImage(), this.w1Image) &&
        t.checkExpect(this.w2.makeImage(), this.w2Image);
    }
    
    // tests for method brickImage()
    boolean testBrickImage(Tester t) {
        return
        t.checkExpect(this.b1.brickImage(), this.brick1) &&
        t.checkExpect(this.b2.brickImage(), this.brick2);
    }
    
    // tests for method moveBrick()
    boolean testMoveBrick(Tester t) {
        return
        t.checkExpect(this.b1.moveBrick(), this.movedB1) &&
        t.checkExpect(this.b2.moveBrick(), this.movedB2);
    }
    
    // tests for method onTick()
    boolean testOnTick(Tester t) {
        return
        t.checkExpect(this.w1.onTick(), this.w1BrickMoved) &&
        t.checkExpect(this.w2.onTick(), this.w2BrickMoved);
    }
    
    
    // tests for the method moveBricks()
    boolean testMoveBricks(Tester t) { 
        return
        t.checkExpect(this.lob1.moveBricks(), this.movedLob1) &&
        t.checkExpect(this.lob2.moveBricks(), this.movedLob2);
    }
    
    // tests for the method bricksImages()
    boolean testBricksImages(Tester t) {
        return
        t.checkExpect(this.lob1.bricksImages(), this.bricks1) &&
        t.checkExpect(this.lob2.bricksImages(), this.bricks2);
    }
    
    // tests for method isHalfway()
    boolean testIsHalfway(Tester t) {
        return
        t.checkExpect(this.b1.isHalfway(400), false) &&
        t.checkExpect(this.b2.isHalfway(400), false) &&
        t.checkExpect(this.b3.isHalfway(400), true) &&
        t.checkExpect(this.b4.isHalfway(400), true);
    }
    
    // tests for method bricksHalfway()
    boolean testBricksHalfway(Tester t) {
       return
       t.checkExpect(this.lob1.bricksHalfway(400), false) &&
       t.checkExpect(this.lob2.bricksHalfway(400), false) &&
       t.checkExpect(this.lob3.bricksHalfway(400), true);
    }
    
    // run the game
    boolean runAnimation = this.w2.bigBang(400, 400, 1);

}
