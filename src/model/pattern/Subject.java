package model.pattern;

import model.users.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();

    protected void addObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObservers(User s) {
        observers.forEach((observer) -> observer.update(s));
    }
}
