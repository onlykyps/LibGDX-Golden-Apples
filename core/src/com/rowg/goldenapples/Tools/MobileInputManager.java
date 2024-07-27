package com.rowg.goldenapples.Tools;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Screens.PlayScreen;
import com.rowg.goldenapples.Sprites.Player;

/**
 * Created by claud on 12/06/2018.
 */

public class MobileInputManager extends InputAdapter
{

    Player player;
    PlayScreen screen;

    OrthographicCamera camera;
    static Vector3 temp = new Vector3(); // temporary vector


    public MobileInputManager(OrthographicCamera camera, PlayScreen screen, Player player) {

        this.camera = camera;
        this.screen = screen;
        this.player = player;
    }

    boolean isLeftPaddleTouched(float touchX, float touchY){
        // handle touch input on the left paddle
        if(
                touchX >= (MobileManager.leftPaddleSprite.getX()/GameMain.PPM) &&
                touchX <= (MobileManager.leftPaddleSprite.getX()+ MobileManager.leftPaddleSprite.getWidth())/GameMain.PPM &&
                touchY >= (MobileManager.leftPaddleSprite.getY()/GameMain.PPM) &&
                touchY <= (MobileManager.leftPaddleSprite.getY()+ MobileManager.leftPaddleSprite.getHeight())/GameMain.PPM
                )
        {
            System.out.println("Left Paddle is touched");
            System.out.println(MobileManager.leftPaddleSprite.getX()/GameMain.PPM);
            System.out.println(MobileManager.leftPaddleSprite.getY()/GameMain.PPM);
            System.out.println(MobileManager.leftPaddleSprite.getWidth()/GameMain.PPM);
            System.out.println(MobileManager.leftPaddleSprite.getHeight()/GameMain.PPM);
            return true;

        }

        return false;
    }

    boolean isRightPaddleTouched(float touchX, float touchY){
        // handle touch input on the right paddle
        if((touchX>=MobileManager.rightPaddleSprite.getX()/GameMain.PPM) &&
                touchX<=(MobileManager.rightPaddleSprite.getX()+MobileManager.rightPaddleSprite.getWidth())/GameMain.PPM &&
                (touchY>=MobileManager.rightPaddleSprite.getY()/GameMain.PPM) &&
                touchY<=(MobileManager.rightPaddleSprite.getY()+MobileManager.rightPaddleSprite.getHeight())/GameMain.PPM ){
            System.out.println("Right Paddle is touched");
            return true;
        }
        System.out.println("Right Paddle is not touched");
        System.out.println(MobileManager.leftPaddleSprite.getX()/GameMain.PPM);
        System.out.println(MobileManager.leftPaddleSprite.getY()/GameMain.PPM);
        System.out.println(MobileManager.leftPaddleSprite.getWidth()/GameMain.PPM);
        System.out.println(MobileManager.leftPaddleSprite.getHeight()/GameMain.PPM);

        System.out.println(MobileManager.rightPaddleSprite.getX()/GameMain.PPM);
        System.out.println(MobileManager.rightPaddleSprite.getY()/GameMain.PPM);
        System.out.println(MobileManager.rightPaddleSprite.getWidth()/GameMain.PPM);
        System.out.println(MobileManager.rightPaddleSprite.getHeight()/GameMain.PPM);
        return false;
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX,screenY, 0);
        //get the touch co-ordinates with respect to the camera's viewport
        camera.unproject(temp);
        System.out.println("The pointer coordinates are");
        float touchX = temp.x;
        System.out.println("tx = " + touchX);
        float touchY = temp.y;
        System.out.println("ty = " + touchY);

        if(isLeftPaddleTouched(touchX,touchY))
        {
            System.out.println("Left paddle has been touched");
            player.setLeftPaddleTouched(true);
            System.out.println("Player.setLeflPaddleTouched = True");
        }
        else if(isRightPaddleTouched(touchX,touchY))
        {
            System.out.println("Right paddle has been touched");
            player.setRightPaddleTouched(true);
            System.out.println("Player.setRightPaddleTouched = True");
        }
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX,screenY, 0);
        //get the touch co-ordinates with respect to the camera's viewport
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        if(isLeftPaddleTouched(touchX,touchY)){
            player.setLeftPaddleTouched(false);
            System.out.println("player.setLeftPaddleTouched = false");
        }
        else if(isRightPaddleTouched(touchX,touchY)){
            player.setRightPaddleTouched(false);
            System.out.println("player.setRightPaddleTouched = false");
        }
        return false;
    }



}
