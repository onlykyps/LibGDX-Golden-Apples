package com.rowg.goldenapples.Tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.rowg.goldenapples.Collectables.EndGame;
import com.rowg.goldenapples.Collectables.Hazards;
import com.rowg.goldenapples.Collectables.InteractiveTileObject;
import com.rowg.goldenapples.Collectables.Logs;
import com.rowg.goldenapples.Collectables.ThrowingRocks;
import com.rowg.goldenapples.GameMain;
import com.rowg.goldenapples.Scenes.HUD;
import com.rowg.goldenapples.Sprites.CuteMonster;
import com.rowg.goldenapples.Sprites.Enemy;
import com.rowg.goldenapples.Sprites.FlyingMonster;
import com.rowg.goldenapples.Sprites.Player;

/**
 * Created by claud on 16/05/2018.
 */

public class WorldContactListener implements ContactListener
{
    protected Body body;
    @Override
    public void beginContact(Contact contact)
    {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cDef)
        {
            case GameMain.FM_BIT | GameMain.LOGS_BIT:
                if (fixA.getFilterData().categoryBits == GameMain.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true,false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true,false);
                break;
            case GameMain.CM_BIT | GameMain.LOGS_BIT:
                if (fixA.getFilterData().categoryBits == GameMain.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true,false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true,false);
                break;
            case GameMain.PLAYER_BIT | GameMain.CM_BIT:
                if (fixA.getFilterData().categoryBits == GameMain.PLAYER_BIT)
                {
                    ((Player)fixA.getUserData()).hit();
                    ((Player)fixA.getUserData()).dieCollision();
                }

                else
                    ((Player)fixB.getUserData()).hit();
                    ((Player)fixA.getUserData()).dieCollision();
                break;
            case GameMain.PLAYER_BIT | GameMain.FM_BIT:
                if (fixA.getFilterData().categoryBits == GameMain.PLAYER_BIT)
                {
                    ((Player)fixA.getUserData()).hit();
                    ((Player)fixA.getUserData()).dieCollision();
                }
                else
                    ((Player)fixB.getUserData()).hit();
                    ((Player)fixA.getUserData()).dieCollision();
                break;
            case GameMain.ENEMY_BIT | GameMain.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).reverseVelocity(true,false);
                ((Enemy)fixB.getUserData()).reverseVelocity(true,false);
                break;
            case GameMain.PLAYER_BIT | GameMain.COLLECTIBLE_BIT:
                ((InteractiveTileObject)fixA.getUserData()).objectCollision();
                break;
            case GameMain.PLAYER_BIT | GameMain.LOGS_BIT:
                ((Logs)fixA.getUserData()).logCollision();
                break;
            case GameMain.THROWING_ROCK_BIT | GameMain.CM_BIT:

                if(fixA.getFilterData().categoryBits == GameMain.CM_BIT)
                {
                    ((CuteMonster) fixA.getUserData()).hitEnemy();
                    ((ThrowingRocks)fixB.getUserData()).setToDestroy();
//                    HUD.subtractRocks();
                }
                else if (fixB.getFilterData().categoryBits == GameMain.CM_BIT)
                {
                    ((CuteMonster) fixB.getUserData()).hitEnemy();
                    ((ThrowingRocks)fixA.getUserData()).setToDestroy();
//                    HUD.subtractRocks();
                }

                System.out.println("Enemy hit!");
                break;

            case GameMain.THROWING_ROCK_BIT | GameMain.FM_BIT:

                if(fixA.getFilterData().categoryBits == GameMain.FM_BIT)
                {
                    ((FlyingMonster) fixA.getUserData()).hitEnemy();
                    ((ThrowingRocks) fixB.getUserData()).setToDestroy();
//                    HUD.subtractRocks();
                }

                else if(fixB.getFilterData().categoryBits == GameMain.FM_BIT)
                {
                    ((FlyingMonster)fixB.getUserData()).hitEnemy();
                    ((ThrowingRocks)fixA.getUserData()).setToDestroy();
//                    HUD.subtractRocks();

                }
                break;

            case GameMain.PLAYER_BIT | GameMain.WIN_GAME_BIT:
                System.out.println("Win Game");
                ((EndGame)fixA.getUserData()).winGameCollision();
                break;
            case GameMain.PLAYER_BIT | GameMain.LOOSE_GAME_BIT:
                System.out.println("Loose Game");
                ((Hazards)fixA.getUserData()).looseGameCollision();
                break;

        }

    }
    @Override
    public void endContact(Contact contact) {

    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
