package secondexam.faculty;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class OperationNotAllowedException extends Exception {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
abstract class Student{
    protected String id;
    protected Map<Integer, Map<String, Integer>> gradeByCourseAndTerm;

    public Student(String id) {
        this.id = id;
        this.gradeByCourseAndTerm = new TreeMap<>();

        IntStream.range(1, getYearsOfStudy()*2 + 1).forEach(i -> gradeByCourseAndTerm.put(i, new HashMap<>()));
    }

    public String getId() {
        return id;
    }

    public abstract int getYearsOfStudy();

    public boolean isGraduated(){
        return gradeByCourseAndTerm.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(integer -> integer > 5)
                .count() == getYearsOfStudy() * 6L;
    }
    public int getCoursePassed(){
        return (int)gradeByCourseAndTerm.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(integer -> integer > 5)
                .count();
    }
    public double getAverageGrade (){
        return gradeByCourseAndTerm.values().stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(5.0);

    }
    private int getCoursesPassed(){
        return (int) this.gradeByCourseAndTerm.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .filter(grade -> grade > 5)
                .mapToInt(grade -> grade)
                .count();
    }

    @Override
    public String toString() {
        return "";
    }
    public String customToString(){
        return String.format("Student: %s Courses passed: %d Average grade: %.2f", this.id, this.getCoursesPassed(), this.getAverageGrade());
    }

    public String getDetailedReport(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Student: %s\n", this.id));
        this.gradeByCourseAndTerm.forEach((key, value) -> {
            stringBuilder.append(String.format("Term %d\n", key));
            stringBuilder.append(String.format("Courses: %d\n", value.size()));
            stringBuilder.append(String.format("Average grade for term: %.2f\n", value.values().stream().mapToInt(Integer::intValue).average().orElse(5.0)));


        });
        stringBuilder.append(String.format("Average grade: %.2f\n", getAverageGrade()));
        List<String> collect = this.gradeByCourseAndTerm.values().stream().map(Map::keySet).flatMap(Collection::stream).sorted(String::compareToIgnoreCase).collect(Collectors.toList());

        stringBuilder.append(String.format("Courses attended: %s",String.join(",", collect)));

        return stringBuilder.toString();
    }



    public void addGrade(int term, String courseName, int grade) throws OperationNotAllowedException {
        if(!gradeByCourseAndTerm.containsKey(term)){
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, id));
        }
        if(gradeByCourseAndTerm.get(term).size() > 2) {
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", this.id, term));
        }

        this.gradeByCourseAndTerm.get(term).putIfAbsent(courseName, grade);

    }

}
class ThreeYearStudent extends Student{
    private static final int YEARS_OF_STUDY = 3;

    public ThreeYearStudent(String id) {
        super(id);
    }

    @Override
    public int getYearsOfStudy() {
        return YEARS_OF_STUDY;
    }
}
class FourYearStudent extends Student{
    private static final int YEARS_OF_STUDY = 4;

    public FourYearStudent(String id) {
        super(id);
    }

    @Override
    public int getYearsOfStudy() {
        return YEARS_OF_STUDY;
    }
}


class Faculty {

    private Map<String, Student> studentsById;
    private List<String> logs;
    private Map<String, Map<String, Integer>> gradesByStudentsAndCourse;

    public Faculty() {
        this.studentsById = new TreeMap<>(Comparator.comparing(String::valueOf).reversed());
        this.logs = new ArrayList<>();
        this.gradesByStudentsAndCourse = new HashMap<>();
    }

    void addStudent(String id, int yearsOfStudies) {
        Student student;
        if(yearsOfStudies == 3) student = new ThreeYearStudent(id);
        else if(yearsOfStudies == 4) student = new FourYearStudent(id);
        else throw new RuntimeException("Enter three or four years.");

        this.studentsById.put(id, student);
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Student student = this.studentsById.get(studentId);
        student.addGrade(term, courseName, grade);
        if(student.isGraduated()){
            logs.add(String.format("Student with ID %s graduated with average grade %.2f in %d years.", student.id, student.getAverageGrade(), student.getYearsOfStudy()));
            this.studentsById.remove(studentId);
        }
    }

    String getFacultyLogs() {
        return String.join("\n", logs);
    }

    String getDetailedReportForStudent(String id) {
        return studentsById.get(id).getDetailedReport();
    }

    void printFirstNStudents(int n) {
        studentsById.values().stream().sorted(Comparator.comparing(Student::getCoursePassed).thenComparing(Student::getAverageGrade).reversed()).limit(n).forEach(s-> System.out.println(s.customToString()));
    }

    void printCourses() {
        StringBuilder stringBuilder = new StringBuilder();
        this.gradesByStudentsAndCourse.forEach((k, v) -> {
            double average = v.values().stream().mapToInt(value -> value).average().orElse(5.0);

            System.out.println(String.format("%s %d %.2f",k, v.keySet().size(), average));

        });
    }

}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}

