package com.rowg.goldenapples.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.hudElements.OptionsButtons;

import javax.swing.text.View;
import javax.xml.soap.Text;

/**
 * Created by claud on 29/06/2018.
 */

public class Options implements Screen
{
    private GameMain gameMain;
    private OrthographicCamera mainCam;
    private Viewport gameViewport;

    private Texture bg;

    private OptionsButtons optionsButtons;



    public Options(GameMain gameMain)
    {
        this.gameMain=gameMain;

        mainCam = new OrthographicCamera();
        mainCam.setToOrtho(false, GameMain.V_WIDTH, GameMain.V_HEIGHT);
        mainCam.position.set(GameMain.V_WIDTH/2f, GameMain.V_HEIGHT/2f,0);

        gameViewport = new StretchViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT);

        bg = new Texture("Menu BG.png");

        optionsButtons = new OptionsButtons(gameMain);

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

        gameMain.getBatch().draw(bg,0,0);

        gameMain.getBatch().end();

        gameMain.getBatch().setProjectionMatrix(optionsButtons.getStage().getCamera().combined);
        optionsButtons.getStage().draw();


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

    }
}
