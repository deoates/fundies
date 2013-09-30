import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class AppleGame extends World {
    int w = 400;
    int h = 400;
    
    Apple apple;
    
    public AppleGame(Apple a) {
        super();
        this.apple = a;
    }
    
    public WorldImage background = new FromFileImage(new Posn(200, 200), "apple-tree.png");
    
    // 
    public WorldImage makeImage() {
        // return new OverlayImages(this.apple.blobImage(), this.background)
        return this.background;
    }
    
}