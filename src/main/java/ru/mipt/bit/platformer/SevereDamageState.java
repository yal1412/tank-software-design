package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.objects.Tank;

public class SevereDamageState implements State {
    private final Tank tank;

    public SevereDamageState(Tank tank) {
        this.tank = tank;
        this.tank.setMovementSpeed(tank.getMovementSpeed() * 1.5f);
    }

    @Override
    public boolean canShoot() {
        return false;
    }
}
