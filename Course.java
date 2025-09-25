import java.io.*;
import java.util.*;

class Course {
    private String courseName;
    private int courseId;
    private int creditHours;
    private int maxStudents;
    private int currentStudents;
    private String teacherName;
    public Course(String name, int id, int hours, int max, String teacher) {
        courseName = name;
        courseId = id;
        creditHours = hours;
        maxStudents = max;
        currentStudents = 0;
        teacherName = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }


}

