package com.rowg.goldenapples.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rowg.goldenapples.Collectables.Logs;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HUD;
import com.rowg.goldenapples.Scenes.hudElements.TouchScreenButtons;
import com.rowg.goldenapples.Sprites.Enemy;
import com.rowg.goldenapples.Sprites.Player;
import com.rowg.goldenapples.Tools.B2WorldCreator;
import com.rowg.goldenapples.Tools.MobileManager;
import com.rowg.goldenapples.Tools.WorldContactListener;

/**
 * Created by claud on 11/05/2018.
 */

public class PlayScreen implements Screen
{

    private GameMain game;
    private TextureAtlas atlas;

    //ceea ce ne urmareste in lumea jocului si ceea ce este afisat in viewport
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private HUD hud;


    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator b2WorldCreator;

    private Player player;
    private Array<Logs> logs;

    private TouchScreenButtons touchScreenButtons;



    //private CuteMonster cuteMonster;
    //private FlyingMonster flyingMonster;


    public PlayScreen (GameMain game)
    {
        atlas = new TextureAtlas("heroEnemies.pack");
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(GameMain.V_WIDTH/ GameMain.PPM, GameMain.V_HEIGHT/ GameMain.PPM,gameCam);
        hud = new HUD(game);


//        MobileManager.initialize(GameMain.V_WIDTH/GameMain.PPM, GameMain.V_HEIGHT/GameMain.PPM);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level_mvg_2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ GameMain.PPM);

        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();
        b2WorldCreator = new B2WorldCreator(this);
        world.setContactListener(new WorldContactListener());

        player = new Player(this);
        logs = b2WorldCreator.getLogs();

        touchScreenButtons = new TouchScreenButtons(game);


//        Gdx.input.setInputProcessor(new MobileInputManager(gameCam,this, player)); // enable InputManager to receive input events
//        System.out.println("Inout processor has been set");
//        Gdx.input.setInputProcessor(hud.stageHUD);


        //cuteMonster = new CuteMonster(this,5.32f,0.32f);
        //flyingMonster = new FlyingMonster(this,.32f,.32f);
    }


    public TextureAtlas getAtlas()
    {
        return atlas;
    }




    @Override
    public void show() {

    }

    public void handleInput(float dt)
    {
        if (player.currentState != Player.State.DEAD)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=2)
                player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(),true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x<=2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(),true);
            if (Gdx.input.isKeyPressed(Input.Keys.M))
            {
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                if (HUD.checkRockCount() > 0)
                {
                    player.throwRock();
                    HUD.subtractRocks();
                }

            if (Gdx.input.isKeyJustPressed(Input.Keys.M))
            {

                for (Logs log: logs)
                {
//                    if (player.getX())
                }


            }


        }

    }



    public void update(float dt)
    {

            handleInput(dt);

            world.step(1/60f,6,2);

            player.update(dt);
            //cuteMonster.update(dt);
            //flyingMonster.update(dt);

            for (Enemy enemy: b2WorldCreator.getCuteMonsters())
            {
                enemy.update(dt);
                if (enemy.getX()<player.getX() + 224/GameMain.PPM)
                {
                    enemy.body.setActive(true);
                }
            }

            for (Enemy enemy: b2WorldCreator.getFlyingMonsters())
            {
                enemy.update(dt);
                if (enemy.getX()<player.getX() + 224/GameMain.PPM)
                {
                    enemy.body.setActive(true);
                }
            }

            if (player.currentState != Player.State.DEAD)
            {
                if(player.b2body.getPosition().x >= GameMain.V_WIDTH/2/ GameMain.PPM)
                {
                    gameCam.position.x = player.b2body.getPosition().x;
                }
            }

            gameCam.update();
        }


    public GameMain getGame() {
        return game;
    }

    //aici isi ia prima data delta time valoarea si aceasta este pasata mai departe la update si input
    @Override
    public void render(float delta)
    {
        b2dr.render(world, gameCam.combined);

        update(delta);

        gameCam.update();
        renderer.setView(gameCam);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        //cuteMonster.draw(game.batch);
        //flyingMonster.draw(game.batch);
        for (Enemy enemy: b2WorldCreator.getCuteMonsters())
        {
            enemy.draw(game.batch);
        }

        for (Enemy enemy: b2WorldCreator.getFlyingMonsters())
        {
            enemy.draw(game.batch);
        }



        game.batch.end();

        game.batch.setProjectionMatrix(hud.stageHUD.getCamera().combined);
        hud.stageHUD.draw();

        //pentru a reda(desena) doar ce vede camera
        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.setProjectionMatrix(touchScreenButtons.getStage().getCamera().combined);
        touchScreenButtons.getStage().draw();





    }

    @Override
    public void resize(int width, int height)
    {
        gamePort.update(width,height);

    }

    public World getGameWorld()
    {return world;}

    public TiledMap getGameMap()
    {
        return map;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        touchScreenButtons.getStage().dispose();
        game.getBatch().dispose();

    }
}
