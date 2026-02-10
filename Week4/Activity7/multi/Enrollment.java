public class Enrollment {
    public static class Course {
        String courseCode;
        String courseTitle;
        Student[] enrolledStudents;
        int enrollmentCount = 0;
        static String schoolName = "My University";

        public Course(String courseCode, String courseTitle) {
            this.courseCode = courseCode;
            this.courseTitle = courseTitle;
            this.enrolledStudents = new Student[50];
        }

        public void enrollStudent(Student student) {
            enrolledStudents[enrollmentCount] = student;
            enrollmentCount += 1;
        }

        public void displayCourseInfo() {
            System.out.println("School Name  : " + schoolName);
            System.out.println("Course Code  : " + courseCode);
            System.out.println("Course Title : " + courseTitle);
            System.out.println();
            System.out.println("Students");
            for (int x = 0; x < this.enrollmentCount; x++) {
                System.out.println(enrolledStudents[x].getFormalName());
            }
        }

        public static String getSchoolName() {
            return schoolName;
        }
    }

    public static class Student {
        int studentId;
        String firstName;
        String middleName;
        String lastName;
        String gender;
        String email;

        static int totalStudents = 0;

        public Student(int studentId, String firstName, String middleName, String lastName, String gender,
                String email) {
            Student.totalStudents += 1;
            this.studentId = studentId;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.gender = gender;
            this.email = email;
        }

        public void displayStudentInfo() {
            System.out.println("============" + getFormalName());
            System.out.println("Student ID  : " + studentId);
            System.out.println("First name  : " + firstName);
            System.out.println("Middle name : " + middleName);
            System.out.println("Last name   : " + lastName);
            System.out.println("Gender      : " + gender);
            System.out.println("Email       : " + email);
        }

        public static int getTotalStudents() {
            return totalStudents;
        }

        public String getFormalName() {
            return this.lastName + ", " + this.firstName + " " + this.middleName;
        }
    }

    public static void main(String[] args) {
        System.out.println("EFM Colleges Enrollment Registration");
        int lastIdNum = 2511044;
        boolean active = true;
        // Student loop
        while (active) {
            int choice = new InputHandler("""
                    Enroll a new student?
                    [1] yes
                    [0] no

                    Choice : """)
                    .setAllowedChars("01")
                    .nextInt();
            switch (choice) {
                case 1:
                    break;
                case 0:
                    active = false;
                    continue;
            }
            System.out.println("Enter student name");
            String fName = new InputHandler("First  Name : ").min(3).onlyAlphabetic().nextString();
            String mName = new InputHandler("Middle Name : ").min(3).onlyAlphabetic().nextString();
            String lName = new InputHandler("last Name   : ").min(3).onlyAlphabetic().nextString();
            System.out.println();
            String gender = new InputHandler("Gender      : ")
                    .onlyAlphabetic().nextString();
            String email = new InputHandler("Email       : ")
                    .nextString();
            Student student = new Student(++lastIdNum, fName, mName, lName, gender, email);

            System.out.println("Processing...");
            student.displayStudentInfo();
        }
    }
}
