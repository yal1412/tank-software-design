package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.objects.Tank;

import java.util.Date;

public class NoDamageState implements State {
    private final Tank tank;

    public NoDamageState(Tank tank) {
        this.tank = tank;
    }

    @Override
    public boolean canShoot() {
        long time = new Date().getTime();
        long delta = time - tank.getLastTimeShooting();
        if (delta < 4000)
            return false;
        tank.setLastTimeShooting(time);
        return true;
    }
}
