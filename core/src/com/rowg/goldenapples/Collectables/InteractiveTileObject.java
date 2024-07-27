package com.rowg.goldenapples.Collectables;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Screens.PlayScreen;


/**
 * Created by claud on 18/05/2018.
 */

public abstract class InteractiveTileObject
{

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected com.badlogic.gdx.math.Rectangle bounds;
    protected Body body;

    protected Fixture fixture;


    public InteractiveTileObject(PlayScreen screen, Rectangle bounds)
    {
        this.world=screen.getGameWorld();
        this.map=screen.getGameMap();
        this.bounds=bounds;


        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX()+bounds.getWidth()/2)/ GameMain.PPM,(bounds.getY()+bounds.getHeight()/2)/ GameMain.PPM);
        body = world.createBody(bodyDef);


        shape.setAsBox(bounds.getWidth()/2/ GameMain.PPM,bounds.getHeight()/2/ GameMain.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameMain.COLLECTIBLE_BIT;
        /*
        fixtureDef.isSensor = true;
        */
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    public abstract void objectCollision();

    public void setCategoryFilter (short filterBit)
    {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }





    public TiledMapTileLayer.Cell getCell()
    {
        TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(2);
        return layer.getCell((int)(body.getPosition().x * GameMain.PPM / 16),(int)(body.getPosition().y* GameMain.PPM/16));
        //return layer.setCell((int)(body.getPosition().x * GameMain.PPM / 16),(int)(body.getPosition().y*GameMain.PPM/16),);
    }



}
