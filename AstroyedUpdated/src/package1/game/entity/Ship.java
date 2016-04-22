package package1.game.entity;

import package1.game.Game;
import package1.game.SoundEffect;
import package1.game.gameUtil.Movement;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tyleranson on 3/15/16.
 */
public class Ship extends Entity {

    private static final double DEF_ROTATION = -Math.PI / 20.0;

    private static final double THRUST_MAGNITUDE = 1;

    /**
     * The fastest our rocket ship can travel
     */
    private static final double MAX_SPEED = 8;

    /**
     * is the up button pressed
     */
    private boolean upPressed;

    /**
     * is the right button pressed
     */
    private boolean rightPressed;

    /**
     * is the left button presssed
     */
    private boolean leftPressed;

    /**
     * is the down button pressed
     */
    private boolean downPressed;

    private int animationFrame;

    private List<Bullet> bullets;

    private boolean fireBullet;

    public boolean immortal;

    private int timeImmortal;

    private final int Max_Immortal = 500;

    public boolean killerShip;

    private int timeKiller;

    private final int Max_Killer = 500;


    public Ship() {
        super(new Movement(1900 / 2, 1000 / 2), new Movement(0.0, 0.0), 10.0);
        this.rotation = DEF_ROTATION;
        this.deadObject = false;
        this.upPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;
        this.downPressed = false;
        this.fireBullet = false;
        this.animationFrame = 0;
        this.bullets = new ArrayList<Bullet>();
        this.immortal = true;
        this.timeImmortal = Max_Immortal;
        this.killerShip = false;
        this.timeKiller = Max_Killer;
    }

