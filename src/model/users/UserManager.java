package model.users;

import model.pattern.Observer;

import java.util.ArrayList;
import java.util.List;

public class UserManager implements Observer {
    private List<User> programUsers = new ArrayList<>();

    @Override
    public void update(User s) {
        programUsers.add(s);
    }

}

