package me.wappen.gamezexample;

import me.wappen.gamez.GameTime;
import me.wappen.gamez.KeyState;
import me.wappen.gamez.components.Component;
import me.wappen.gamez.components.KinematicBody;
import processing.core.PVector;

import java.awt.event.KeyEvent;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class KeyboardController extends Component {
    private float maxFuel = 20f;
    private float fuelRate = 40f;
    private float refuelRate = 20f;
    private float fuel;

    @Override
    public void onSpawn() {
        fuel = maxFuel;
    }

    @Override
    public void onTick(GameTime time) {
        if (getGame().getKeyState(KeyEvent.VK_UP) == KeyState.DOWN) {
            float power = fuelRate * time.getDeltaTime();
            if (power > fuel) power = fuel;
            getEntity().getComponent(KinematicBody.class).getVel().add(0, -power);
            fuel -= power;
        }
        if (getGame().getKeyState(KeyEvent.VK_UP) == KeyState.UP) {
            float refuel = refuelRate * time.getDeltaTime();
            if ((fuel + refuel) > maxFuel) refuel = maxFuel - fuel;
            fuel += refuel;
        }

        if (getGame().getKeyState(KeyEvent.VK_LEFT) == KeyState.DOWN)
            getEntity().getComponent(KinematicBody.class).getVel().add(-10 * time.getDeltaTime(), 0);
        if (getGame().getKeyState(KeyEvent.VK_RIGHT) == KeyState.DOWN)
            getEntity().getComponent(KinematicBody.class).getVel().add(10 * time.getDeltaTime(), 0);

        if (getGame().getKeyState(KeyEvent.VK_BACK_SPACE) == KeyState.PRESSED)
            getGame().findEntity("boxParent").getNode().delete();
        if (getGame().getKeyState(KeyEvent.VK_SPACE) == KeyState.PRESSED)
            getGame().findEntity("boxParent").getNode().forEachChild(
                    c -> c.getEntity().getComponent(KinematicBody.class).getVel().add(PVector.random2D().mult(12f)));

        if (getGame().getKeyState(KeyEvent.VK_1) == KeyState.PRESSED)
            getGame().getRoot().forEachChild(
                    c -> {
                        KinematicBody kb = c.getEntity().getComponent(KinematicBody.class);
                        if (kb != null)
                            kb.setGravity(0);
                    });
        if (getGame().getKeyState(KeyEvent.VK_2) == KeyState.PRESSED)
            getGame().getRoot().forEachChild(
                    c -> {
                        KinematicBody kb = c.getEntity().getComponent(KinematicBody.class);
                        if (kb != null)
                            kb.setGravity(9.807f);
                    });

        if (getGame().getKeyState(KeyEvent.VK_SUBTRACT) == KeyState.DOWN)
            getGame().getView().getSize().mult(0.99f);
        if (getGame().getKeyState(KeyEvent.VK_ADD) == KeyState.DOWN)
            getGame().getView().getSize().mult(1.01f);

        if (getGame().getKeyState(KeyEvent.VK_W) == KeyState.DOWN)
            getGame().findEntity("boxParent").getNode().getLocalPos().y -= 100 * time.getDeltaTime();

        if (getGame().getKeyState(KeyEvent.VK_Q) == KeyState.PRESSED)
            for (int i = 0; i < 30000000; i++) { System.out.print(""); }
    }
}
