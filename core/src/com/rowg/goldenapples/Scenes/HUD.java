package com.rowg.goldenapples.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;


/**
 * Created by claud on 11/05/2018.
 */

public class HUD implements InputProcessor
{

    private Image coinImage, lifeImage, rockImage, vanatImage;
    private static Label coinLabel, rockLabel, lifeLabel, vanatLabel;
    private ImageButton pauseBtn;
    public Stage stageHUD;

    private static GameMain gameMain;
    private Viewport gameViewport;
    private static Integer score = 0;
    private static Integer rocks = 0;
    private static Integer lives = 2;
    private static Integer vanat = 0;


    public HUD (GameMain gameMain)
    {
        this.gameMain=gameMain;
        gameViewport = new FitViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT,new OrthographicCamera());
        stageHUD = new Stage(gameViewport, gameMain.getBatch());

        Gdx.input.setInputProcessor(stageHUD);


        createLabels();
        createImages();
//        createTouchDisplay();
//        addAllListeners();

        Table coinLifeRock = new Table();
        coinLifeRock.top().left();
        coinLifeRock.setFillParent(true);

//        Table paddles = new Table();
//        paddles.bottom().bottom();
//        paddles.setFillParent(true);

        coinLifeRock.add(lifeImage).padTop(10);
        coinLifeRock.add(lifeLabel).padLeft(5).padTop(10);
        coinLifeRock.add(coinImage).padLeft(5).padTop(10);
        coinLifeRock.add(coinLabel).padLeft(5).padTop(10);
        coinLifeRock.add(rockImage).padLeft(5).padTop(10);
        coinLifeRock.add(rockLabel).padLeft(5).padTop(10);
        coinLifeRock.row();
        coinLifeRock.add(vanatImage).padLeft(10).padTop(10);
        coinLifeRock.add(vanatLabel).padLeft(10).padTop(10);

        stageHUD.addActor(coinLifeRock);
    }


    void createLabels()
    {
        coinLabel = new Label("x " + String.format("%01d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lifeLabel = new Label("x " + String.format("%01d",lives),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        rockLabel = new Label("x " + String.format("%01d",rocks),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        vanatLabel = new Label("x " + String.format("%01d",vanat),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }

    void createImages()
    {
        coinImage = new Image(new Texture("Coin.png"));
        lifeImage = new Image(new Texture("Heart.png"));
        rockImage = new Image(new Texture("rock.png"));
        vanatImage = new Image(new Texture("Vanat.png"));
    }


    public static void addScore (int value)
    {
        score += value;
        coinLabel.setText("x " + String.format("%01d",score));
    }

    public static void addRocks (int value)
    {
        rocks += value;
        rockLabel.setText("x " + String.format("%01d",rocks));
    }

    public static void addLives (int value)
    {
        lives += value;
        lifeLabel.setText("x " + String.format("%01d",lives));
    }

    public static void addVanat (int value)
    {
        vanat += value;
        vanatLabel.setText("x " + String.format("%01d",vanat));
    }


    public static void subtractRocks()
    {
        rocks--;
        rockLabel.setText("x " + String.format("%01d",rocks));
    }

    public static void subtractLives()
    {
        if (lives < 0)
        {
            gameMain.setScreen(new MainMenu(gameMain));
            resetAll();
        }
        else
        {
            lives--;
            lifeLabel.setText("x " + String.format("%01d",lives));
            deathReset();
        }

    }

    public static Integer checkRockCount()
    {
        return rocks;
    }

    public static Integer checkVanat()
    {
        return vanat;
    }

    public static void resetAll()
    {
        vanat = 0;
        rocks = 0;
        score = 0;
        lives = 2;
        vanatLabel.setText("x " + String.format("%01d", vanat));
        rockLabel.setText("x " +String.format("%01d",rocks));
        coinLabel.setText("x " +String.format("%01d",score));
        lifeLabel.setText("x " +String.format("%01d",lives));
    }

    public static void deathReset()
    {
        vanat = 0;
        rocks = 0;
        vanatLabel.setText("x " + String.format("%01d", vanat));
        rockLabel.setText("x " +String.format("%01d",rocks));
    }





    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
