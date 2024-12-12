import java.util.ArrayList;
import java.util.Scanner;

// Main Class
public class GymManagementSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Member> members = new ArrayList<>();
    static ArrayList<Trainer> trainers = new ArrayList<>();

    public static void main(String[] args) {
        // Sample Trainers
        trainers.add(new Trainer(1, "John", "Strength Training"));
        trainers.add(new Trainer(2, "Emily", "Yoga"));

        while (true) {
            System.out.println("\n--- Gym Management System ---");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Add Attendance");
            System.out.println("4. View Trainers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    viewMembers();
                    break;
                case 3:
                    addAttendance();
                    break;
                case 4:
                    viewTrainers();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new member
    private static void addMember() {
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Member Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Membership Type (Monthly/Yearly): ");
        String membershipType = scanner.nextLine();

        int id = members.size() + 1;
        members.add(new Member(id, name, age, membershipType));
        System.out.println("Member added successfully!");
    }

    // View all members
    private static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        System.out.println("\n--- Member List ---");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // Add attendance for a member
    private static void addAttendance() {
        if (members.isEmpty()) {
            System.out.println("No members available to mark attendance.");
            return;
        }

        System.out.println("\n--- Members ---");
        for (Member member : members) {
            System.out.println(member);
        }

        System.out.print("Enter Member ID to mark attendance: ");
        int memberId = scanner.nextInt();
        Member member = findMemberById(memberId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        member.incrementAttendance();
        System.out.println("Attendance marked for " + member.getName() + ". Total Attendance: " + member.getAttendance());
    }

    // View all trainers
    private static void viewTrainers() {
        if (trainers.isEmpty()) {
            System.out.println("No trainers available.");
            return;
        }

        System.out.println("\n--- Trainer List ---");
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }
    }

    // Helper to find a member by ID
    private static Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) return member;
        }
        return null;
    }
}

// Member Class
class Member {
    private int id;
    private String name;
    private int age;
    private String membershipType;
    private int attendance;

    public Member(int id, String name, int age, String membershipType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
        this.attendance = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }

    public void incrementAttendance() {
        this.attendance++;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Membership: " + membershipType + ", Attendance: " + attendance;
    }
}

// Trainer Class
class Trainer {
    private int id;
    private String name;
    private String specialization;

    public Trainer(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}
