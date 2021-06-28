package me.wappen.gamezexample;

import me.wappen.gamez.GameTime;
import me.wappen.gamez.components.Component;
import processing.core.PVector;

/**
 * @author LenzK
 * @since 27.06.2021
 */

public class LogComponent extends Component {
    @Override
    public void onTick(GameTime time) {
        PVector pos = getNode().getPos();
        System.out.printf("delta: %f%nx: %f y: %f z: %f%n", time.getDeltaTime(), pos.x, pos.y, pos.z);
    }
}
