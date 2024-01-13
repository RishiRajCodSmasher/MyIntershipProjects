import java.io.*;
import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter methods for username and password

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Expense {
    private String date;
    private String category;
    private double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    // Getter methods for date, category, and amount

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

class ExpenseTracker {
    private List<User> users;
    private List<Expense> expenses;
    private Scanner scanner;

    public ExpenseTracker() {
        users = new ArrayList<>();
        expenses = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // User Registration
    public void registerUser(String username, String password) {
        users.add(new User(username, password));
    }

    // Expense Entry
    public void addExpense(String date, String category, double amount) {
        expenses.add(new Expense(date, category, amount));
    }

    // Expense Listing
    public void listExpenses() {
        for (Expense expense : expenses) {
            System.out.println("Date: " + expense.getDate() +
                               ", Category: " + expense.getCategory() +
                               ", Amount: $" + expense.getAmount());
        }
    }

    // Category-wise Summation
    public void calculateCategoryTotal() {
        Map<String, Double> categoryTotal = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double currentTotal = categoryTotal.getOrDefault(category, 0.0);
            categoryTotal.put(category, currentTotal + expense.getAmount());
        }

        System.out.println("Category-wise Total Expenses:");
        for (Map.Entry<String, Double> entry : categoryTotal.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    // Persistence - Save and Load
    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("expenses.ser"))) {
            oos.writeObject(users);
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("expenses.ser"))) {
            users = (List<User>) ois.readObject();
            expenses = (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.loadDataFromFile();  // Load existing data if available

        // Sample user registration
        tracker.registerUser("user1", "password123");

        // Sample expense entries
        tracker.addExpense("2024-01-06", "Groceries", 50.0);
        tracker.addExpense("2024-01-07", "Utilities", 100.0);
        tracker.addExpense("2024-01-08", "Entertainment", 30.0);

        // Display expenses
        System.out.println("All Expenses:");
        tracker.listExpenses();

        // Display category-wise total expenses
        tracker.calculateCategoryTotal();

        // Save data to file for persistence
        tracker.saveDataToFile();
    }
}
