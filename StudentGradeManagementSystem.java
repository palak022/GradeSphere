import java.util.Scanner;

public class StudentGradeManagementSystem {

    static int calculateTotal(int[] marks) {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum;
    }

    static double calculateAverage(int total, int subjects) {
        return (double) total / subjects;
    }

    static String calculateGrade(double avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    static double calculateGPA(String grade) {
        switch (grade) {
            case "A+": return 10.0;
            case "A": return 9.0;
            case "B": return 8.0;
            case "C": return 7.0;
            case "D": return 6.0;
            default: return 0.0;
        }
    }

    static boolean hasFailedAnySubject(int[] marks) {
        for (int m : marks) if (m < 35) return true;
        return false;
    }

    static String getResult(int[] marks, String grade) {
        if (hasFailedAnySubject(marks)) return "FAIL";
        return grade.equals("F") ? "FAIL" : "PASS";
    }

    static String getRemarks(String grade) {
        switch (grade) {
            case "A+": return "Outstanding Performance";
            case "A": return "Excellent Performance";
            case "B": return "Good Performance";
            case "C": return "Average Performance";
            case "D": return "Below Average";
            default: return "Poor Performance";
        }
    }

    static int getHighest(int[] marks) {
        int max = marks[0];
        for (int m : marks) if (m > max) max = m;
        return max;
    }

    static int getLowest(int[] marks) {
        int min = marks[0];
        for (int m : marks) if (m < min) min = m;
        return min;
    }

    static String getRank(double avg) {
        if (avg >= 85) return "Top Rank";
        else if (avg >= 70) return "Merit Rank";
        else if (avg >= 50) return "Average Rank";
        else return "Needs Improvement";
    }

    static String scholarshipEligibility(double avg, int attendance) {
        if (avg >= 85 && attendance >= 75) return "Eligible";
        return "Not Eligible";
    }

    static void performanceSummary(int[] marks, String[] subjects) {
        System.out.println("\n+---------------- PERFORMANCE SUMMARY ----------------+");
        for (int i = 0; i < marks.length; i++) {
            String status;
            if (marks[i] >= 75) status = "Strong";
            else if (marks[i] < 35) status = "Weak (Fail)";
            else status = "Average";

            System.out.printf("| %-20s : %-15s |\n", subjects[i], status);
        }
        System.out.println("+----------------------------------------------------+");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char choice;

        do {
            System.out.println("\n====================================================");
            System.out.println("        STUDENT GRADE MANAGEMENT SYSTEM");
            System.out.println("====================================================");

            sc.nextLine();
            System.out.print("Enter Student Name        : ");
            String name = sc.nextLine();

            System.out.print("Enter Roll Number         : ");
            int rollNo = sc.nextInt();

            System.out.print("Enter Number of Subjects  : ");
            int n = sc.nextInt();

            String[] subjectNames = new String[n];
            int[] marks = new int[n];

            sc.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.print("Enter Subject " + (i + 1) + " Name     : ");
                subjectNames[i] = sc.nextLine();
            }

            System.out.println("\n---------------- ENTER MARKS ----------------");
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Marks for " + subjectNames[i] + " : ");
                    int m = sc.nextInt();
                    if (m >= 0 && m <= 100) {
                        marks[i] = m;
                        break;
                    } else {
                        System.out.println("Invalid input. Marks must be 0 to 100.");
                    }
                }
            }

            System.out.print("\nEnter Attendance Percentage : ");
            int attendance = sc.nextInt();

            int total = calculateTotal(marks);
            double avg = calculateAverage(total, n);
            String grade = calculateGrade(avg);
            double gpa = calculateGPA(grade);
            String result = getResult(marks, grade);

            System.out.println("\n================ STUDENT REPORT ================");
            System.out.printf("Name        : %s\n", name);
            System.out.printf("Roll No     : %d\n", rollNo);
            System.out.printf("Attendance  : %d%%\n", attendance);

            System.out.println("\n+---------------- SUBJECT MARKS ----------------+");
            System.out.printf("| %-20s | %-10s |\n", "Subject", "Marks");
            System.out.println("+----------------------+------------+");
            for (int i = 0; i < n; i++) {
                System.out.printf("| %-20s | %-10d |\n", subjectNames[i], marks[i]);
            }
            System.out.println("+----------------------------------------------+");

            System.out.println("\n---------------- FINAL RESULT ----------------");
            System.out.printf("Total Marks   : %d\n", total);
            System.out.printf("Percentage    : %.2f%%\n", avg);
            System.out.printf("Grade         : %s\n", grade);
            System.out.printf("GPA           : %.1f\n", gpa);
            System.out.printf("Result        : %s\n", result);
            System.out.printf("Rank Category : %s\n", getRank(avg));
            System.out.printf("Scholarship   : %s\n", scholarshipEligibility(avg, attendance));
            System.out.printf("Highest Marks : %d\n", getHighest(marks));
            System.out.printf("Lowest Marks  : %d\n", getLowest(marks));
            System.out.printf("Remarks       : %s\n", getRemarks(grade));

            performanceSummary(marks, subjectNames);

            System.out.println("====================================================");

            System.out.print("\nDo you want to calculate again? (Y/N): ");
            choice = sc.next().charAt(0);

        } while (choice == 'Y' || choice == 'y');

        System.out.println("\nThank you for using Student Grade Management System.");
        sc.close();
    }
}
