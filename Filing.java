import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Filing {
    private static final String SPECIAL_COURSES_FILE = "special_courses.txt";
    protected static void saveCoursesToFile() {
        try {
            FileWriter writer = new FileWriter("courses.txt");
            for (Course course : Main.courses) {

                writer.write(
                        course.getCourseName() + "," +
                                course.getCourseId() + "," +
                                course.getCreditHours() + "," +
                                course.getMaxStudents() + "," +
                                course.getCurrentStudents() + "," +
                                course.getTeacherName() + "\n"
                );
            }
            writer.close();
            System.out.println(Main.GREEN + "Courses saved to file." + Main.RESET);
        } catch (IOException e) {
            System.out.println(Main.RED + "Error occurred while saving courses to file: " + Main.RESET + e.getMessage());
        }
    }

    protected static void saveTeachersToFile() {
        try {
            FileWriter writer = new FileWriter("teachers.txt");
            for (Teacher teacher : Main.teachers) {
                writer.write(teacher.getTeacherName() + "," + teacher.getCourseId() + "\n");
            }
            writer.close();
            System.out.println(Main.GREEN + "Teachers saved to file." + Main.GREEN);
        } catch (IOException e) {
            System.out.println(Main.RED + "Error occurred while saving teachers to file: " + Main.RESET + e.getMessage());
        }
    }

    protected static void saveStudentsToFile() {
        try {
            FileWriter writer = new FileWriter("students.txt");

            for (Student student : Main.students) {
                writer.write(student.getStudentName() + "," + student.getStudentId() + ",");
                if (!student.getMarks().isEmpty()) {
                    for (Course course : Main.courses) {
                        Integer mark = student.getMarks().get(course);
                        if (mark != null) {
                            writer.write(mark + ",");
                        } else {
                            writer.write(",");
                        }
                    }
                } else {
                    writer.write(",");
                }
                ArrayList<Course> enrolledCourses = student.getCourses();
                for (int i = 0; i < enrolledCourses.size(); i++) {
                    Course course = enrolledCourses.get(i);
                    writer.write(course.getCourseId() + "");
                    if (i != enrolledCourses.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }

            writer.close();
            System.out.println(Main.GREEN + "Students and enrolled courses saved to file." + Main.RESET);
        } catch (IOException e) {
            System.out.println(Main.RED + "Error occurred while saving students and enrolled courses to file: " + Main.RESET + e.getMessage());
        }
    }



    protected static void loadCoursesFromFile() {
        try {
            File file = new File("courses.txt");
            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String courseName = data[0];
                int courseId = Integer.parseInt(data[1]);
                int creditHours = Integer.parseInt(data[2]);
                int maxStudents = Integer.parseInt(data[3]);
                int currentStudents = Integer.parseInt(data[4]);
                String teacherName = data[5];

                Course course = new Course(courseName, courseId, creditHours, maxStudents, teacherName);
                course.setCurrentStudents(currentStudents);
                Main.courses.add(course);
            }
            scanner.close();
            System.out.println("Courses loaded from file.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading courses from file: " + e.getMessage());
        }
    }



    protected static void loadTeachersFromFile() {
        try {
            File file = new File("teachers.txt");
            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String teacherName = data[0];
                int courseId = Integer.parseInt(data[1]);

                Teacher teacher = new Teacher(teacherName, courseId);
                Main.teachers.add(teacher);
            }
            scanner.close();
            System.out.println("Teachers loaded from file.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading teachers from file: " + e.getMessage());
        }
    }

    protected static void loadStudentsFromFile() {
        try {
            File file = new File("students.txt");
            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String studentName = data[0];
                String studentId = data[1];
                Integer studentMarks = null;

                if (data.length > 2 && !data[2].isEmpty()) {
                    studentMarks = Integer.parseInt(data[2]);
                }

                Student student = new Student(studentName, studentId);

                if (data.length > 3) {
                    for (int i = 3; i < data.length; i++) {
                        int courseId = Integer.parseInt(data[i]);
                        for (Course course : Main.courses) {
                            if (course.getCourseId() == courseId) {
                                student.addCourse(course);
                                if (studentMarks != null) {
                                    student.setMark(course, studentMarks);
                                }
                                break;
                            }
                        }
                    }
                }

                Main.students.add(student);
            }
            scanner.close();
            System.out.println("Students loaded from file.");
        } catch (IOException e) {
            System.out.println("Error occurred while loading students from file: " + e.getMessage());
        }
    }

}
