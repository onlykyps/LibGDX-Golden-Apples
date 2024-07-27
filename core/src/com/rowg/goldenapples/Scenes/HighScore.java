package com.rowg.goldenapples.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.hudElements.HighScoreButtons;

import javax.swing.text.View;

/**
 * Created by claud on 19/05/2018.
 */

public class HighScore implements Screen
{
    private GameMain gameMain;
    private OrthographicCamera mainCamera;
    private Viewport gameViewport;
    private Texture bg;

    private HighScoreButtons hsBtns;


    public HighScore (GameMain gameMain)
    {
        this.gameMain=gameMain;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false,GameMain.V_WIDTH, GameMain.V_HEIGHT);
        mainCamera.position.set(GameMain.V_WIDTH,GameMain.V_HEIGHT,0);

        gameViewport = new StretchViewport(GameMain.V_WIDTH,GameMain.V_HEIGHT,mainCamera);

        bg = new Texture("Menu BG.png");

        hsBtns = new HighScoreButtons(gameMain);
    }








    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMain.getBatch().begin();
        gameMain.getBatch().draw(bg,0,00);
        gameMain.getBatch().end();

        gameMain.getBatch().setProjectionMatrix(hsBtns.getStage().getCamera().combined);
        hsBtns.getStage().draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        bg.dispose();
    }
}
