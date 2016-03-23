package package1.game.entity;

import package1.game.Game;

import java.awt.*;
import java.util.Random;

import package1.game.gameUtil.Movement;
/**
 * Created by tyleranson on 3/16/16.
 */
public class Asteroid extends Entity{


    public Asteroid(Movement position, Movement speed, double magnitude){
        super(position, speed, magnitude);
        this.deadObject = false;
        this.position = position;
        this.speed = speed;
        this.magnitude = magnitude;
        this.rotation = 5.0f;
    }

    public void draw(Graphics2D g, Game game) {

//        int xpoints[] = {0,10,10,-10,-8,-12 -15};
//        int ypoints[] = {0,10,20,21,10,7, 8};
//
//        g.drawPolygon(xpoints, ypoints, xpoints.length); //Draw the Asteroid.
//        g.fillPolygon(xpoints, ypoints, xpoints.length);

        g.setColor (Color.darkGray);
        g.draw3DRect (10, 10, 20, 20, true);
//        g.draw3DRect (25, 110, 50, 75, false);
//        g.fill3DRect (100, 10, 50, 75, true);
//        g.fill3DRect (100, 110, 50, 75, false);
    }
}
