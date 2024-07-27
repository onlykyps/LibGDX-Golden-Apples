package com.rowg.goldenapples.Scenes.hudElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.rowg.goldenapples.Scenes.HighScore;
import com.rowg.goldenapples.Scenes.Options;
import com.rowg.goldenapples.Screens.PlayScreen;
import com.rowg.goldenapples.Sprites.Player;

/**
 * Created by claud on 19/05/2018.
 */

public class MainMenuButtons
{
    private GameMain game;
    private Stage stage;
    private Viewport gameViewPort;

    private ImageButton playBtn;
    private ImageButton highScoreBtn;
    private ImageButton optionsBtn;
    private ImageButton quitBtn;
    private ImageButton musicBtn;


    public MainMenuButtons (GameMain game)
    {
        this.game=game;

        gameViewPort = new FitViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewPort, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(playBtn);
        stage.addActor(highScoreBtn);
        stage.addActor(optionsBtn);
        stage.addActor(musicBtn);
        stage.addActor(quitBtn);

    }


    void createAndPositionButtons()
    {
        playBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Start Game.png"))));
        highScoreBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Highscore.png"))));
        optionsBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Options.png"))));
        musicBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Music On.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Quit.png"))));


        playBtn.setPosition(GameMain.V_WIDTH/4 ,GameMain.V_HEIGHT*3/4, Align.center);
        highScoreBtn.setPosition(GameMain.V_WIDTH*3/4,GameMain.V_HEIGHT*3/4, Align.center);
        optionsBtn.setPosition(GameMain.V_WIDTH/4,GameMain.V_HEIGHT/4, Align.center);
        quitBtn.setPosition(GameMain.V_WIDTH*3/4,GameMain.V_HEIGHT/4,Align.center);
        musicBtn.setPosition(GameMain.V_WIDTH/2,13, Align.center);

    }


    void addAllListeners()
    {
        playBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                game.setScreen(new PlayScreen(game));
            }
        });

        highScoreBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                game.setScreen(new HighScore(game));
            }
        });

        optionsBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                game.setScreen(new Options(game));
            }
        });

        musicBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            }
        });

        quitBtn.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
            }
        });
    }

    public Stage getStage()
    {
        return stage;
    }















}
