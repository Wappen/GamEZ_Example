package me.wappen.gamezexample;

import me.wappen.gamez.Entity;
import me.wappen.gamez.Game;
import me.wappen.gamez.Node;
import me.wappen.gamez.Window;
import me.wappen.gamez.components.KinematicBody;
import me.wappen.gamez.components.shapes.RectangleShape;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class GameExample extends Game {
    public static void main(String[] args) {
        new GameExample().run(800, 800);
    }

    @Override
    public void start() {
        getView().setColor(0xff_c8d2ff);

        Entity player = new Entity("player",
                new RectangleShape(10, 10, 0xff_f00101),
                new KinematicBody(),
                new KeyboardController(),
                new WallCollider());
        player.getComponent(KinematicBody.class).setGravity(9.807f);
        player.getComponent(KinematicBody.class).setDrag(0.6f);
        spawn(player);
        player.getNode().getLocalPos().z += 10;

        Node boxParent = spawn(new Entity("boxParent"));

        for (int i = 0; i < 500; i++) {
            Entity box = new Entity("box" + i,
                    new RectangleShape(10, 10, 0xff_01f001),
                    new KinematicBody(),
                    new WallCollider());
            box.getComponent(KinematicBody.class).setGravity(9.807f);
            box.getComponent(KinematicBody.class).setDrag(0.2f);
            box.getComponent(KinematicBody.class).setVel(PVector.random2D().mult(10f));
            box.getComponent(WallCollider.class).setBounciness(0.8f);
            spawn(box, boxParent);

            PVector pos = new PVector(Window.getResX() / 2f, Window.getResY() / 2f).add(PVector.random2D().mult(50f));
            box.getNode().setPos(pos);
        }
    }
}
