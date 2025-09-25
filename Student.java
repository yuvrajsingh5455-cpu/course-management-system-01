import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Student {
    private String studentName;
    private String studentId;
    private ArrayList<Course> courses;
    private HashMap<Course, Integer> marks;
    public Student(String name, String id) {
        studentName = name;
        studentId = id;
        courses = new ArrayList<>();
        marks = new HashMap<>();
    }
    public HashMap<Course, Integer> getMarks() {
        return marks;
    }

    public void setMark(Course course, int mark) {
        marks.put(course, mark);
    }
    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course c) {
        if (c.getCurrentStudents() < c.getMaxStudents()) {
            c.setCurrentStudents(c.getCurrentStudents() + 1);
            courses.add(c);
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Course is full!");
        }
    }

    public void dropCourse(Course c) {
        if (courses.contains(c)) {
            c.setCurrentStudents(c.getCurrentStudents() - 1);
            courses.remove(c);
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("You are not enrolled in this course!");
        }
    }

    public void viewMarks() {
        System.out.println("Marks for courses:");
        for (Map.Entry<Course, Integer> entry : marks.entrySet()) {
            Course course = entry.getKey();
            int mark = entry.getValue();
            System.out.println(course.getCourseName() + ": " + mark);
        }
    }
    public void viewAvailableCourses(ArrayList<Course> courses) {
        for (Course course : courses) {
            if (!getCourses().contains(course)) {
                System.out.println("Course ID: " + course.getCourseId());
                System.out.println("Course Name: " + course.getCourseName());
                System.out.println("Course Teacher: " + course.getTeacherName());

                System.out.println();
            }
        }
    }


}
