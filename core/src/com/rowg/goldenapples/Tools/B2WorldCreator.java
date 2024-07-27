package com.rowg.goldenapples.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rowg.goldenapples.Collectables.Coin;
import com.rowg.goldenapples.Collectables.EndGame;
import com.rowg.goldenapples.Collectables.Hazards;
import com.rowg.goldenapples.Collectables.Lives;
import com.rowg.goldenapples.Collectables.Logs;
import com.rowg.goldenapples.Collectables.Rocks;
import com.rowg.goldenapples.Collectables.Vanat;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Screens.PlayScreen;
import com.rowg.goldenapples.Sprites.CuteMonster;
import com.rowg.goldenapples.Sprites.FlyingMonster;

/**
 * Created by claud on 14/05/2018.
 */

public class B2WorldCreator
{
    private Array<CuteMonster> cuteMonsters;
    private Array<FlyingMonster> flyingMonsters;
    private Array<Logs> logs;

    public B2WorldCreator(PlayScreen screen)
    {

        World world = screen.getGameWorld();
        TiledMap map = screen.getGameMap();

        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;


        //ground
        for(MapObject object:map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set((rectangle.getX()+rectangle.getWidth()/2)/ GameMain.PPM,(rectangle.getY()+rectangle.getHeight()/2)/ GameMain.PPM);
            body = world.createBody(bDef);


            shape.setAsBox(rectangle.getWidth()/2/ GameMain.PPM,rectangle.getHeight()/2/ GameMain.PPM);
            fDef.shape = shape;
            body.createFixture(fDef);

        }



        //rocks
        for(MapObject object:map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Rocks(screen,rectangle);


        }

        //coins
        for(MapObject object:map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rectangle);

        }

        //lives
        for(MapObject object:map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Lives(screen,rectangle);

        }

        //vanat
        for(MapObject object:map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Vanat(screen,rectangle);

        }

        //create all cute monsters
        cuteMonsters = new Array<CuteMonster>();
        for(MapObject object:map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            cuteMonsters.add(new CuteMonster(screen,rectangle.getX()/GameMain.PPM, rectangle.getY()/GameMain.PPM));
        }

        flyingMonsters = new Array<FlyingMonster>();
        for(MapObject object:map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            flyingMonsters.add(new FlyingMonster(screen,rectangle.getX()/GameMain.PPM, rectangle.getY()/GameMain.PPM));
        }

        //logs
        logs = new Array<Logs>();
        for(MapObject object:map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            logs.add(new Logs(screen,rectangle));


        }

        //end game
        for(MapObject object:map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new EndGame(screen,rectangle, screen.getGame());

        }

        //win game
        for(MapObject object:map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Hazards(screen,rectangle, screen.getGame());

        }



    }


    public Array<CuteMonster> getCuteMonsters() {
        return cuteMonsters;
    }

    public Array<FlyingMonster> getFlyingMonsters() {
        return flyingMonsters;
    }

    public Array<Logs> getLogs() {return logs;}
}
