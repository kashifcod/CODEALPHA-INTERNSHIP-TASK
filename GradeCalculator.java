import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Create arrays to store grades
        int[] grades = new int[numStudents];

        // Input grades
        for (int i = 0; i < numStudents; i++) {
            System.out.print(STR."Enter grade for student \{i + 1}: ");
            grades[i] = scanner.nextInt();
        }

        // Calculate average
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double average = sum / numStudents;

        // Find highest and lowest grades
        int highest = grades[0];
        int lowest = grades[0];
        for (int i = 1; i < numStudents; i++) {
            if (grades[i] > highest) {
                highest = grades[i];
            }
            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }

        // Output results
        System.out.println(STR."Average grade: \{average}");
        System.out.println(STR."Highest grade: \{highest}");
        System.out.println(STR."Lowest grade: \{lowest}");

        scanner.close();
    }
}
