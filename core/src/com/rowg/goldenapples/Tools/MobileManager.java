package com.rowg.goldenapples.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rowg.goldenapples.GameMain;

/**
 * Created by claud on 12/06/2018.
 */

public class MobileManager
{

    static Texture leftPaddleTexture;
    static Texture rightPaddleTexture;
    static Sprite leftPaddleSprite;
    static Sprite rightPaddleSprite;
    public static final float PADDLE_ALPHA = 0.25f;
    public static final float PADDLE_HORIZ_POSITION_FACTOR = 0.02f;
    public static final float PADDLE_VERT_POSITION_FACTOR = 0.01f;


    public static void initializeLeftPaddle(float width,float height){
        //load background texture
        leftPaddleTexture = new Texture(Gdx.files.internal("paddleLeft.png"));
        //set left paddle sprite with the texture
        leftPaddleSprite= new Sprite(leftPaddleTexture);
        // resize the sprite
        leftPaddleSprite.setSize(leftPaddleSprite.getWidth()*width/GameMain.PPM, leftPaddleSprite.getHeight()*height/GameMain.PPM);
        // set the position to bottom left corner with offset
        leftPaddleSprite.setPosition(width* PADDLE_HORIZ_POSITION_FACTOR/GameMain.PPM, height* PADDLE_VERT_POSITION_FACTOR/GameMain.PPM);
        // make the paddle semi transparent
        leftPaddleSprite.setAlpha(PADDLE_ALPHA);
    }

    public static void initializeRightPaddle(float width,float height){
        //load background texture
        rightPaddleTexture = new Texture(Gdx.files.internal("paddleRight.png"));
        //set right paddle sprite with the texture
        rightPaddleSprite= new Sprite(rightPaddleTexture);
        // resize the sprite
        rightPaddleSprite.setSize(rightPaddleSprite.getWidth()*width/GameMain.PPM, rightPaddleSprite.getHeight()*height/GameMain.PPM);
        // set the position to bottom left corner with offset
        rightPaddleSprite.setPosition((leftPaddleSprite.getX()+ leftPaddleSprite.getWidth()+ width*PADDLE_HORIZ_POSITION_FACTOR)/GameMain.PPM, height*PADDLE_VERT_POSITION_FACTOR/GameMain.PPM);
        // make the paddle semi transparent
        rightPaddleSprite.setAlpha(PADDLE_ALPHA);
    }


    public static void initialize(float width, float height)
    {
        initializeLeftPaddle(width,height);
        initializeRightPaddle(width,height);
    }

    public static void renderGame(SpriteBatch batch)
    {
        leftPaddleSprite.draw(batch);
        rightPaddleSprite.draw(batch);
    }

    public static void dispose()
    {
        leftPaddleTexture.dispose();
        rightPaddleTexture.dispose();
    }



}
