package me.wappen.gamezexample;

import me.wappen.gamez.GameTime;
import me.wappen.gamez.Rect;
import me.wappen.gamez.Window;
import me.wappen.gamez.components.Component;
import me.wappen.gamez.components.KinematicBody;
import me.wappen.gamez.components.colliders.CircleCollider;
import me.wappen.gamez.components.colliders.Collider;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class WallCollider extends Component {
    private float bounciness = 0.5f;
    private Collider collider;

    @Override
    public void onPhysicsTick(GameTime time) {
        PVector vel = getEntity().getComponent(KinematicBody.class).getVel();
        PVector pos = getNode().getLocalPos();

        Rect bounds = collider.getBounds().offset(pos);

        if (bounds.getLeft() < 0) {
            vel.x = Math.abs(vel.x) * bounciness;
            pos.x = bounds.getSize().x / 2;
        }
        if (bounds.getRight() > Window.getResX()) {
            vel.x = -Math.abs(vel.x) * bounciness;
            pos.x = Window.getResX() - bounds.getSize().x / 2;
        }
        if (bounds.getTop() < 0) {
            vel.y = Math.abs(vel.y) * bounciness;
            pos.y = bounds.getSize().y / 2;
        }
        if (bounds.getBottom() > Window.getResY()) {
            vel.y = -Math.abs(vel.y) * bounciness;
            pos.y = Window.getResY() - bounds.getSize().y / 2;
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

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }
}
