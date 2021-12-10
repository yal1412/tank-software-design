package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.objects.GameObject;

public interface Observer {
    void update(Event event, GameObject gameObject, int id);
}
