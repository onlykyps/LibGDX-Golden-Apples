package com.rowg.goldenapples.Collectables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HUD;
import com.rowg.goldenapples.Screens.PlayScreen;

/**
 * Created by claud on 18/05/2018.
 */

public class Rocks extends InteractiveTileObject {
    public Rocks(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);

    }

    @Override
    public void objectCollision()
    {
        Gdx.app.log("Rocks","Object Collision Detected");
        setCategoryFilter(GameMain.DESTROYED_BIT);
        getCell().setTile(null);
        HUD.addRocks(1);

    }
}
