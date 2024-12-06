import java.util.*;

class HealthyLifestyleTracker {
    private static User user = new User();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> logWorkout(scanner);
                case 2 -> logNutrition(scanner);
                case 3 -> logSleep(scanner);
                case 4 -> setGoals(scanner);
                case 5 -> viewProgress();
                case 6 -> {
                    System.out.println("Thank you for using the Healthy Lifestyle Tracker!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("\n~~~~~ Healthy Lifestyle Tracker ~~~~~");
        System.out.println("1. Log Workout");
        System.out.println("2. Log Nutrition");
        System.out.println("3. Log Sleep");
        System.out.println("4. Set Goals");
        System.out.println("5. View Progress");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void logWorkout(Scanner scanner) {
        System.out.print("Enter workout description: ");
        String description = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        user.logWorkout(description, duration);
        System.out.println("Workout logged!");
    }

    private static void logNutrition(Scanner scanner) {
        System.out.print("Enter meal description: ");
        String meal = scanner.nextLine();
        System.out.print("Enter calories: ");
        int calories = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        user.logNutrition(meal, calories);
        System.out.println("Nutrition logged!");
    }

    private static void logSleep(Scanner scanner) {
        System.out.print("Enter sleep duration (hours): ");
        int hours = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        user.logSleep(hours);
        System.out.println("Sleep logged!");
    }

    private static void setGoals(Scanner scanner) {
        System.out.print("Enter daily workout goal (minutes): ");
        int workoutGoal = scanner.nextInt();
        System.out.print("Enter daily calorie intake goal: ");
        int calorieGoal = scanner.nextInt();
        System.out.print("Enter daily sleep goal (hours): ");
        int sleepGoal = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        user.setGoals(workoutGoal, calorieGoal, sleepGoal);
        System.out.println("Goals updated!");
    }

    private static void viewProgress() {
        user.viewProgress();
    }
}

class User {
    private int workoutGoal = 0;
    private int calorieGoal = 0;
    private int sleepGoal = 0;
    private int streak = 0;
    private final List<ActivityLog> logs = new ArrayList<>();

    public void logWorkout(String description, int duration) {
        logs.add(new ActivityLog("Workout", description, duration));
        updateStreak();
    }

    public void logNutrition(String meal, int calories) {
        logs.add(new ActivityLog("Nutrition", meal, calories));
    }

    public void logSleep(int hours) {
        logs.add(new ActivityLog("Sleep", "Logged Sleep", hours));
        updateStreak();
    }

    public void setGoals(int workoutGoal, int calorieGoal, int sleepGoal) {
        this.workoutGoal = workoutGoal;
        this.calorieGoal = calorieGoal;
        this.sleepGoal = sleepGoal;
    }

    public void viewProgress() {
        System.out.println("\n=== Progress Report ===");
        System.out.println("Workout Goal: " + workoutGoal + " minutes/day");
        System.out.println("Calorie Goal: " + calorieGoal + " calories/day");
        System.out.println("Sleep Goal: " + sleepGoal + " hours/day");
        System.out.println("Current Streak: " + streak + " days");
        System.out.println("\nLogs:");
        for (ActivityLog log : logs) {
            System.out.println(log);
        }
    }

    private void updateStreak() {
        streak++;
    }
}

class ActivityLog {
    private final String type;
    private final String description;
    private final int value;

    public ActivityLog(String type, String description, int value) {
        this.type = type;
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + ": " + description + " (" + value + ")";
    }
}