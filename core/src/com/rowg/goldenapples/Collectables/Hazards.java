package com.rowg.goldenapples.Collectables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HUD;
import com.rowg.goldenapples.Scenes.MainMenu;
import com.rowg.goldenapples.Screens.LooseScreen;
import com.rowg.goldenapples.Screens.PlayScreen;

/**
 * Created by claud on 30/06/2018.
 */

public class Hazards
{
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected com.badlogic.gdx.math.Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    protected GameMain gameMain;

    public Hazards (PlayScreen screen, Rectangle bounds, GameMain gameMain)
    {
        this.world=screen.getGameWorld();
        this.map=screen.getGameMap();
        this.bounds=bounds;
        this.gameMain = gameMain;


        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX()+bounds.getWidth()/2)/ GameMain.PPM,(bounds.getY()+bounds.getHeight()/2)/ GameMain.PPM);
        body = world.createBody(bodyDef);


        shape.setAsBox(bounds.getWidth()/2/ GameMain.PPM,bounds.getHeight()/2/ GameMain.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameMain.LOOSE_GAME_BIT;

        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    public void looseGameCollision()
    {
        this.gameMain.setScreen(new LooseScreen(this.gameMain));
        HUD.deathReset();



    }
}
