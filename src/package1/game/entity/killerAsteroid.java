package package1.game.entity;

import package1.game.Game;

import java.awt.*;
import java.util.Random;

import package1.game.gameUtil.Movement;

import javax.swing.*;

/**
 * Created by tyleranson on 3/16/16.
 */
public class killerAsteroid extends Entity{

    protected static int size = 30;

    public killerAsteroid(Movement position, Movement speed, double magnitude){
        super(position, speed, magnitude);
        this.deadObject = false;
        this.position = position;
        this.speed = speed;
        this.magnitude = magnitude;
        this.rotation = 5.0f;
    }

    public static int getSize(){
        return size;
    }

    @Override
    public void handleInterception(Game game, Entity ent){
        if(ent.getClass() == Ship.class){
            ent.killObject();
            JOptionPane.showConfirmDialog(null, "YOUR SHIP HAS BEEN ASTROYED!", "*****Game Over*****", JOptionPane.OK_CANCEL_OPTION);
        }
        if(ent.getClass() == Bullet.class){
            killObject();
        }
    }

    public void draw(Graphics2D g, Game game) {

//        int xpoints[] = {0,10,10,-10,-8,-12 -15};
//        int ypoints[] = {0,10,20,21,10,7, 8};
//
//        g.drawPolygon(xpoints, ypoints, xpoints.length); //Draw the Asteroid.
//        g.fillPolygon(xpoints, ypoints, xpoints.length);
        g.setColor (Color.BLACK);
//        g.fill3DRect (size,size,size,size, true);

//        g.draw3DRect (200,200,200,200, false);
        g.fill3DRect (-15,-15,size,size, true);
//        g.fill3DRect (20,20,20,20, true);
    }
}