package com.rowg.goldenapples.Scenes.hudElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HUD;
import com.rowg.goldenapples.Sprites.Player;

/**
 * Created by claud on 30/06/2018.
 */

public class TouchScreenButtons
{
    private GameMain gameMain;
    private Stage stage;
    private Viewport gameViewport;


    private ImageButton leftPaddle, rightPaddle, upPaddle, throwRockPaddle;


    public TouchScreenButtons (GameMain gameMain)
    {
        this.gameMain=gameMain;

        gameViewport = new FitViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, gameMain.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(leftPaddle);
        stage.addActor(rightPaddle);
        stage.addActor(upPaddle);
        stage.addActor(throwRockPaddle);
    }



    void createAndPositionButtons()
    {
        leftPaddle = new ImageButton(new SpriteDrawable(new Sprite(new Texture("paddleLeft.png"))));
        rightPaddle = new ImageButton(new SpriteDrawable(new Sprite(new Texture("paddleRight.png"))));
        upPaddle = new ImageButton(new SpriteDrawable(new Sprite(new Texture("paddleUp.png"))));
        throwRockPaddle = new ImageButton(new SpriteDrawable(new Sprite(new Texture("rock.png"))));

        upPaddle.setSize(30,30);
        throwRockPaddle.setSize(30,30);

        leftPaddle.setPosition(GameMain.V_WIDTH*1/30 ,GameMain.V_HEIGHT*1/11, Align.left);
        rightPaddle.setPosition(GameMain.V_WIDTH*4/30 ,GameMain.V_HEIGHT*1/11, Align.left);
        throwRockPaddle.setPosition(GameMain.V_WIDTH*27/30 ,GameMain.V_HEIGHT*1/11, Align.left);
        upPaddle.setPosition(GameMain.V_WIDTH*24/30 ,GameMain.V_HEIGHT*1/11, Align.left);



    }

    void addAllListeners()
    {
        leftPaddle.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Player.movePlayer(-1f);
            }
        });

        rightPaddle.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Player.movePlayer(1f);
            }
        });

        upPaddle.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Player.jump();
            }
        });

        throwRockPaddle.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                if (HUD.checkRockCount() > 0)
                {
                    Player.throwRock();
                    HUD.subtractRocks();
                }

            }
        });
    }


    public Stage getStage()
    {
        return this.stage;
    }

}
