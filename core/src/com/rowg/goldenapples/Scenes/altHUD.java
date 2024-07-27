package com.rowg.goldenapples.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.GameMain;

/**
 * Created by claud on 19/05/2018.
 */

public class altHUD implements Disposable {


    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private Integer score;


    com.badlogic.gdx.scenes.scene2d.ui.Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label playerLabel;


    public altHUD(SpriteBatch spriteBatch)
    {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(GameMain.V_WIDTH, GameMain.V_HEIGHT,new OrthographicCamera());

        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerLabel = new Label("Player",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(playerLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel);

        stage.addActor(table);

    }


    @Override
    public void dispose()
    {
        stage.dispose();

    }
}