    /******************************************************************
     * @param upPressed
     *****************************************************************/
    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    /******************************************************************
     * @param rightPressed
     */
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    /******************************************************************
     * @param leftPressed
     */
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    /******************************************************************
     * @param downPressed
     */
    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }



    public void setFiring(boolean fireBullet){
        this.fireBullet = fireBullet;
    }

    public void setImmortal(boolean immortal){
        this.immortal = immortal;
    }

    public void setKillerShip(boolean killerShip){
        this.killerShip = killerShip;
    }
    @Override
    public void update(Game game) {
        super.update(game);

        this.animationFrame++;


        if(immortal){
            timeImmortal--;
            if(timeImmortal == 0){
                setImmortal(false);
                timeImmortal = Max_Immortal;
            }
        }
        if(killerShip){
            timeKiller--;
            if(timeKiller == 0){
                setKillerShip(false);
                timeKiller = Max_Killer;
            }
        }

        if (leftPressed != rightPressed) {
            rotate(leftPressed ? DEF_ROTATION : -DEF_ROTATION);
        }
        if (upPressed) {
            speed.add(new Movement(rotation).scale(1));
            if (speed.getShipMagnitude() >= MAX_SPEED * MAX_SPEED) {
                speed.controlSpeed().scale(MAX_SPEED);
            }
        }
        if (!upPressed && !downPressed){

            speed.scale(.975);
            SoundEffect.UH.stop();
        }
        if (downPressed){
            speed.add(new Movement(rotation).scale(-THRUST_MAGNITUDE/2));
            if (speed.getShipMagnitude() >= MAX_SPEED * MAX_SPEED) {
                speed.controlSpeed().scale(MAX_SPEED/2);
            }
            SoundEffect.UH.loop();
        }
        if (fireBullet && game.getCombo() < 20 ) {
            if (game.bulletCount == 0) {
                Bullet bullet = new Bullet(this, rotation);
                bullets.add(bullet);
                game.registerEntity(bullet);
                game.bulletCount++;
            }
        }
        if (fireBullet && game.getCombo() >= 20){
            if (game.bulletCount == 0) {
                Bullet bullet1 = new Bullet(this, rotation);
                bullet1.position.x = bullet1.position.x + 7;
//                bullet.position.y = Math.PI/2;
                Bullet bullet2 = new Bullet(this, rotation);
                bullet2.position.x = bullet2.position.x - 15;
//                bullet2.position.y = Math.PI/2;
                bullets.add(bullet1);
                bullets.add(bullet2);
                game.registerEntity(bullet1);
                game.registerEntity(bullet2);
                game.bulletCount++;
            }
        }
//        Iterator<Bullet> iter = bullets.iterator();
//            while (iter.hasNext()){
//                Bullet bullet = iter.next();
//                if(bullet.deadObject){
//                    iter.remove();
//                }
//        }
    }


    @Override
    public void handleInterception(Game game, Entity ent){

        if(ent.getClass() == Collectable.class) {
            ent.killObject();
            game.addCombo(1);
            if(game.getCombo()%15 == 0){
                setImmortal(true);
                SoundEffect.SHIELD.play();
            }

            if(game.getCombo()%25 == 0){
                setKillerShip(true);
                SoundEffect.SUPER.play();
            }
            if(game.getCombo()%5== 0){
                for(int i = 0; i <= 360; i = i + 40){
                    Bullet bullet = new Bullet(this,i);
                    bullets.add(bullet);
                    game.registerEntity(bullet);
                }
            }

        }

        if((ent.getClass() == killerAsteroid.class && !immortal && !killerShip) || (ent.getClass() == SmallKiller.class && !immortal && !killerShip)) {
            killObject();
            game.addDeathCount(1);
            SoundEffect.BONK.play();
            game.startGame();

        }
        if(ent.getClass() == killerAsteroid.class && killerShip) {
            SoundEffect.BREAK.play();
            ent.killObject();
            Random rand = new Random();
            double randomNumber = -3 + (3 + 3) * rand.nextDouble();
            double randomNumber2 = -3 + (3 + 3) * rand.nextDouble();
            SmallKiller a = (new SmallKiller(new Movement(ent.getPosition()), new Movement(randomNumber, randomNumber2), SmallKiller.getSize()));
            double randomNumber3 = -3 + (3 + 3) * rand.nextDouble();
            double randomNumber4 = -3 + (3 + 3) * rand.nextDouble();
            SmallKiller b = (new SmallKiller(new Movement(ent.getPosition()), new Movement(randomNumber3, randomNumber4), SmallKiller.getSize()));
            double randomNumber5 = -3 + (3 + 3) * rand.nextDouble();
            double randomNumber6 = -3 + (3 + 3) * rand.nextDouble();
            killerAsteroid c = (new killerAsteroid(new Movement(rand.nextInt(1200),rand.nextInt(900)), new Movement(randomNumber5, randomNumber6), killerAsteroid.getSize()));

            game.registerEntity(a);
            game.registerEntity(b);
            game.registerEntity(c);
            game.addPoints(10);
        }
        if(ent.getClass() == SmallKiller.class && killerShip) {
            ent.killObject();
            game.addPoints(20);
            Collectable b = (new Collectable(this.getPosition(), this.getSpeed(), Collectable.getSize()));
            game.registerEntity(b);
        }
    }
    /**
     * @param g
     * @param game
     */
    @Override
    public void draw(Graphics2D g, Game game) {
        Random rnd = new Random();
        int rc = rnd.nextInt(255)+1;
        int rc2 = rnd.nextInt(255)+1;
        int rc3 = rnd.nextInt(255)+1;
        Color j = new Color(rc,rc2,rc3);

        g.setColor(Color.YELLOW);
        g.drawLine(10, -4, 20, 0);
        g.drawLine(10, 4, 20, 0);
        g.setColor(Color.yellow);



        g.drawLine(-15, 15, -10, 4);
        g.drawLine(-15, 15, 4, 4);
        g.drawLine(-15, -15, -10, -4);
        g.drawLine(-15, -15, 4, -4);
        g.drawLine(-15, -15, 0, -4);
        g.drawLine(-15, -15, -6, -4);
        g.drawLine(-15, 15, 0, 4);
        g.drawLine(-15, 15, -6, 4);

        g.setColor(Color.white);
        g.fillRect(-10, -4, 20, 8);

        if(game.getCombo() >= 25){
            g.setColor(new Color(30,200,200));
            g.fillRect(-6,-10,20,2);
            g.fillRect(-6,8,20,2);
        }

        if(this.immortal){
            g.setColor (j);
            g.drawOval(-21, -21, 42, 42);
        }
        if(this.killerShip){
            g.setColor(j);
            g.drawLine(-20, -25, 30, 0);
            g.drawLine(-20, -25, -20, 25);
            g.drawLine(30, 0, -20, 25);
        }
    }
}


