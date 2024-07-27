package com.rowg.goldenapples.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.rowg.goldenapples.Screens.PlayScreen;

/**
 * Created by claud on 20/05/2018.
 */

public abstract class Enemy extends Sprite
{
    protected World world;
    protected PlayScreen screen;
    public Body body;
    public Vector2 velocity;
    protected Fixture fixture;


    public Enemy (PlayScreen screen, float x, float y)
    {
        this.world = screen.getGameWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocity = new Vector2(0.3f,0);
        body.setActive(false);
    }

    protected abstract void defineEnemy();


    public void reverseVelocity(boolean x, boolean y)
    {
        if (x)
        {
            velocity.x = -velocity.x;


        }
        if (y) {velocity.y = -velocity.y;}
    }

    public abstract void update(float dt);

    public void setCategoryFilter (short filterBit)
    {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }


//    public abstract int getEnemyLives();
//
//    public abstract int loseLife();


}






