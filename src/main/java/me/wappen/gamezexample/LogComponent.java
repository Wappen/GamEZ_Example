package me.wappen.gamezexample;

import me.wappen.gamez.GameTime;
import me.wappen.gamez.components.Component;
import me.wappen.gamez.components.colliders.CircleCollider;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class LogComponent extends Component {
    @Override
    public void onTick(GameTime time) {
        System.out.printf("r: %f%n", getEntity().getComponent(CircleCollider.class).getRadius());
    }
}
