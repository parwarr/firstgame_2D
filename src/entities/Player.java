package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[] [] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false;
    private boolean up,left,right,down;
    private float playerSpeed = 1.5f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){

        updatePos();
        updateAnimationTick();
        setAnimation();



    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction] [animationIndex], (int)x, (int)y, 256, 160, null);

    }




    private void updateAnimationTick() {

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmounts(playerAction))
                animationIndex = 0;
        }

    }

    private void setAnimation() {
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }



    private void updatePos() {
        moving = false;

        if(up){
            y-=playerSpeed;
            moving = true;
        }
        if (left){
            x-=playerSpeed;
            moving = true;
        }

        if (down){
            y+=playerSpeed;
            moving = true;
        }

        if (right) {
            x+=playerSpeed;
            moving = true;
        }


        if (up && down){
            moving = false;
        }

        if (left && right){
            moving = false;
        }


    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[9][6];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j] [i] = img.getSubimage( i*64, j*40,64,40 );


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}


