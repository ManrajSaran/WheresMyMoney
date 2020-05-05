package model.users;


import model.exceptions.BudgetLimitReachedException;
import model.exceptions.ExceedingBudgetException;
import model.expenses.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private final HashMap<String, List<Expense>> expenseList;

    private int income;
    private int saving;
    private int shoppingBudget;
    private int foodBudget;
    private int livingBudget;
    private int loansBudget;

    public int shoppingSpending = 0;
    public int foodSpending = 0;
    public int livingSpending = 0;
    public int loansSpending = 0;

    public User() {
        expenseList = new HashMap<>();
        initialiseExpenses();
    }

    private void initialiseExpenses() {
        expenseList.put("Shopping", new ArrayList<>());
        expenseList.put("Food", new ArrayList<>());
        expenseList.put("Living", new ArrayList<>());
        expenseList.put("Loans", new ArrayList<>());


    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setSavings(int savings) {
        this.saving = savings;
    }

    public int getSaving() {
        return this.saving;
    }

    public void setShoppingBudget(int shoppingBudget) {
        this.shoppingBudget = shoppingBudget;
    }

    public void setFoodBudget(int foodBudget) {
        this.foodBudget = foodBudget;
    }

    public void setLivingBudget(int livingBudget) {
        this.livingBudget = livingBudget;
    }

    public void setLoansBudget(int loansBudget) {
        this.loansBudget = loansBudget;
    }

    public boolean checkOkBudget() {
        return income == this.shoppingBudget + this.foodBudget + this.livingBudget + this.loansBudget + this.saving;
    }

    public void makeTransaction(Expense e) throws ExceedingBudgetException, BudgetLimitReachedException {
        List<Expense> temp;
        if (e instanceof Shopping) {
            temp = expenseList.get("Shopping");
            temp.add(e);
            this.shoppingSpending += e.getAmount();
            if (getAmountForType("Shopping") > shoppingBudget) {
                throw new ExceedingBudgetException();
            } else if (getAmountForType("Shopping") == shoppingBudget) {
                throw new BudgetLimitReachedException();
            }
        }
        if (e instanceof Food) {
            temp = expenseList.get("Food");
            temp.add(e);
            this.foodSpending += e.getAmount();
            if (getAmountForType("Food") > foodBudget) {
                throw new ExceedingBudgetException();
            } else if (getAmountForType("Food") == foodBudget) {
                throw new BudgetLimitReachedException();
            }
        }
        if (e instanceof Living) {
            temp = expenseList.get("Living");
            temp.add(e);
            this.livingSpending += e.getAmount();
            if (getAmountForType("Living") > livingBudget) {
                throw new ExceedingBudgetException();
            } else if (getAmountForType("Living") == livingBudget) {
                throw new BudgetLimitReachedException();
            }
        }
        if (e instanceof Loans) {
            temp = expenseList.get("Loans");
            temp.add(e);
            this.loansSpending += e.getAmount();
            if (getAmountForType("Loans") > loansBudget) {
                throw new ExceedingBudgetException();
            } else if (getAmountForType("Loans") == loansBudget) {
                throw new BudgetLimitReachedException();
            }
        }
    }

    public void deleteLatestTransaction(String s) {
        List<Expense> temp = expenseList.get(s);
        int index = temp.size() - 1;
        int amount = 0;
        if (index >= 0) {
            amount = temp.get(index).getAmount();
            temp.remove(index);
        }
        decrementSpending(s, amount);
    }

    private void decrementSpending(String s, int amount) {
        switch (s) {
            case "Shopping":
                this.shoppingSpending -= amount;
                break;
            case "Food":
                this.foodSpending -= amount;
                break;
            case "Living":
                this.livingSpending -= amount;
                break;
            default:
                this.loansSpending -= amount;
                break;
        }
    }

    public int getAmountForType(String s) {
        List<Expense> temp = expenseList.get(s);
        int result = 0;
        result = temp.stream().map(Expense::getAmount).reduce(result, Integer::sum);
        return result;
    }

    private int getTotalFoodShoppingSpending() {
        return this.foodSpending + this.shoppingSpending;
    }

    private double calPercentageFor(int expenseSpending) {
        return (double) expenseSpending / getTotalFoodShoppingSpending();
    }

    public int[] recommendedNewLimits() {
        int[] newLimits = new int[4];
        newLimits[2] = this.livingBudget;
        newLimits[3] = this.loansBudget;
        boolean tooMuchFood = checkOverSpent(this.foodSpending, this.foodBudget);
        boolean tooMuchShopping = checkOverSpent(this.shoppingSpending, this.shoppingBudget);
        if (tooMuchFood && tooMuchShopping) {
            manageDoubleOverSpent(newLimits);
        } else if (tooMuchFood || tooMuchShopping) {
            manageSingleOverSpent(tooMuchFood, tooMuchShopping, newLimits);
        } else {
            addExtraToLoans(newLimits);
        }
        assert (this.income >= newLimits[0] + newLimits[1] + newLimits[2] + newLimits[3]);
        return newLimits;

    }

    private void addExtraToLoans(int[] newLimits) {
        int leftFromShopping = calcLeftOver(this.shoppingSpending, this.shoppingBudget);
        int leftFromFood = calcLeftOver(this.foodSpending, this.foodBudget);
        newLimits[3] = this.loansBudget + leftFromShopping + leftFromFood;
        newLimits[0] = this.shoppingSpending;
        newLimits[1] = this.foodSpending;
    }

    private void manageDoubleOverSpent(int[] newLimits) {
        int totalForTwo = this.foodBudget + this.shoppingBudget;
        double percentageForFood = calPercentageFor(this.foodSpending);
        int newFoodBudget = (int) (percentageForFood * totalForTwo);
        int newShoppingBudget = totalForTwo - newFoodBudget;
        newLimits[0] = newShoppingBudget;
        newLimits[1] = newFoodBudget;
    }

    private void manageSingleOverSpent(boolean tooMuchFood, boolean tooMuchShopping, int[] newLimits) {
        if (tooMuchFood) {
            int anyLeftFromShopping = calcLeftOver(this.shoppingSpending, this.shoppingBudget);
            if (anyLeftFromShopping > 0) {
                newLimits[0] = this.shoppingSpending;
                newLimits[1] = this.foodBudget + anyLeftFromShopping;
            } else {
                newLimits[0] = this.shoppingSpending;
                newLimits[1] = this.foodBudget;
            }
        } else if (tooMuchShopping) {
            int anyLeftFromFood = calcLeftOver(this.foodSpending, this.foodBudget);
            if (anyLeftFromFood > 0) {
                newLimits[0] = this.shoppingBudget + anyLeftFromFood;
                newLimits[1] = this.foodSpending;
            } else {
                newLimits[0] = this.shoppingBudget;
                newLimits[1] = this.foodBudget;
            }
        }
    }

    private int calcLeftOver(int shoppingSpending, int shoppingBudget) {
        return shoppingBudget - shoppingSpending;
    }

    private boolean checkOverSpent(int spending, int budget) {
        return spending > budget;
    }
}
