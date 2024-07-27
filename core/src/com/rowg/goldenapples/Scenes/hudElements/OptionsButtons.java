package com.rowg.goldenapples.Scenes.hudElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.MainMenu;
import com.rowg.goldenapples.Scenes.Options;

/**
 * Created by claud on 29/06/2018.
 */

public class OptionsButtons
{
    private GameMain gameMain;
    private Viewport gameViewport;
    private Stage stage;

    private ImageButton easy, medium, hard, backBtn;
    private Image sign;


    public OptionsButtons(GameMain gameMain)
    {
        this.gameMain=gameMain;

        gameViewport = new FitViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport, gameMain.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(backBtn);
        stage.addActor(sign);


    }


    void createAndPositionButtons()
    {
        easy = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Easy.png"))));
        medium = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Medium.png"))));
        hard = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Hard.png"))));

        backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Back.png"))));
        sign = new Image(new SpriteDrawable(new Sprite(new Texture("Check Sign.png"))));

        hard.setPosition(GameMain.V_WIDTH*5/8 ,GameMain.V_HEIGHT/4);
        medium.setPosition(GameMain.V_WIDTH*3/8,GameMain.V_HEIGHT/4);
        easy.setPosition(GameMain.V_WIDTH*1/8,GameMain.V_HEIGHT/4);



        backBtn.setPosition(GameMain.V_WIDTH*1/30 ,GameMain.V_HEIGHT*1/6, Align.left);

        sign.setPosition(GameMain.V_WIDTH*3/8 + 74,GameMain.V_HEIGHT*3/4 - 20);

        backBtn.setSize(30,30);
        sign.setSize(15,15);

        easy.setSize(90,180);
        medium.setSize(90,180);
        hard.setSize(90,180);


        //sterge

    }

    void addAllListeners()
    {
        backBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                gameMain.setScreen(new MainMenu(gameMain));
            }
        });

        easy.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                    sign.setX(easy.getX() + easy.getWidth()/2f + 28);
                    sign.setY(easy.getY() + easy.getHeight()/2f - 6);
            }
        });

        medium.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                    sign.setX(medium.getX() + medium.getWidth()/2f + 28);
                    sign.setY(medium.getY() + medium.getHeight()/2f - 6);
            }
        });

        hard.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                    sign.setX(hard.getX() + hard.getWidth()/2f + 28);
                    sign.setY(hard.getY() + hard.getHeight()/2f - 6);

            }
        });
    }

    public Stage getStage()
    {
        return this.stage;
    }



}
