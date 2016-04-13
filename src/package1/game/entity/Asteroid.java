package package1.game.entity;

import package1.game.Game;

import java.awt.*;
import java.util.Random;

import package1.game.gameUtil.Movement;
/**
 * Created by tyleranson on 3/16/16.
 */
public class Asteroid extends Entity{

    protected static int size = 20;

    public Asteroid(Movement position, Movement speed, double magnitude){
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
        if(ent.getClass() != Asteroid.class){
            killObject();
        }
    }

    public void draw(Graphics2D g, Game game) {

//        int xpoints[] = {0,10,10,-10,-8,-12 -15};
//        int ypoints[] = {0,10,20,21,10,7, 8};
//
//        g.drawPolygon(xpoints, ypoints, xpoints.length); //Draw the Asteroid.
//        g.fillPolygon(xpoints, ypoints, xpoints.length);

        Random rnd = new Random();
        int rc = rnd.nextInt(255)+1;
        int rc2 = rnd.nextInt(255)+1;
        int rc3 = rnd.nextInt(255)+1;
        Color j = new Color(rc,rc2,rc3);

        g.setColor (j);
        g.draw3DRect (-(size/2),-(size/2),size,size, true);

//        g.draw3DRect (200,200,200,200, false);
        g.fill3DRect (-(size/2),-(size/2),size,size, true);
//        g.fill3DRect (20,20,20,20, true);
    }
}
