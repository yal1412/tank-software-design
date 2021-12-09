package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.objects.GameObject;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Event event, GameObject object);
}
