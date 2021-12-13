package ru.mipt.bit.platformer.driver.observation;

import ru.mipt.bit.platformer.objects.gameObjects.GameObject;
/**
 * Entity
 */
public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Event event, GameObject object);
}
