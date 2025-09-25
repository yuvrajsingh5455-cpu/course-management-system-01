import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class Main {
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String BOLD = "\u001B[1m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE


    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Filing.loadCoursesFromFile();
        Filing.loadTeachersFromFile();
        Filing.loadStudentsFromFile();

        File file = new File("students.txt");
        if (file.length() == 0) {
            Student s1 = new Student("Arham", "22k-4080");
            students.add(s1);
        }

        File file1 = new File("teachers.txt");
        if (file1.length() == 0) {
            Teacher t1 = new Teacher("Abdul Aziz", 8700);
            teachers.add(t1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(YELLOW_BOLD_BRIGHT + "\n\t\t ==== \uD835\uDD4E\uD835\uDD56\uD835\uDD5D\uD835\uDD54\uD835\uDD60\uD835\uDD5E\uD835\uDD56 \uD835\uDD65\uD835\uDD60 ℂ\uD835\uDD60\uD835\uDD66\uD835\uDD63\uD835\uDD64\uD835\uDD56 \uD835\uDD44\uD835\uDD52\uD835\uDD5F\uD835\uDD52\uD835\uDD58\uD835\uDD56\uD835\uDD5E\uD835\uDD56\uD835\uDD5F\uD835\uDD65 \uD835\uDD4A\uD835\uDD6A\uD835\uDD64\uD835\uDD65\uD835\uDD56\uD835\uDD5E ==== \n" + RESET);
        while (true) {
            System.out.print("\n\t\t1. Login as Student");
            System.out.println("\t\t2. Register as Student");
            System.out.print("\t\t3. Login as Teacher");
            System.out.println("\t\t4. Register as Teacher");
            System.out.print("\t\t5. Save Details");
            System.out.println("\t\t\t6. System Theme");
            System.out.println("\t\t7. Exit");

            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    loginAsStudent(br);
                    break;
                case 2:
                    registerStudent(br);
                    break;
                case 3:
                    loginAsTeacher(br);
                    break;
                case 4:
                    registerTeacher(br);
                    break;
                case 5:
                    Filing.saveCoursesToFile();
                    Filing.saveTeachersToFile();
                    Filing.saveStudentsToFile();
                    break;
                case 6:
                    systemTheme(br);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println(BOLD + RED + "Invalid choice!" + RESET);
            }
        }
    }



    private static void loginAsStudent(BufferedReader br) throws IOException {

        System.out.print("\nEnter your ID: ");
        String id = br.readLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                found = true;
                while (true) {
                    System.out.println("\n1. View Available Courses");
                    System.out.println("2. Add Course");
                    System.out.println("3. Drop Course");
                    System.out.println("4. View Enrolled Courses");
                    System.out.println("5. View Marks");
                    System.out.println("6. Logout");

                    int choice = Integer.parseInt(br.readLine());

                    switch (choice) {
                        case 1:
                            viewAvailableCourses(student);
                            break;
                        case 2:
                            addCourse(br, student);
                            break;
                        case 3:
                            removeCourse(br, student);
                            break;
                        case 4:
                            viewCourses(student);
                            break;
                        case 5:
                            student.viewMarks();
                            break;
                        case 6:
                            System.out.println("Logged out successfully!");
                            return;
                        default:
                            System.out.println(BOLD + RED + "Invalid choice!" + RESET);
                    }
                }
            }
        }
        if (!found) {
            System.out.println(RED + "Invalid ID!" + RESET);
        }


    }

    private static void addCourse(BufferedReader br, Student student) throws IOException {
        

        System.out.print("Enter course code: ");
        String courseCode = br.readLine();

        for (Course course : student.getCourses()) {
            if (course.getCourseId() == Integer.parseInt(courseCode)) {
                System.out.println("You are already enrolled in this course!");
                return;
            }
        }

        for (Course course : courses) {
            if (course.getCourseId() == Integer.parseInt(courseCode)) {
                if (course.getCurrentStudents() < course.getMaxStudents()) {
                    course.setCurrentStudents(course.getCurrentStudents() + 1);
                    student.addCourse(course);
                } else {
                    System.out.println("Course is full!");
                }
                return;
            }
        }
        System.out.println("This course is not available!");
    }

    private static void removeCourse(BufferedReader br, Student student) throws IOException {

        System.out.print("Enter course code: ");
        int courseCode = Integer.parseInt(br.readLine());

        if (!student.getMarks().isEmpty()) {
                System.out.println("You can only drop courses before marks are assigned!");
                return;
            }




            for (Course course : student.getCourses()) {
                if (course.getCourseId() == courseCode) {
                    student.dropCourse(course);
                    student.getMarks().remove(course);
                    return;
                }
            }

        System.out.println("You are not enrolled in this course!");
    }


    private static void viewCourses(Student student) {
        if (student.getCourses().isEmpty()) {
            System.out.println("You are not enrolled in any courses!");
            return;
        }

        System.out.println("Courses:");
        for (Course course : student.getCourses()) {
            System.out.println(course.getCourseId() + " (" + course.getCourseName() + ")");
        }
    }

    private static void viewAvailableCourses(Student student) {
        System.out.println("Available Courses: ");
        student.viewAvailableCourses(courses);
    }

    private static void loginAsTeacher(BufferedReader br) throws IOException {
        System.out.print("\nEnter your ID: ");
        int id = Integer.parseInt(br.readLine());
        boolean found = false;
        for (Teacher teacher : teachers) {
            if (teacher.getCourseId() == id) {
                found = true;
                while (true) {
                    System.out.println("\n1. Add a New Course");
                    System.out.println("2. Remove Course");
                    System.out.println("3. Assign Grade");
                    System.out.println("4. Logout");

                    int choice = Integer.parseInt(br.readLine());

                    switch (choice) {
                        case 1:
                            addCourse(br, teacher);
                            break;
                        case 2:
                            removeCourse(br, teacher);
                            break;
                        case 3:
                            assignGrade(br, teacher);
                            break;
                        case 4:
                            System.out.println("Logged out successfully!");
                            return;
                        default:
                            System.out.println("Invalid choice!");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Invalid ID!");
        }

    }

    private static void addCourse(BufferedReader br, Teacher teacher) throws IOException {
        System.out.print("Enter course name: ");
        String name = br.readLine();
        System.out.print("Enter course ID: ");
        int id = Integer.parseInt(br.readLine());
        System.out.print("Enter credit hours: ");
        int hours = Integer.parseInt(br.readLine());
        System.out.print("Enter maximum number of students: ");
        int max = Integer.parseInt(br.readLine());
        System.out.println("Is it a special course? (Y/N): ");
        String special = br.readLine();

        boolean isSpecialCourse = special.equalsIgnoreCase("Y");

        if (isSpecialCourse) {

            SpecialCourse specialCourse = new SpecialCourse(name, id, hours, max, teacher.getTeacherName());
            courses.add(specialCourse);
            //teacher.addCourse(specialCourse);
        }else {
            Course c = new Course(name, id, hours, max, teacher.getTeacherName());
            courses.add(c);
            //teacher.addCourse(c);
        }


    }

    private static void removeCourse(BufferedReader br, Teacher teacher) throws IOException {
        System.out.print("Enter course ID: ");
        int id = Integer.parseInt(br.readLine());

        for (Course c : courses) {
            if (c.getCourseId() == id) {
                teacher.removeCourse(c);
                return;
            }
        }

        System.out.println("Invalid course ID!");
    }

    private static void assignGrade(BufferedReader br, Teacher teacher) throws IOException {
        System.out.print("Enter student ID: ");
        String studentId = br.readLine();

        System.out.print("Enter course ID: ");
        int courseId = Integer.parseInt(br.readLine());

        System.out.print("Enter grade (out of 100): ");
        int grade = Integer.parseInt(br.readLine());

        Student s = null;
        Course c = null;

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                s = student;
                break;
            }
        }

        if (s == null) {
            System.out.println("Invalid student ID!");
            return;
        }

        for (Course course : s.getCourses()) {
            if (course.getCourseId() == courseId) {
                c = course;
                break;
            }
        }

        if (c == null) {
            System.out.println("This course is not being taken by the specified student!");
            return;
        }

        teacher.assignGrade(s, c, grade);
    }

    private static void registerStudent(BufferedReader br) throws IOException {
        System.out.println("Enter Name: ");
        String name = br.readLine();
        System.out.println("Enter ID: ");
        String id = br.readLine();

        //error
      //  List<Student> newList = new ArrayList<>(students);
//        for (Student student: newList){
//            if(student.getStudentId().equals(id)){
//                System.out.println("User ID already Registered!");
//            }else if(!(student.getStudentId().equals(id))) {
//                Student newStudent = new Student(name,id);
//                students.add(newStudent);
//                System.out.println(GREEN + "Registration Successful!" + RESET);
//            }
//        }

        Student newStudent = new Student(name,id);
        students.add(newStudent);
        System.out.println(GREEN + "Registration Successful!" + RESET);

    }
    private static void registerTeacher(BufferedReader br) throws IOException{
        System.out.println("Enter Name: ");
        String name = br.readLine();
        System.out.println("Enter number ID: ");
        int id = Integer.parseInt(br.readLine());

                Teacher newTeacher = new Teacher(name,id);
                teachers.add(newTeacher);
                System.out.println(GREEN + "Registration Successful!" + RESET);

    }

    private static void systemTheme(BufferedReader br) throws IOException{
        System.out.println("Select System Theme: \n");
        System.out.print("\n\t\t1. Dark");
        System.out.print("\t\t2. Blue");
        System.out.print("\t\t3. Reset to Default\n");

        int num = Integer.parseInt(br.readLine());

        if (num == 1){
            System.out.println(BLACK_BACKGROUND);
        } else if (num == 2) {
            System.out.println(BLUE_BACKGROUND);
        } else if (num == 3) {
            System.out.println(RESET);
        }else {
            System.out.println(RED_BRIGHT + "Invalid Choice" + RESET);
        }
    }
}
