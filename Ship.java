package package1.game.entity;

import package1.game.Game;
import package1.game.gameUtil.Movement;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tyleranson on 3/15/16.
 */
public class Ship extends Entity {

    private static final double DEF_ROTATION = -Math.PI / 20.0;

    private static final double THRUST_MAGNITUDE = 1;

    /**
     * The fastest our rocket ship can travel
     */
    private static final double MAX_SPEED = 10;

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

    public Ship() {
        super(new Movement(600 / 2, 600 / 2), new Movement(0.0, 0.0), 10.0);
        this.rotation = DEF_ROTATION;
        this.deadObject = false;
        this.upPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;
        this.downPressed = false;
        this.animationFrame = 0;
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

    @Override
    public void update(Game game) {
        super.update(game);

        this.animationFrame++;

        if (leftPressed != rightPressed) {
            rotate(leftPressed ? DEF_ROTATION : -DEF_ROTATION);
        }
        if (upPressed) {
            speed.add(new Movement(rotation).scale(1));
            if (speed.getShipMagnitude() >= MAX_SPEED * MAX_SPEED) {
                speed.controlSpeed().scale(MAX_SPEED);
            }
        }
        if (downPressed){
            speed.add(new Movement(rotation).scale(-THRUST_MAGNITUDE/2));
            if (speed.getShipMagnitude() >= MAX_SPEED * MAX_SPEED) {
                speed.controlSpeed().scale(MAX_SPEED);
            }
        }
    }

    @Override
    public void handleInterception(Game game, Entity ent){
        if(ent.getClass() == Asteroid.class) {
            game.killPlayer();
        }
    }
    /**
     * @param g
     * @param game
     */
    @Override
    public void draw(Graphics2D g, Game game) {
        g.drawLine(10, -4, 15, 0);
        g.drawLine(10, 4, 15, 0);
        g.drawRect(-10, -4, 19, 8);
        g.drawLine(-10, 8, -10, 4);
        g.drawLine(-10, 8, 4, 4);
        g.drawLine(-10, -8, -10, -4);
        g.drawLine(-10, -8, 4, -4);
    }
}


