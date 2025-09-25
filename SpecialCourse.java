class SpecialCourse extends Course {

    public SpecialCourse(String name, int id, int hours, int max, String teacher) {
        super(name, id, hours, max, teacher);
    }

    @Override
    public String getCourseName() {
        // Override the getCourseName() method to add a special prefix to the course name
        return "Special Course: " + super.getCourseName();
    }

}
