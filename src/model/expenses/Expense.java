package model.expenses;

import java.util.Date;

public abstract class Expense {
    private String description;
    private int amount;
    private Date date;

    Expense(String description, int amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getAmount() {
        return this.amount;
    }
}

