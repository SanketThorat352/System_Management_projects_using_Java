import java.util.*;

class Student {
    private String name;
    private int age;
    private String gender;
    private int roomNumber;

    public Student(String name, int age, String gender, int roomNumber) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Room Number: " + roomNumber;
    }
}

class Hostel {
    private Map<Integer, List<Student>> rooms = new HashMap<>();
    private int maxCapacity;

    public Hostel(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean addStudent(Student student) {
        int roomNumber = student.getRoomNumber();
        rooms.putIfAbsent(roomNumber, new ArrayList<>());

        if (rooms.get(roomNumber).size() < maxCapacity) {
            rooms.get(roomNumber).add(student);
            return true;
        } else {
            System.out.println("Room " + roomNumber + " is full.");
            return false;
        }
    }

    public void displayStudents() {
        for (Map.Entry<Integer, List<Student>> entry : rooms.entrySet()) {
            System.out.println("Room " + entry.getKey() + ":");
            for (Student student : entry.getValue()) {
                System.out.println("  " + student);
            }
        }
    }

    public void displayRoomDetails(int roomNumber) {
        if (rooms.containsKey(roomNumber)) {
            System.out.println("Room " + roomNumber + " details:");
            for (Student student : rooms.get(roomNumber)) {
                System.out.println("  " + student);
            }
        } else {
            System.out.println("Room " + roomNumber + " is empty or does not exist.");
        }
    }
}

public class HostelManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hostel hostel = new Hostel(2); // Maximum 2 students per room

        while (true) {
            System.out.println("\nHostel Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Display Room Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();

                    Student student = new Student(name, age, gender, roomNumber);
                    if (hostel.addStudent(student)) {
                        System.out.println("Student added successfully.");
                    }
                    break;

                case 2:
                    hostel.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter room number: ");
                    int room = scanner.nextInt();
                    scanner.nextLine();
                    hostel.displayRoomDetails(room);
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}