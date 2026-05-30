import java.io.*;
import java.util.*;

class Expense implements Serializable {
    double amount;
    String category, date;

    Expense(double amount, String category, String date) {
        this.amount = amount;
        this.category = category; 
        this.date = date;
    }

    public String toString() {
        return "Amount: Rs." + amount +
               " | Category: " + category +
               " | Date: " + date;
    }
}

public class ExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();

    static void addExpense(Scanner sc) {
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Date (MM/YYYY): ");
        String date = sc.nextLine();

        expenses.add(new Expense(amount, category, date));
        System.out.println("Expense Added Successfully!");
    }

    static void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No Expenses Found!");
            return;
        }

        System.out.println("\n===== ALL EXPENSES =====");
        for (Expense e : expenses)
            System.out.println(e);
    }

    static void monthlyReport(Scanner sc) {
        System.out.print("Enter Month/Year (MM/YYYY): ");
        String month = sc.nextLine();

        double total = 0;
        for (Expense e : expenses)
            if (e.date.equals(month))
                total += e.amount;

        System.out.println("Total Expense for " + month + " = Rs." + total);
    }

    static void highestCategory() {
        if (expenses.isEmpty()) {
            System.out.println("No Expenses Available!");
            return;
        }

        HashMap<String, Double> map = new HashMap<>();

        for (Expense e : expenses)
            map.put(e.category,
                    map.getOrDefault(e.category, 0.0) + e.amount);

        String category = "";
        double max = 0;

        for (String key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                category = key;
            }
        }

        System.out.println("Highest Expense Category: " +
                category + " (Rs." + max + ")");
    }

    static void saveData() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream("expenses.dat"))) {

            out.writeObject(expenses);
            System.out.println("Data Saved Successfully!");

        } catch (Exception e) {
            System.out.println("Error Saving Data!");
        }
    }

    @SuppressWarnings("unchecked")
    static void loadData() {
        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream("expenses.dat"))) {

            expenses = (ArrayList<Expense>) in.readObject();

        } catch (Exception e) {
            System.out.println("No Previous Data Found!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        loadData();

        int choice;

        do {
            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Monthly Expense Report");
            System.out.println("4. Highest Expense Category");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addExpense(sc);
                case 2 -> displayExpenses();
                case 3 -> monthlyReport(sc);
                case 4 -> highestCategory();
                case 5 -> saveData();
                case 6 -> {
                    saveData();
                    System.out.println("Thank You!");
                }
                default -> System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}