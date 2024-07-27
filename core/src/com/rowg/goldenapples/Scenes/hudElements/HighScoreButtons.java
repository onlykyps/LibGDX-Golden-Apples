package com.rowg.goldenapples.Scenes.hudElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HighScore;
import com.rowg.goldenapples.Scenes.MainMenu;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Created by claud on 19/05/2018.
 */

public class HighScoreButtons
{
    private GameMain gameMain;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton backBtn;
    private Label coinLabel;

    public HighScoreButtons (GameMain gameMain)
    {
        this.gameMain=gameMain;
        gameViewport = new FitViewport(GameMain.V_WIDTH,GameMain.V_HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport,gameMain.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionUIElements();

        stage.addActor(backBtn);
        stage.addActor(coinLabel);


    }

    void createAndPositionUIElements()
    {
        backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Back.png"))));
        backBtn.setSize(30,30);

        coinLabel = new Label("100", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        backBtn.setPosition(GameMain.V_WIDTH*1/30 ,GameMain.V_HEIGHT*1/11, Align.left);
        coinLabel.setPosition(GameMain.V_WIDTH/2,GameMain.V_HEIGHT/4);

        backBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                gameMain.setScreen(new MainMenu(gameMain));
            }
        });
    }

    public Stage getStage()
    {
        return this.stage;
    }

















}
