package ru.mipt.bit.platformer;

public interface Observable {
    void addObserver(Object o);
    void removeObserver(Object o);
    void notifyObservers(Event event, GameObject object);
}
