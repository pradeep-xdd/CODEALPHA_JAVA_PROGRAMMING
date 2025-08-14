import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    public String getLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }
}

public class Task_1_GradeTracker {
    private ArrayList<Student> students;
    private Scanner sc;
    
    public GradeManager() {
        students = new ArrayList<>();
        sc = new Scanner(System.in);
    }
    
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter grade (0-100): ");
        int grade = sc.nextInt();
        sc.nextLine(); // consume newline
        
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade! Must be between 0-100");
            return;
        }
        
        students.add(new Student(name, grade));
        System.out.println("Student added successfully!\n");
    }
    
    public void updateGrade() {
        if (students.isEmpty()) {
            System.out.println("No students found!\n");
            return;
        }
        
        System.out.print("Enter student name to update: ");
        String name = sc.nextLine();
        
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new grade: ");
                int newGrade = sc.nextInt();
                sc.nextLine();
                
                if (newGrade >= 0 && newGrade <= 100) {
                    student.setGrade(newGrade);
                    System.out.println("Grade updated successfully!\n");
                } else {
                    System.out.println("Invalid grade!\n");
                }
                return;
            }
        }
        System.out.println("Student not found!\n");
    }
    
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!\n");
            return;
        }
        
        System.out.println("=== ALL STUDENTS ===");
        System.out.printf("%-20s %-10s %-10s%n", "Name", "Grade", "Letter");
        System.out.println("----------------------------------------");
        
        for (Student student : students) {
            System.out.printf("%-20s %-10d %-10s%n", 
                student.getName(), 
                student.getGrade(), 
                student.getLetterGrade());
        }
        System.out.println();
    }
    
    public void calculateStats() {
        if (students.isEmpty()) {
            System.out.println("No students found!\n");
            return;
        }
        
        double total = 0;
        int highest = students.get(0).getGrade();
        int lowest = students.get(0).getGrade();
        String topStudent = students.get(0).getName();
        String lowStudent = students.get(0).getName();
        
        for (Student student : students) {
            int grade = student.getGrade();
            total += grade;
            
            if (grade > highest) {
                highest = grade;
                topStudent = student.getName();
            }
            if (grade < lowest) {
                lowest = grade;
                lowStudent = student.getName();
            }
        }
        
        double average = total / students.size();
        
        System.out.println("=== GRADE STATISTICS ===");
        System.out.printf("Total Students: %d%n", students.size());
        System.out.printf("Average Grade: %.2f%n", average);
        System.out.printf("Highest Grade: %d (%s)%n", highest, topStudent);
        System.out.printf("Lowest Grade: %d (%s)%n", lowest, lowStudent);
        System.out.println();
    }
    
    public void generateReport() {
        if (students.isEmpty()) {
            System.out.println("No students found!\n");
            return;
        }
        
        System.out.println("===== STUDENT GRADE REPORT =====");
        
        // Calculate stats
        double total = 0;
        int aCount = 0, bCount = 0, cCount = 0, dCount = 0, fCount = 0;
        
        for (Student student : students) {
            total += student.getGrade();
            
            String letterGrade = student.getLetterGrade();
            switch (letterGrade) {
                case "A": aCount++; break;
                case "B": bCount++; break;
                case "C": cCount++; break;
                case "D": dCount++; break;
                case "F": fCount++; break;
            }
        }
        
        double average = total / students.size();
        
        // Display report
        System.out.printf("Class Size: %d students%n", students.size());
        System.out.printf("Class Average: %.2f%n%n", average);
        
        System.out.println("Grade Distribution:");
        System.out.printf("A grades: %d (%.1f%%)%n", aCount, (aCount * 100.0 / students.size()));
        System.out.printf("B grades: %d (%.1f%%)%n", bCount, (bCount * 100.0 / students.size()));
        System.out.printf("C grades: %d (%.1f%%)%n", cCount, (cCount * 100.0 / students.size()));
        System.out.printf("D grades: %d (%.1f%%)%n", dCount, (dCount * 100.0 / students.size()));
        System.out.printf("F grades: %d (%.1f%%)%n%n", fCount, (fCount * 100.0 / students.size()));
        
        displayAllStudents();
        calculateStats();
    }
    
    public void showMenu() {
        System.out.println("=== STUDENT GRADE MANAGER ===");
        System.out.println("1. Add Student");
        System.out.println("2. Update Grade");
        System.out.println("3. View All Students");
        System.out.println("4. Calculate Statistics");
        System.out.println("5. Generate Full Report");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }
    
    public void run() {
        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateGrade();
                    break;
                case 3:
                    displayAllStudents();
                    break;
                case 4:
                    calculateStats();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    System.out.println("Thanks for using Grade Manager!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!\n");
            }
        }
    }
    
    public static void main(String[] args) {
        GradeManager manager = new GradeManager();
        manager.run();
    }
}
