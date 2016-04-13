package package1.game.entity;

import package1.game.Game;
import package1.game.gameUtil.Movement;

import java.awt.*;
import java.io.BufferedReader;
import java.util.Random;

/**
 * Created by Charles on 4/12/2016.
 */
public class Bullet extends Entity {

    protected static int size = 2;
    public Bullet(Entity owner, double angle){
        super(new Movement(owner.position), new Movement(angle).scale(6.75),2.0);
        this.deadObject = false;
        this.position = position;
        this.speed = speed;
        this.magnitude = magnitude;
        this.rotation = 5.0f;
    }

    public static int getSize(){
        return size;
    }

    public void update(Game game){
        super.update(game);

    }
    @Override
    public void handleInterception(Game game, Entity ent){
        if(ent.getClass() == killerAsteroid.class){
            ent.killObject();
        }
    }

    public void draw(Graphics2D g, Game game) {

        Random rnd = new Random();
        int rc = rnd.nextInt(255)+1;
        int rc2 = rnd.nextInt(255)+1;
        int rc3 = rnd.nextInt(255)+1;
        Color j = new Color(rc,rc2,rc3);

        g.setColor (Color.red);
        g.draw3DRect (size,size,size,size, true);

        g.fill3DRect (size,size,size,size, true);
    }
}

