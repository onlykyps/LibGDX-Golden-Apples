package com.rowg.goldenapples.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.rowg.goldenapples.Collectables.ThrowingRocks;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Screens.PlayScreen;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by claud on 20/05/2018.
 */

public class CuteMonster extends Enemy
{

    private float stateTime;
    private Animation walkAnimation;
    private Animation deadAnimation;
    private Array<TextureRegion> frames;
    private Array<TextureRegion> framesDead;

    private boolean setToDestroy;
    private boolean destroyed;

    private int lives = 1;


    public CuteMonster(PlayScreen screen, float x, float y)
    {

        super(screen, x, y);
        frames = new Array<TextureRegion>();
        framesDead = new Array<TextureRegion>();

        for (int i = 0; i<5; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("mon1_sprite"),i*50,0,60,59));
        walkAnimation = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 1; i < 6; i++)
            framesDead.add(new TextureRegion(screen.getAtlas().findRegion("mon1_sprite"),i*50,100,56,59));
        deadAnimation = new Animation(0.25f,framesDead);
        framesDead.clear();


        stateTime = 0;
        setBounds(getX(),getY(),64/GameMain.PPM,64/GameMain.PPM);
        setToDestroy = false;
        destroyed = false;
    }

    public TextureRegion getFrame(float dt)
    {
        TextureRegion region;
        region = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

            if (velocity.x > 0 && region.isFlipX() == true)
            {
                region.flip(true,false);
            }

            if (velocity.x < 0 && region.isFlipX() == false)
            {
                region.flip(true,false);
            }

            stateTime += dt;


        return region;
    }

    public void update(float dt)
    {
        stateTime += dt;

        if (setToDestroy && !destroyed)
        {
            setRegion((TextureRegion) deadAnimation.getKeyFrame(stateTime,true));
            world.destroyBody(body);
            destroyed = true;

            stateTime = 0;
        }
        else if (!destroyed)
        {
            setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
            setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime,true));
            setRegion(getFrame(dt));
        }
        body.setLinearVelocity(velocity);

    }


    public void hitEnemy()
    {
        if (lives == 0)
        {
            setToDestroy = true;
        }
        else
        {
            lives --;
        }


    }

    public void draw(Batch batch)
    {
        if (!destroyed || stateTime < 1)
         super.draw(batch);
    }

    @Override
    protected void defineEnemy()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);


        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(10/ GameMain.PPM);


        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameMain.CM_BIT;
        fixtureDef.filter.maskBits = GameMain.GROUND_BIT | GameMain.ENEMY_BIT | GameMain.PLAYER_BIT
                | GameMain.LOGS_BIT | GameMain.THROWING_ROCK_BIT;

        body.createFixture(fixtureDef).setUserData(this);

    }
}
