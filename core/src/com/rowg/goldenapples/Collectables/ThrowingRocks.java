package com.rowg.goldenapples.Collectables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Screens.PlayScreen;

/**
 * Created by claud on 23/05/2018.
 */

public class ThrowingRocks extends Sprite
{

    PlayScreen screen;
    World world;

    Texture throwingRock;
    float stateTime;
    boolean destroyed;
    boolean setToDestroy;
    boolean throwRight;

    Body body;

    public ThrowingRocks(PlayScreen screen, float x, float y, boolean throwRight)
    {
        this.throwRight = throwRight;
        this.screen = screen;
        this.world = screen.getGameWorld();
        throwingRock = new Texture("rock.png");
        setRegion(throwingRock);
        setBounds(x,y,6/ GameMain.PPM,6/GameMain.PPM);
        defineThrowingRocks();
    }

    public void defineThrowingRocks()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(throwRight ? getX() + 12/GameMain.PPM: getX() - 12/GameMain.PPM, getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        if(!world.isLocked())
            body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/GameMain.PPM);
        fixtureDef.filter.categoryBits = GameMain.THROWING_ROCK_BIT;
        fixtureDef.filter.maskBits = GameMain.GROUND_BIT | GameMain.CM_BIT | GameMain.FM_BIT;

        fixtureDef.shape = shape;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0;

        body.createFixture(fixtureDef).setUserData(this);
        body.setLinearVelocity(new Vector2(throwRight ? 2: -2, 2.5f));
    }

    public void update(float dt)
    {
        stateTime += dt;
        setRegion(throwingRock);
        setPosition(body.getPosition().x - getWidth()/2,body.getPosition().y - getHeight()/2);

        if((stateTime > 2 || setToDestroy) && !destroyed)
        {
            world.destroyBody(body);
            destroyed = true;
        }
        if (body.getLinearVelocity().y > 2f)
            body.setLinearVelocity(body.getLinearVelocity().x,2f);
        if((throwRight && body.getLinearVelocity().x < 0) || (!throwRight && body.getLinearVelocity().x > 0))
            setToDestroy();

    }

    public void setToDestroy(){
        setToDestroy = true;
    }

    public boolean isDestroyed(){return destroyed;}






}
