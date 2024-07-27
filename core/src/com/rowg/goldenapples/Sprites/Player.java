package com.rowg.goldenapples.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rowg.goldenapples.Collectables.ThrowingRocks;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HUD;
import com.rowg.goldenapples.Screens.PlayScreen;


/**
 * Created by claud on 14/05/2018.
 */

public class Player extends Sprite
{

    public enum State {FALLING, JUMPING, STANDING, RUNNING, DEAD}
    public static State currentState;
    public State previousState;
    public World world;
    public static Body b2body;
    private TextureRegion playerStand;
    private TextureRegion playerDead;
    private Animation playerRun;
    private Animation playerJump;
    private static boolean runningRight;
    private boolean playerIsDead;
    private float stateTimer;

    private static Array<ThrowingRocks> throwingRocks;
    private static PlayScreen screen;

    boolean isLeftPaddleTouched;  // indicates if left paddle is touched
    boolean isRightPaddleTouched; // indicates if right paddle is touched




    public Player (PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("heroAll"));
        this.world=screen.getGameWorld();
        this.screen = screen;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        //walk/run animation
        for (int i = 1; i < 6; i++)
        {
            frames.add(new TextureRegion(getTexture(),i *32,64,32,64));
        }

        playerRun = new Animation(0.1f, frames);
        frames.clear();

        //jump animation
        for (int i = 7; i < 9 ; i++)
        {
            frames.add(new TextureRegion(getTexture(), i*32,64,32,64));

        }

        playerJump = new Animation(0.1f,frames);
        frames.clear();

        playerStand = new TextureRegion(getTexture(),293,64,32,64);

        playerDead = new TextureRegion(screen.getAtlas().findRegion("heroAll"),32,0,32,64);

        definePlayer();
        setBounds(0,0,32/ GameMain.PPM,64/ GameMain.PPM);
        setRegion(playerStand);

        throwingRocks = new Array<ThrowingRocks>();



    }

    public static void movePlayer(float x)
    {

       b2body.setLinearVelocity(x, b2body.getLinearVelocity().y);

    }



    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));


        for(ThrowingRocks rock : throwingRocks)
        {
            rock.update(dt);
            if(rock.isDestroyed())
                throwingRocks.removeValue(rock, true);
        }

        // move specified units to left if left paddle is touched
        if (isLeftPaddleTouched){
            System.out.println("Move player left");
        }

        // move specified units to right if right paddle is touched
        else if (isRightPaddleTouched){
            System.out.println("Move player right");
        }



    }

    public TextureRegion getFrame(float dt)
    {
        currentState = getState();

        TextureRegion region;
        switch (currentState)
        {
            case DEAD:
                region = playerDead;
                break;
            case JUMPING:
                region = (TextureRegion) playerJump.getKeyFrame(stateTimer, true);
                break;
            case RUNNING:
                region = (TextureRegion) playerRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
                default:
                region = playerStand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
        {
            region.flip(true,false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX())
        {
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer +dt : 0;
        previousState = currentState;
        return region;

    }
    public State getState()
    {
        if (playerIsDead)
        {
            return State.DEAD;
        }
        else if (b2body.getLinearVelocity().y > 0)
        {
            return State.JUMPING;
        }
        else if (b2body.getLinearVelocity().y < 0)
        {
            return State.FALLING;
        }
        else if (b2body.getLinearVelocity().x != 0)
        {
            return State.RUNNING;
        }
        else return State.STANDING;
    }
    public void definePlayer()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(256/ GameMain.PPM,32/ GameMain.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(15/ GameMain.PPM);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameMain.PLAYER_BIT;
        fixtureDef.filter.maskBits = GameMain.GROUND_BIT | GameMain.COLLECTIBLE_BIT
                | GameMain.LOGS_BIT | GameMain.FM_BIT | GameMain.CM_BIT | GameMain.WIN_GAME_BIT | GameMain.LOOSE_GAME_BIT;

        b2body.createFixture(fixtureDef).setUserData(this);

    }
    public static void jump()
    {
        if ( currentState != State.JUMPING )
        {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }
    public void hit()
    {
        playerIsDead = true;
        Filter filter = new Filter();
        filter.maskBits = GameMain.NOTHING_BIT;
        for (Fixture fixture: b2body.getFixtureList())
            fixture.setFilterData(filter);
        b2body.applyLinearImpulse(new Vector2(0,4f),b2body.getWorldCenter(),true);

    }
    public static void throwRock(){
        throwingRocks.add(new ThrowingRocks(screen, b2body.getPosition().x, b2body.getPosition().y, runningRight ? true : false));
    }
    public void draw(Batch batch){
        super.draw(batch);

        for(ThrowingRocks rock : throwingRocks)
            rock.draw(batch);

    }

    public void dieCollision()
    {
        screen.getGame().setScreen(new PlayScreen(screen.getGame()));
        HUD.subtractLives();
    }



    public void setLeftPaddleTouched(boolean isTouched)
    {
        // to restrict motion in only one direction if both are touched
        if(isRightPaddleTouched && isTouched){
            isRightPaddleTouched = false;
            System.out.println("setleftPAddle to touched is false");
        }

        isLeftPaddleTouched = isTouched;
        System.out.println("setleftPAddle to touched");
    }
    public void setRightPaddleTouched(boolean isTouched)
    {
        // to restrict motion if both are touched
        if(isLeftPaddleTouched && isTouched){
            isLeftPaddleTouched = false;
            System.out.println("setLeftPAddle to touched is false");
        }
        System.out.println("setLeftPAddle to touched is true");
        isRightPaddleTouched = isTouched;

    }











}
