package package1.game;

import package1.GameClockTimer;
import package1.game.entity.Asteroid;
import package1.game.entity.Entity;
import package1.game.entity.Ship;
import package1.game.gameUtil.Movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by tyleranson on 3/15/16.
 */
public class Game extends JFrame {


    boolean rightPressed=false, leftPressed=false, downPressed=false,
            upPressed=false;
    /**
     * The frames per second that the game is going to refresh at
     */
    private static final int FPS = 60;

    /**
     * The number of nanoseconds that should elapse each frame.
     */
    private static final long FRAME_TIME = (long)(1000000000.0 / FPS);

    /**
     * the GUI instance or world
     */
    private GUI gui;

    /**
     * A list of enties that are in the game currently
     */
    private List<Entity> entities;

    /**
     * A list of entities that are going to be added to the game
     */
    private List<Entity> pendingEntities;

    /**
     * The rocketShip (RocketShip)
     */
    private Ship rocketShip;

    /**
     * a clock for handling updates to the game
     */
    private GameClockTimer timer;


    private Game() {
        super("ASTROYED");

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        add(this.gui = new GUI(this), BorderLayout.CENTER);

        addKeyListener(new KeyAdapter(){ /******************************************************************
         * keyPressed method determines what keys are being pressed in
         * combination with others to set the rocket ship in a direction.
         * @param e
         *****************************************************************/
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if(code == KeyEvent.VK_UP){// && (!downPressed)){
                rocketShip.setUpPressed(true);
            }
            //if((code == KeyEvent.VK_UP) && (downPressed)){
            //}
            if(code == KeyEvent.VK_DOWN){
                rocketShip.setDownPressed(true);
            }
            //if((code == KeyEvent.VK_DOWN) && (upPressed)){
            //    rocketShip.setY(20);
            //    downPressed = true;
            //}
            if(code == KeyEvent.VK_LEFT){
                rocketShip.setLeftPressed(true);

                // ship.paintIcon(this, g2, x, y);
            }
            //if((code == KeyEvent.VK_LEFT) && (rightPressed)){
            //    rocketShip.setX(0);
            //    leftPressed = true;
            //}
            if(code == KeyEvent.VK_RIGHT){
                rocketShip.setRightPressed(true);
            }
            //if((code == KeyEvent.VK_RIGHT) && (leftPressed)) {
            //    rocketShip.setX(0);
            //    rightPressed = true;
            //}
        }

            @Override
            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_UP){ // && (!downPressed)){
                   rocketShip.setUpPressed(false);
                    //rocketShip.setY(0);
                    //upPressed = false;
                    //rotate();
                }
                //if((code == KeyEvent.VK_UP) && (downPressed)){
                //    rocketShip.setY(2);
                //    upPressed = false;
                    //rotate();
                //}
                if(code == KeyEvent.VK_DOWN){
                    rocketShip.setDownPressed(false);
                }
                //if((code == KeyEvent.VK_DOWN) && (upPressed)){
                //    rocketShip.setY(-2);
                //    downPressed = false;
                //}
                if(code == KeyEvent.VK_LEFT){// && (!rightPressed)){
                    rocketShip.setLeftPressed(false);
                }
               // if((code == KeyEvent.VK_LEFT) && (rightPressed)){
                    //rocketShip.setX(2);
                    //leftPressed = false;
                //}
                if(code == KeyEvent.VK_RIGHT){// && (!leftPressed)){
                    rocketShip.setRightPressed(false);
                }

            }

        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void addAsteroid(List<Entity> entities) {
        Random rand = new Random();
        entities.add(new Asteroid(new Movement(rand.nextInt(600),rand.nextInt(600)), new Movement(rand.nextInt(3), rand.nextInt(3)),10));
    }

    /**
     * Starts the game and keeps the game running.
     */
    private void startGame() {
        entities = new LinkedList<Entity>();
        pendingEntities = new ArrayList<Entity>();
        rocketShip = new Ship();
        addAsteroid(entities);

        //Sets everything back to its default values
        resetGame();

        this.timer = new GameClockTimer(FPS);
        while(true) {
            //Gets the initial time of the start
            long start = System.nanoTime();

            timer.update();
            for (int i = 0; i < 5 && timer.hasElapsedCycle(); i++){
                updateGame();
            }
            gui.repaint();

            long delta = FRAME_TIME - (System.nanoTime() - start);
            if(delta > 0) {
                try {
                    Thread.sleep(delta / 1000000L, (int) delta % 1000000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            for(Entity entity : entities) {
                entity.update(this);

            }
        }


    }

    private void resetGame(){
        clearLists();
    }

    private void updateGame(){

    }

    private void clearLists(){
        pendingEntities.clear();
        entities.clear();
        entities.add(rocketShip);
    }

    /******************************************************************
     * main method of the GUI that makes an instance of the GUI
     * @param args
     *****************************************************************/
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Game game = new Game();
        game.startGame();
    }




}
