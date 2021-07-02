package me.wappen.gamezexample;

import me.wappen.gamez.*;
import me.wappen.gamez.components.KinematicBody;
import me.wappen.gamez.components.colliders.CircleCollider;
import me.wappen.gamez.components.shapes.CircleShape;
import me.wappen.gamez.components.shapes.ForceVisualizerShape;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class GameExample extends Game {
    public static void main(String[] args) {
        new GameExample().run(600, 800);
    }

    @Override
    public void start() {
        getView().setColor(0xff_c8d2ff);

        Entity player = new Entity("player",
                new CircleShape(20, 0xff_f00101),
                new CircleCollider(20),
                new KinematicBody(),
                new KeyboardController(),
                new ForceVisualizerShape(),
                new WallCollider());
        player.getComponent(KinematicBody.class).setGravity(9.807f);
        player.getComponent(KinematicBody.class).setDrag(0.4f);
        player.getComponent(WallCollider.class).setCollider(player.getComponent(CircleCollider.class));
        spawn(player);
        player.getNode().getLocalPos().z += 10;

        Entity coll = new Entity("coll",
                new CircleShape(50, 0xff_f0f001),
                new CircleCollider(50),
                new KinematicBody(),
                new ForceVisualizerShape(),
                new WallCollider());
        coll.getComponent(KinematicBody.class).setGravity(9.807f);
        coll.getComponent(KinematicBody.class).setDrag(0.0f);
        coll.getComponent(KinematicBody.class).setVel(PVector.random2D().mult(2f));
        coll.getComponent(WallCollider.class).setCollider(coll.getComponent(CircleCollider.class));
        coll.getComponent(WallCollider.class).setBounciness(1.0f);
        spawn(coll);
        coll.getNode().setPos(new PVector(100, 100));

        Node boxParent = spawn(new Entity("boxParent"));

        for (int i = 0; i < 3; i++) {
            Entity box = new Entity("box" + i,
                    new CircleShape(20, 0xff_01f001),
                    new CircleCollider(20),
                    new KinematicBody(),
                    new ForceVisualizerShape(),
                    new WallCollider());
            box.getComponent(KinematicBody.class).setGravity(9.807f);
            box.getComponent(KinematicBody.class).setDrag(0.6f);
            box.getComponent(KinematicBody.class).setVel(PVector.random2D().mult(1f));
            box.getComponent(WallCollider.class).setCollider(box.getComponent(CircleCollider.class));
            box.getComponent(WallCollider.class).setBounciness(1.0f);
            spawn(box, boxParent);

            PVector pos = new PVector(Window.getResX() / 2f, Window.getResY() / 2f).add(PVector.random2D().mult(50f));
            box.getNode().setPos(pos);
        }
    }
}
