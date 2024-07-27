package com.rowg.goldenapples.Collectables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Screens.PlayScreen;

/**
 * Created by claud on 18/05/2018.
 */

public class Logs  {

    protected World world;
    protected TiledMap map;
    protected com.badlogic.gdx.math.Rectangle bounds;
    protected Body body;

    protected Fixture fixture;



    public Logs (PlayScreen screen, Rectangle bounds)
    {
        this.world=screen.getGameWorld();
        this.map=screen.getGameMap();
        this.bounds=bounds;


        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set((bounds.getX()+bounds.getWidth()/2)/ GameMain.PPM,(bounds.getY()+bounds.getHeight()/2)/ GameMain.PPM);
        body = world.createBody(bodyDef);


        shape.setAsBox(bounds.getWidth()/2/ GameMain.PPM,bounds.getHeight()/2/ GameMain.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameMain.LOGS_BIT;
        fixtureDef.filter.maskBits = GameMain.PLAYER_BIT | GameMain.FM_BIT | GameMain.CM_BIT | GameMain.GROUND_BIT;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    public void logCollision()
    {
        Gdx.app.log("Logs","Object Collision Detected");

    }

    public float getX()
    {
        return this.body.getPosition().x;
    }

    public void move()
    {
        this.body.setTransform(new Vector2(100,100),0);
    }
}
