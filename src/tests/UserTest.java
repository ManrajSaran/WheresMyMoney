package tests;

import model.exceptions.BudgetLimitReachedException;
import model.exceptions.ExceedingBudgetException;
import model.expenses.*;
import model.users.User;
import model.users.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    private User user1;
    private User user2;
    private UserManager manager;
    private Date date;


    @BeforeEach
    void runBefore() {
        manager = new UserManager();
        user1 = new User(manager);
        user2 = new User(manager);
        user1.setIncome(50000000);
        user1.setShoppingBudget(10000000);
        user1.setFoodBudget(10000000);
        user1.setLivingBudget(10000000);
        user1.setLoansBudget(10000000);
        user1.setSavings(10000000);


        user2.setIncome(50000000);
        user2.setShoppingBudget(10000000);
        user2.setFoodBudget(10000000);
        user2.setLivingBudget(10000000);
        user2.setLoansBudget(10000000);
        user2.setSavings(10000000);

        date = new Date(2020, Calendar.JANUARY, 1);

    }

    @Test
    void testOkBudget() {
        assertTrue(user1.checkOkBudget());

        user1.setFoodBudget(9999999);
        assertFalse(user1.checkOkBudget());

        user2.setFoodBudget(10000001);
        assertFalse(user2.checkOkBudget());
    }

    @Test
    void testSingleTransactionPerType() {
        try {
            Expense expense = new Shopping("Shopping", 10, date);
            user1.makeTransaction(expense);

            assertEquals(10, user1.getAmountForType("Shopping"));
            assertEquals(0, user1.getAmountForType("Food"));
            assertEquals(0, user1.getAmountForType("Living"));
            assertEquals(0, user1.getAmountForType("Loans"));

            Expense expense2 = new Food("Food", 50, date);
            user1.makeTransaction(expense2);
            assertEquals(10, user1.getAmountForType("Shopping"));
            assertEquals(50, user1.getAmountForType("Food"));
            assertEquals(0, user1.getAmountForType("Living"));
            assertEquals(0, user1.getAmountForType("Loans"));

            Expense expense3 = new Living("Living", 454329, date);
            user1.makeTransaction(expense3);
            assertEquals(10, user1.getAmountForType("Shopping"));
            assertEquals(50, user1.getAmountForType("Food"));
            assertEquals(454329, user1.getAmountForType("Living"));
            assertEquals(0, user1.getAmountForType("Loans"));

            Expense expense4 = new Loans("Loans", 1000, date);
            user1.makeTransaction(expense4);
            assertEquals(10, user1.getAmountForType("Shopping"));
            assertEquals(50, user1.getAmountForType("Food"));
            assertEquals(454329, user1.getAmountForType("Living"));
            assertEquals(1000, user1.getAmountForType("Loans"));
        } catch (ExceedingBudgetException | BudgetLimitReachedException e) {
            fail();
        }
    }

    @Test
    void testMultipleTransactionPerType() {
        int expectedShoppingAmount = 0;
        int expectedFoodAmount = 0;
        int expectedLivingAmount = 0;
        int expectedLoansAmount = 0;
        assertEquals(0, user1.getAmountForType("Shopping"));
        assertEquals(0, user1.getAmountForType("Food"));
        assertEquals(0, user1.getAmountForType("Living"));
        assertEquals(0, user1.getAmountForType("Loans"));

        for (int i = 123; i < 133; i++) {
            expectedShoppingAmount += i;
            expectedFoodAmount += i * 2;
            expectedLivingAmount += i * 3;
            expectedLoansAmount += i / 2;

            Expense expense = new Shopping("Shopping", i, date);
            Expense expense2 = new Food("Food", i * 2, date);
            Expense expense3 = new Living("Living", i * 3, date);
            Expense expense4 = new Loans("Loans", i / 2, date);

            try {
                user1.makeTransaction(expense);
                user1.makeTransaction(expense2);
                user1.makeTransaction(expense3);
                user1.makeTransaction(expense4);
            } catch (ExceedingBudgetException | BudgetLimitReachedException e) {
                fail();
            }
        }
        assertEquals(expectedShoppingAmount, user1.getAmountForType("Shopping"));
        assertEquals(expectedFoodAmount, user1.getAmountForType("Food"));
        assertEquals(expectedLivingAmount, user1.getAmountForType("Living"));
        assertEquals(expectedLoansAmount, user1.getAmountForType("Loans"));
    }

    @Test
    void testExpectLimitExceptionFromTransaction() {
        try {
            Expense expense = new Shopping("Shopping", 10000000, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignored) {
        }

        try {
            Expense expense = new Food("Food", 10000000, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignored) {
        }

        try {
            Expense expense = new Living("Living", 10000000, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignored) {
        }

        try {
            Expense expense = new Loans("Loans", 10000000, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignored) {
        }
    }

    @Test
    void testExpectExceedExceptionFromTransaction() {
        try {
            Expense expense = new Shopping("Shopping", 10000001, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException ignored) {
        } catch (BudgetLimitReachedException e) {
            fail();
        }

        try {
            Expense expense = new Food("Food", 10000001, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException ignored) {
        } catch (BudgetLimitReachedException e) {
            fail();
        }

        try {
            Expense expense = new Living("Living", 10000001, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException ignored) {
        } catch (BudgetLimitReachedException e) {
            fail();
        }

        try {
            Expense expense = new Loans("Loans", 10000001, date);
            user1.makeTransaction(expense);
            fail();
        } catch (ExceedingBudgetException ignored) {
        } catch (BudgetLimitReachedException e) {
            fail();
        }
    }

    @Test
    void testDeleteLastTransaction() {
        try {
            Expense expense = new Shopping("Shopping", 10, date);
            Expense expense2 = new Food("Food", 50, date);
            Expense expense3 = new Living("Living", 454329, date);
            Expense expense4 = new Loans("Loans", 1000, date);

            for (int i = 0; i < 2; i++) {
                user1.makeTransaction(expense);
                user1.makeTransaction(expense2);
                user1.makeTransaction(expense3);
                user1.makeTransaction(expense4);
            }

            assertEquals(20, user1.getAmountForType("Shopping"));
            assertEquals(100, user1.getAmountForType("Food"));
            assertEquals(908658, user1.getAmountForType("Living"));
            assertEquals(2000, user1.getAmountForType("Loans"));

            user1.deleteLatestTransaction("Shopping");
            assertEquals(10, user1.getAmountForType("Shopping"));
            user1.deleteLatestTransaction("Food");
            assertEquals(50, user1.getAmountForType("Food"));
            user1.deleteLatestTransaction("Living");
            assertEquals(454329, user1.getAmountForType("Living"));
            user1.deleteLatestTransaction("Loans");
            assertEquals(1000, user1.getAmountForType("Loans"));

            user1.deleteLatestTransaction("Shopping");
            assertEquals(0, user1.getAmountForType("Shopping"));
            user1.deleteLatestTransaction("Food");
            assertEquals(0, user1.getAmountForType("Food"));
            user1.deleteLatestTransaction("Living");
            assertEquals(0, user1.getAmountForType("Living"));
            user1.deleteLatestTransaction("Loans");
            assertEquals(0, user1.getAmountForType("Loans"));

        } catch (ExceedingBudgetException | BudgetLimitReachedException e) {
            fail();
        }
    }

    @Test
    void testRecommendNewLimitAllSpendExact() {
        try {
            Expense expense = new Shopping("Shopping", 10000000, date);
            Expense expense2 = new Food("Food", 10000000, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            try {
                user1.makeTransaction(expense);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense2);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {

            }

            int[] newLimits = user1.recommendedNewLimits();

            assertEquals(10000000, newLimits[0]);
            assertEquals(10000000, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        }
    }

    @Test
    void testRecommendNewLimitUnderSpendFoodAndShopping() {
        try {
            Expense expense = new Shopping("Shopping", 9999999, date);
            Expense expense2 = new Food("Food", 9999999, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            user1.makeTransaction(expense);
            user1.makeTransaction(expense2);
            try {
                user1.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user1.recommendedNewLimits();

            assertEquals(9999999, newLimits[0]);
            assertEquals(9999999, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000002, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }
    }

    @Test
    void testRecommendNewLimitsUnderSpendFoodOrShopping() {
        try {
            Expense expense = new Shopping("Shopping", 9999999, date);
            Expense expense2 = new Food("Food", 10000000, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            user1.makeTransaction(expense);
            try {
                user1.makeTransaction(expense2);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user1.recommendedNewLimits();

            assertEquals(9999999, newLimits[0]);
            assertEquals(10000000, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000001, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }

        try {
            Expense expense = new Shopping("Shopping", 10000000, date);
            Expense expense2 = new Food("Food", 9999999, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            user2.makeTransaction(expense2);
            try {
                user2.makeTransaction(expense);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user2.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user2.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user2.recommendedNewLimits();

            assertEquals(10000000, newLimits[0]);
            assertEquals(9999999, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000001, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }
    }

    @Test
    void testRecommendNewLimitsSingleOverSpentWithLeftOver() {
        try {
            Expense expense = new Shopping("Shopping", 9999999, date);
            Expense expense2 = new Food("Food", 10000001, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            user1.makeTransaction(expense);
            try {
                user1.makeTransaction(expense2);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user1.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user1.recommendedNewLimits();

            assertEquals(9999999, newLimits[0]);
            assertEquals(10000001, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }

        try {
            Expense expense = new Shopping("Shopping", 10000005, date);
            Expense expense2 = new Food("Food", 9999995, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            user2.makeTransaction(expense2);
            try {
                user2.makeTransaction(expense);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user2.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user2.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user2.recommendedNewLimits();

            assertEquals(10000005, newLimits[0]);
            assertEquals(9999995, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }
    }

    @Test
    void testRecommendNewLimitsSingleOverSpentNoLeftOver() {
        try {
            Expense expense = new Shopping("Shopping", 10000000, date);
            Expense expense2 = new Food("Food", 10000001, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            try {
                user1.makeTransaction(expense);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense2);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user1.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user1.recommendedNewLimits();

            assertEquals(10000000, newLimits[0]);
            assertEquals(10000000, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }

        try {
            Expense expense = new Shopping("Shopping", 10000005, date);
            Expense expense2 = new Food("Food", 10000000, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            try {
                user2.makeTransaction(expense2);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user2.makeTransaction(expense);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user2.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user2.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user2.recommendedNewLimits();

            assertEquals(10000000, newLimits[0]);
            assertEquals(10000000, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }
    }

    @Test
    void testRecommendNewLimitsDoubleOverSpent() {
        try {
            Expense expense = new Shopping("Shopping", 10000020, date);
            Expense expense2 = new Food("Food", 10000020, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            try {
                user1.makeTransaction(expense);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user1.makeTransaction(expense2);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user1.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user1.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user1.recommendedNewLimits();

            assertEquals(10000000, newLimits[0]);
            assertEquals(10000000, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }

        try {
            Expense expense = new Shopping("Shopping", 10000011, date);
            Expense expense2 = new Food("Food", 11111111, date);
            Expense expense3 = new Living("Living", 10000000, date);
            Expense expense4 = new Loans("Loans", 10000000, date);

            try {
                user2.makeTransaction(expense2);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user2.makeTransaction(expense);
            } catch (ExceedingBudgetException ignore) {
            }
            try {
                user2.makeTransaction(expense3);
            } catch (BudgetLimitReachedException ignore) {
            }
            try {
                user2.makeTransaction(expense4);
            } catch (BudgetLimitReachedException ignore) {
            }

            int[] newLimits = user2.recommendedNewLimits();

            assertEquals(9473690, newLimits[0]);
            assertEquals(10526310, newLimits[1]);
            assertEquals(10000000, newLimits[2]);
            assertEquals(10000000, newLimits[3]);

        } catch (ExceedingBudgetException e) {
            fail();
        } catch (BudgetLimitReachedException ignore) {
        }
    }
}
