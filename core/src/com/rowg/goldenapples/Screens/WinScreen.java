package com.rowg.goldenapples.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.hudElements.MainMenuButtons;

/**
 * Created by claud on 30/06/2018.
 */

public class WinScreen implements Screen {

    private GameMain gameMain;
    private OrthographicCamera mainCamera;
    private Viewport gameViewPort;


    private Texture bg;

    private MainMenuButtons btns;


    public WinScreen(GameMain gameMain) {
        this.gameMain = gameMain;
        mainCamera = new OrthographicCamera(GameMain.V_WIDTH, GameMain.V_HEIGHT);
        mainCamera.position.set(GameMain.V_HEIGHT / 2, GameMain.V_HEIGHT / 2, 0);
        gameViewPort = new StretchViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT, mainCamera);
        bg = new Texture("Win.jpg");

        btns = new MainMenuButtons(gameMain);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMain.getBatch().begin();
        gameMain.getBatch().draw(bg, 0, 0);

        gameMain.getBatch().end();

        gameMain.getBatch().setProjectionMatrix(btns.getStage().getCamera().combined);
        btns.getStage().draw();


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
    public void dispose() {
        bg.dispose();
        btns.getStage().dispose();

    }
}






