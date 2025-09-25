import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Teacher {
    private String teacherName;
    private int courseId;
    private ArrayList<Course> courses;

    public Teacher(String name, int id) {
        teacherName = name;
        courseId = id;
        courses = new ArrayList<>();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCourseId() {
        return courseId;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course c) {
        courses.add(c);
        System.out.println("Course added successfully!");
    }

    public void removeCourse(Course c) {

        if (c.getTeacherName().equals(getTeacherName())) {
            courses.remove(c);
            System.out.println("Course removed successfully!");
            try {
                FileWriter writer = new FileWriter("courses.txt");
                for (Course course : courses) {
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
                System.out.println("Courses saved to file.");
            } catch (IOException e) {
                System.out.println("Error occurred while saving courses to file: " + e.getMessage());
            }

        } else {
            System.out.println("This course is not being taught by you!");
        }

    }


    public void assignGrade(Student s, Course c, int grade) {
        if (s.getCourses().contains(c)) {
            s.setMark(c, grade);
            System.out.println("Grade assigned successfully!");
        } else {
            System.out.println("Student is not enrolled in this course!");
        }

    }
}
