package me.wappen.gamezexample;

import me.wappen.gamez.GameTime;
import me.wappen.gamez.Window;
import me.wappen.gamez.components.Component;
import me.wappen.gamez.components.KinematicBody;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class WallCollider extends Component {
    private float bounciness = 0.5f;

    @Override
    public void onPhysicsTick(GameTime time) {
        PVector vel = getEntity().getComponent(KinematicBody.class).getVel();
        PVector pos = getNode().getLocalPos();

        if (pos.x < 0) {
            vel.x = Math.abs(vel.x) * bounciness;
            pos.x = 0;
        }
        if (pos.x > Window.getResX()) {
            vel.x = -Math.abs(vel.x) * bounciness;
            pos.x = Window.getResX();
        }
        if (pos.y < 0) {
            vel.y = Math.abs(vel.y) * bounciness;
            pos.y = 0;
        }
        if (pos.y > Window.getResY()) {
            vel.y = -Math.abs(vel.y) * bounciness;
            pos.y = Window.getResY();
        }

        getEntity().getComponent(KinematicBody.class).setVel(vel);
        getNode().setLocalPos(pos);
    }

    public float getBounciness() {
        return bounciness;
    }

    public void setBounciness(float bounciness) {
        this.bounciness = bounciness;
    }
}
