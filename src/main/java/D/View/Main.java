package D.View;



import CustomExceptions.OutOfRangeInput;
import D.Entities.Course;
import D.Entities.Employee;
import D.Entities.Prof;
import D.Entities.Student;
import D.Service.CourseStudentService;
import D.Service.Impl.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner input=new Scanner(System.in);
    static CourseServiceImpl courseService=new CourseServiceImpl();
    static CourseStudentServiceImpl courseStudentService=new CourseStudentServiceImpl();
    static EmployeeServiceImpl employeeService=new EmployeeServiceImpl();
    static ProfServiceImpl profService=new ProfServiceImpl();
    static StudentServiceImpl studentService=new StudentServiceImpl();
    public static void main(String[] args) {
        boolean flag=true;
   while(flag){
       System.out.println("login as :"+"\n"+"1-admin"+"\n"+"2-profs"+"\n"+"3-student");
       try {
           Integer operator = input.nextInt();
           if(operator>3 || operator<1){
               throw new OutOfRangeInput("please enter something in range!");
           }
           System.out.println("please enter your username:");
           String username=input.next();
           System.out.println("please enter your password:");
           String password=input.next();
           switch (operator){
               case 1:employeeLogin(username,password);break;
               case 2:profLogin(username,password);break;
               case 3:studentLogin(username,password);break;
           }
       }catch (InputMismatchException e){
           System.out.println("please ente a number!");
       }catch (OutOfRangeInput e){
           System.out.println(e.getMessage());
       }

    }
    }
    public static void employeeLogin(String username,String password){
        Integer id=employeeService.login(new Employee(username,password));
        if(id!=0){
            employeeMenu(id);
        }else System.out.println("there is no employee with this username and password!");
    }
    public static void profLogin(String username,String password){
        Integer id=profService.login(new Prof(username,password));
        if(id!=0){
            profMenu(id);
        }else System.out.println("there is no prof with this username and password!");
    }
    public static void studentLogin(String username,String password){
        Integer id=studentService.login(new Student(username,password));
        if(id!=0){
            studentMenu(id);
        }else System.out.println("there is no student with this username and password!");
    }
    public static void employeeMenu(Integer Id){
        boolean flag=true;
        while(flag){
            System.out.println("1-add , delete and edit employee"+"\n"+"2-add , delete and edit prof"+"\n"+"3-add , delete and edit student"+
                    "\n"+"4-add , delete and edit course"+"\n"+"5-showing payment"+"\n"+"6-exit");
        try {
            Integer operator= input.nextInt();
            if(operator>6 || operator<1){
                throw new OutOfRangeInput("please enter something in range!");
            }
            switch (operator){
                case 1:CUD(1);break;
                case 2:CUD(2);break;
                case 3:CUD(3);break;
                case 4:CUD(4);break;
                case 5:
                    Employee employee=employeeService.findById(Id);
                    System.out.println("firstname:"+employee.getFirstname()+" lastname:"+employee.getLastname()+
                            "  payment:150000 T");break;
                case 6:flag=false;break;

            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }
        }

    }
    public static void profMenu(Integer Id){
        boolean flag=true;
        while(flag){
            System.out.println("1-showin details!"+"\n"+"2-submiting a score"+"\n"+"3-showing payment"+"\n"+"4-exit");
              try {
                  Integer operator= input.nextInt();
                  if(operator>4 || operator<1){
                      throw new OutOfRangeInput("please enter something in range!");
                  }
                  switch (operator){
                      case 1:Prof prof=profService.findById(Id);
                          System.out.println("id:" + prof.getId() + " firstname:" + prof.getFirstname() + " lastname:" + prof.getLastname()+" type:"+
                                  prof.getType());break;
                      case 2:submittingScore(Id);break;
                      case 3:showingPayment(Id);break;
                      case 4:flag=false;break;
                  }
              }catch (InputMismatchException e){
                  System.out.println("please enter a number!");
                  input.nextLine();
              }catch (OutOfRangeInput e){
                  System.out.println(e.getMessage());
              }
        }
    }
    public static void studentMenu(Integer Id){
boolean flag=true;
while(flag){
    System.out.println("1-showing details"+"\n"+"2-showing all courses"+"\n"+"3-unit selecting"+"\n"+"4-courses till now"+"\n"+"5-exit");
    try {
        Integer operator= input.nextInt();
        if(operator>5 || operator<1){
            throw new OutOfRangeInput("please enter something in range!");
        }

        Student student=studentService.findById(Id);
        switch (operator){
            case 1:
                System.out.println("id:" + student.getId() + " firstname:" + student.getFirstname() + " lastname:" + student.getLastname());break;
            case 2:List<Course> courses=courseService.findAll();
                for (Course course:courses
                     ) {
                    System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                            course.getYear()+" term:"+course.getTerm()+
                            " unit:"+course.getUnit());
                }
                break;
            case 3:unitSelecting(Id);break;
            case 4:List<Integer> courseIds=courseStudentService.courseByStudentId(Id);
                for (Integer courseid:courseIds
                     ) {
                    Course course=courseService.findById(courseid);
                    System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                            course.getYear()+" term:"+course.getTerm()+
                            " unit:"+course.getUnit()+" score:"+courseStudentService.score(student,course));
                }
                break;
            case 5:flag=false;break;
        }
    }catch (InputMismatchException e){
        System.out.println("please enter a number!");
        input.nextLine();
    }catch (OutOfRangeInput e){
        System.out.println(e.getMessage());
    }
}
    }
    public static void CUD(Integer operator1){
        boolean flag=true;
        while(flag){
            System.out.println("1-adding"+"\n"+"2-editing"+"\n"+"3-deleting"+"\n"+"4-exit");
            try {
                Integer operator= input.nextInt();
                if(operator>4 || operator<1){
                    throw new OutOfRangeInput("please enter a number!");
                }
                switch (operator){
                    case 1:
                        switch (operator1){
                            case 1:  employeeCreating();break;
                            case 2:profCreating();break;
                            case 3:studentCreating();break;
                            case 4:courseCreating();break;
                        }
                        break;
                    case 2:  switch (operator1){
                        case 1:  employeeEditing();break;
                        case 2:profEditing();break;
                        case 3:studentEditing();break;
                        case 4:courseEditing();break;
                    }
                    break;
                    case 3:  switch (operator1){
                        case 1:  employeeDeleting();break;
                        case 2:profDeleting();break;
                        case 3:studentDeleting();break;
                        case 4:courseDeleting();break;
                    }
                    break;
                    case 4:flag=false;break;
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRangeInput e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void employeeCreating(){
        System.out.println("please enter firstname:");
        String firstname=input.next();
        System.out.println("please enter lastname:");
        String lastname=input.next();
        System.out.println("please enter username:");
        String username=input.next();
        System.out.println("please enter password:");
        String password=input.next();
     int id=  employeeService.create(new Employee(firstname,lastname,username,password));
if(id>0){
    System.out.println("done!");
}
    }
    public static void employeeDeleting() {
        List<Employee> employeeList = employeeService.findAll();
        for (Employee employee : employeeList
        ) {
            System.out.println("id:" + employee.getId() + " firstname:" + employee.getFirstname() + " lastname:" + employee.getLastname());
        }
        System.out.println("please enter id of the employee you want to delete:");
        try {
            Integer id=input.nextInt();
            Employee employee=employeeService.findById(id);
            if(employee!=null){
                employeeService.Delete(id);

            }else {
                System.out.println("there is no employee with this id!");
            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }

    }
    public static void employeeEditing(){
        boolean flag=true;
        while(flag) {
            System.out.println("1-firstname" + "\n" + "2-lastname" + "\n" + "3-username" + "\n" + "4-password" + "\n" + "5-exit");
        try {
            Integer operator= input.nextInt();
            if(operator>5 || operator<1){
                throw new OutOfRangeInput("please enter something in range!");
            }
            if(operator==5){
                return;
            }
            List<Employee> employeeList = employeeService.findAll();
            for (Employee employee : employeeList
            ) {
                System.out.println("id:" + employee.getId() + " firstname:" + employee.getFirstname() + " lastname:" + employee.getLastname());
            }
            System.out.println("please enter id of the employee you want to edit:");

                Integer id = input.nextInt();
                Employee employee = employeeService.findById(id);
                if (employee != null) {
                    System.out.println("please enter new value!");
                    String newValue = input.next();
                    switch (operator) {
                        case 1:employee.setFirstname(newValue);employeeService.Update(employee);break;
                        case 2:employee.setLastname(newValue);employeeService.Update(employee);break;
                        case 3:employee.setUsername(newValue);employeeService.Update(employee);break;
                        case 4:employee.setPassword(newValue);employeeService.Update(employee);break;
                        case 5:flag=false;break;
                    }
                }else {
                    System.out.println("there is no employee whit this id!");
                }


        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }
        }
    }
    public static void profCreating(){
        System.out.println("please enter firstname:");
        String firstname=input.next();
        System.out.println("please enter lastname:");
        String lastname=input.next();
        System.out.println("please enter username:");
        String username=input.next();
        System.out.println("please enter password:");
        String password=input.next();
        System.out.println("please enter 1 for haghol tadris and 2 for heyat elmi");
        try {
            Integer operator= input.nextInt();
            if(operator!=2 && operator!=1){
                throw new OutOfRangeInput("not valid job type!");
            }
            int id=  profService.create(new Prof(firstname,lastname,username,password,operator==1? "hagholtadris":"heyatelmi"));
            if(id>0){
                System.out.println("done!");
            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }


    }
    public static void profDeleting(){
        List<Prof> profs = profService.findAll();
        for (Prof prof : profs
        ) {
            System.out.println("id:" + prof.getId() + " firstname:" + prof.getFirstname() + " lastname:" + prof.getLastname()+" type:"+
                    prof.getType());
        }
        System.out.println("please enter id of the employee you want to delete:");
        try {
            Integer id=input.nextInt();
            Prof prof=profService.findById(id);
            if(prof!=null){
                profService.Delete(id);

            }else {
                System.out.println("there is no employee with this id!");
            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
    }
    public static void profEditing(){
        boolean flag=true;
        while(flag) {
            System.out.println("1-firstname" + "\n" + "2-lastname" + "\n" + "3-username" + "\n" + "4-password" + "\n" + "5-type"+"\n"+"6-exit");
            try {
                Integer operator= input.nextInt();
                if(operator>6 || operator<1){
                    throw new OutOfRangeInput("please enter something in range!");
                }
                if(operator==6){
                    return;
                }
                List<Prof> profList = profService.findAll();
                for (Prof prof : profList
                ) {
                    System.out.println("id:" + prof.getId() + " firstname:" + prof.getFirstname() + " lastname:" + prof.getLastname());
                }
                System.out.println("please enter id of the prof you want to edit:");

                Integer id = input.nextInt();
                Prof prof = profService.findById(id);
                if (prof != null) {
                    System.out.println("please enter new value:");
                    String newValue = input.next();
                    switch (operator) {
                        case 1:prof.setFirstname(newValue);profService.Update(prof);break;
                        case 2:prof.setLastname(newValue);profService.Update(prof);break;
                        case 3:prof.setUsername(newValue);profService.Update(prof);break;
                        case 4:prof.setPassword(newValue);profService.Update(prof);break;
                        case 5:
                        case 6:flag=false;break;
                    }
                }else {
                    System.out.println("there is no prof whit this id!");
                }


            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRangeInput e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void studentCreating(){
        System.out.println("please enter firstname:");
        String firstname=input.next();
        System.out.println("please enter lastname:");
        String lastname=input.next();
        System.out.println("please enter username:");
        String username=input.next();
        System.out.println("please enter password:");
        String password=input.next();
        int id=  studentService.create(new Student(firstname,lastname,username,password));
        if(id>0){
            System.out.println("done!");
        }
    }
    public static void studentEditing(){
        boolean flag=true;
        while(flag) {
            System.out.println("1-firstname" + "\n" + "2-lastname" + "\n" + "3-username" + "\n" + "4-password" + "\n" + "5-exit");
            try {
                Integer operator= input.nextInt();
                if(operator>5 || operator<1){
                    throw new OutOfRangeInput("please enter something in range!");
                }
                if(operator==5){
                    return;
                }
                List<Student> students = studentService.findAll();
                for (Student student : students
                ) {
                    System.out.println("id:" + student.getId() + " firstname:" + student.getFirstname() + " lastname:" + student.getLastname());
                }
                System.out.println("please enter id of the student you want to delete:");

                Integer id = input.nextInt();
                Student student = studentService.findById(id);
                if (student != null) {
                    System.out.println("please enter new value:");
                    String newValue = input.next();
                    switch (operator) {
                        case 1:student.setFirstname(newValue);studentService.Update(student);break;
                        case 2:student.setLastname(newValue);studentService.Update(student);break;
                        case 3:student.setUsername(newValue);studentService.Update(student);break;
                        case 4:student.setPassword(newValue);studentService.Update(student);break;
                        case 5:flag=false;break;
                    }
                }else {
                    System.out.println("there is no student whit this id!");
                }


            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRangeInput e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static  void studentDeleting(){
        List<Student> studentList = studentService.findAll();
        for (Student student : studentList
        ) {
            System.out.println("id:" + student.getId() + " firstname:" + student.getFirstname() + " lastname:" + student.getLastname());
        }
        System.out.println("please enter id of the student you want to delete:");
        try {
            Integer id=input.nextInt();
            Student student=studentService.findById(id);
            if(student!=null){
                studentService.Delete(id);

            }else {
                System.out.println("there is no student with this id!");
            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }

    }
    public static void courseCreating(){

        try {
            System.out.println("please enter coursename:");
            String courseName=input.next();
            System.out.println("please enter year:");
            Integer yearOfCourse= input.nextInt();
            if(yearOfCourse>1400 || yearOfCourse<1390){
                throw new OutOfRangeInput("there is no year with this condition!");
            }
            System.out.println("please enter term:");
            Integer term= input.nextInt();
            if(term>2 || term<1){
                throw new OutOfRangeInput("wrong type of term!");
            }
            System.out.println("please enter number of unit:");
            Integer unit= input.nextInt();
            if(unit>4 || unit<1){
                throw new OutOfRangeInput("wrong type of unit!");
            }
            List<Prof> profs=profService.findAll();
            for (Prof prof : profs
            ) {
                System.out.println("id:" + prof.getId() + " firstname:" + prof.getFirstname() + " lastname:" + prof.getLastname());
            }
            System.out.println("please enter id of the prof you want to select:");

            Integer profId = input.nextInt();
            Prof prof = profService.findById(profId);
            if (prof != null) {
                int id=courseService.create(new Course(courseName,profId,yearOfCourse,term,unit));
                if (id > 0) {
                    System.out.println("done!");
                    return;
                }
            }
            System.out.println("there is no prof with this id!please try again!");
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }

    }
    public static void courseEditing(){
        boolean flag=true;
        while(flag) {
            System.out.println("1-course name" + "\n" + "2-prof" + "\n" + "3-term" + "\n" + "4-year" + "\n" + "5-unit"+"\n"+"6-exit");
            try {
                Integer operator= input.nextInt();
                if(operator>6 || operator<1){
                    throw new OutOfRangeInput("please enter something in range!");
                }
                if(operator==6){
                    return;
                }
                List<Course> courses = courseService.findAll();
                for (Course course : courses
                ) {
                    System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                            course.getYear()+" term:"+course.getTerm()+
                            " unit:"+course.getUnit());
                }
                System.out.println("please enter id of the course you want to edit:");
                Integer id = input.nextInt();
                Course course = courseService.findById(id);
                if (course != null) {

                    switch (operator) {
                        case 1:
                            System.out.println("please enter new value:");
                            String newValue = input.next();
                            course.setName(newValue);courseService.Update(course);break;
                        case 2:
                            List<Prof> profList = profService.findAll();
                            for (Prof prof : profList
                            ) {
                                System.out.println("id:" + prof.getId() + " firstname:" + prof.getFirstname() + " lastname:" + prof.getLastname());
                            }
                            System.out.println("please enter id of the prof you want to add:");
                            Integer profid = input.nextInt();
                            Prof prof = profService.findById(profid);
                            if (prof != null) {
                            course.setProfid(profid);courseService.Update(course);break;}else{
                                System.out.println("there is no prof with this id!");break;
                            }

                        case 3:
                            System.out.println("please enter new value:");
                            Integer newTerm =input.nextInt();
                            if(newTerm >2 || newTerm <1){
                                throw new OutOfRangeInput("wrong type term!");
                            }
                            course.setTerm(newTerm);courseService.Update(course);break;
                        case 4:
                            System.out.println("please enter new value:");
                            Integer newYear=input.nextInt();
                            if(newYear >1400 || newYear <1390){
                                throw new OutOfRangeInput("wrong type term!");
                            }
                            course.setYear(newYear);courseService.Update(course);break;
                        case 5:System.out.println("please enter new value:");
                            Integer newUnit=input.nextInt();
                            if(newUnit >4 || newUnit <1){
                                throw new OutOfRangeInput("wrong type term!");
                            }
                            course.setUnit(newUnit);courseService.Update(course);break;
                        case 6:flag=false;break;
                    }
                }else {
                    System.out.println("there is no course whit this id!");
                }


            }catch (InputMismatchException e){
                System.out.println("please enter a number!");
                input.nextLine();
            }catch (OutOfRangeInput e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void courseDeleting(){
        List<Course> courses = courseService.findAll();
        for (Course course : courses
        ) {
            System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                    course.getYear()+" term:"+course.getTerm()+
                    " unit:"+course.getUnit());        }
        System.out.println("please enter id of the course you want to delete:");
        try {
            Integer id=input.nextInt();
            Course course=courseService.findById(id);
            if(course!=null){
                courseService.Delete(id);

            }else {
                System.out.println("there is no course with this id!");
            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }

    }
    public static void submittingScore(Integer id){
        List<Course> courses=courseService.findAll();
        for (Course course: courses
             ) {
         if(Objects.equals(course.getProfid(), id)){
             System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                     course.getYear()+" term:"+course.getTerm()+
                     " unit:"+course.getUnit());
         }
        }
        System.out.println("please enter id of the course that you want to score for:");
        try {
            Integer courseId=input.nextInt();
            Course course= courseService.findById(courseId);
            if(course==null){
                throw new OutOfRangeInput("there is no course with this id! try again!");
            }
            List<Student> students=studentService.findAll();
            for (Student student: students
            ) {
                System.out.println("id:" + student.getId() + " firstname:" + student.getFirstname() + " lastname:" + student.getLastname());
            }
            System.out.println("please enter the id of student that you want to score for:");

                Integer studentId=input.nextInt();
                Student student= studentService.findById(studentId);
                if(student==null){
                    throw new OutOfRangeInput("there is no student with this id! try again!");
                }
         List<Integer> coursesId= courseStudentService.courseByStudentId(studentId);
            for (Integer courseId1:coursesId
                 ) {
                if(Objects.equals(courseId1, courseId)){
                    System.out.println("please enter score:");
                    Integer score=input.nextInt();
                    if(score>20 || score<0) {
                        throw new OutOfRangeInput("wrong score type!");
                    }
                    courseStudentService.scoring(student,course,score);
                    return;

                }
            }
            System.out.println("this student did not pick  this course with you!");
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }
    }
    public static void showingPayment(Integer id){
        try {
            Prof prof = profService.findById(id);
            System.out.println("please enter year:");
            Integer yearOfCourse = input.nextInt();
            if (yearOfCourse > 1400 || yearOfCourse < 1390) {
                throw new OutOfRangeInput("there is no year with this condition!");
            }
            System.out.println("please enter term:");
            Integer term = input.nextInt();
            if (term > 2 || term < 1) {
                throw new OutOfRangeInput("wrong type of term!");
            }
            List<Course> courses = courseService.findAll();
            int counter = 0;
            for (Course course : courses
            ) {
                if (Objects.equals(course.getProfid(), id) && Objects.equals(course.getYear(), yearOfCourse) && Objects.equals(course.getTerm(), term)) {
                    counter += course.getUnit();
                }
            }
            if (Objects.equals(prof.getType(), "heyatelmi")) {
                System.out.println("your salary is :" + 5000 + (counter * 1000));
            } else {
                System.out.println("your salary is :" + (counter * 1000));
            }
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }
    }
    public static void unitSelecting(Integer id){
        try {
            System.out.println("please enter year:");
            Integer yearOfCourse = input.nextInt();
            if (yearOfCourse > 1400 || yearOfCourse < 1390) {
                throw new OutOfRangeInput("there is no year with this condition!");
            }
            System.out.println("please enter term:");
            Integer term = input.nextInt();
            if (term > 2 || term < 1) {
                throw new OutOfRangeInput("wrong type of term!");
            }
            boolean flag = true;
            while (flag) {
                System.out.println("1-adding a new course" + "\n" + "2-deleting a course" + "\n" + "3-exit");
                  Integer operator= input.nextInt();
                  if(operator>3 || operator<1){
                      throw new OutOfRangeInput("please enter something in range!");
                  }
                  switch (operator){
                      case 1:addingUnit(yearOfCourse,term,id);break;
                      case 2:deletingUnit(yearOfCourse,term,id);break;
                      case 3:flag=false;break;
                  }

            }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }
    }
    public static void addingUnit(Integer year,Integer term,Integer id){
        List<Course> courses=courseService.findAll();
        for (Course course:courses
             ) {
            if(Objects.equals(course.getYear(), year) && Objects.equals(course.getTerm(), term)){
                System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                        course.getYear()+" term:"+course.getTerm()+
                        " unit:"+course.getUnit());
            }
        }
        Integer exYear= 0;
        Integer exTerm=  0;
        if(term==1){
             exYear= year-1;
          exTerm=  term+1;
        }else {
             exYear= year;
             exTerm=  term-1;
        }
        Integer scoreCounter=0;
        Integer unitCounter=0;
        Student student=studentService.findById(id);
        List<Integer> coursesId=courseStudentService.courseByStudentId(student.getId());
        for (Integer a:coursesId
        ) {
            Course course=courseService.findById(a);
            if(Objects.equals(course.getYear(), exYear) && Objects.equals(course.getTerm(), exTerm)){
                scoreCounter+=courseStudentService.score(student,course)*course.getUnit();
                unitCounter+=course.getUnit();
            }
        }
        Integer lastTermAvg=0;
        if(unitCounter>0){ lastTermAvg=scoreCounter/unitCounter;}
        unitCounter=0;

        System.out.println("please enter the id of the course:");
        try {
            Integer courseId=input.nextInt();
           Course course= courseService.findById(courseId);
           if(course==null || !Objects.equals(course.getYear(), year) || !Objects.equals(course.getTerm(), term)){
               throw new OutOfRangeInput("there is no course with this id in this term! ");
           }
           List<Integer> coursesId1=courseStudentService.courseByStudentId(student.getId());
            for (Integer a1:coursesId1
            ) {
                 Course course1=courseService.findById(a1);
                if(Objects.equals(course1.getYear(), year) && Objects.equals(course1.getTerm(), term) && Objects.equals(course1.getId(), course.getId())){
                    System.out.println("you have already picked this unit !");
                    return;

                }else if(Objects.equals(course1.getYear(), year) && Objects.equals(course1.getTerm(), term)){
                    unitCounter+=course.getUnit();
                }
            }

           if(lastTermAvg>18){
               if(unitCounter+course.getUnit()>24){
                   System.out.println("you can not pick more than 24 unit!");
               }else {courseStudentService.unitSelecting(student,course);return;}

           }else {
               if(unitCounter+course.getUnit()>20){
                   System.out.println("you can not pick more than 20 unit!");
               }else {
                   courseStudentService.unitSelecting(student,course);return;
               }
           }
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }

    }
    public static void deletingUnit(Integer year,Integer term,Integer id){
        Student student=studentService.findById(id);
        List<Integer> courses=courseStudentService.courseByStudentId(id);
        Course course=new Course();
        for (Integer a:courses
             ) {
            course=courseService.findById(a);
            if(course!=null && Objects.equals(course.getYear(), year) && Objects.equals(course.getTerm(), term)){
                System.out.println("id:" + course.getId() + " course name:" + course.getName() + " prof id:" + course.getProfid()+" :year"+
                        course.getYear()+" term:"+course.getTerm()+
                        " unit:"+course.getUnit());
            }
        }
        System.out.println("please enter id of the course you want to delete:");
        try {
            Integer courseId=input.nextInt();
            Course course1=courseService.findById(courseId);
            if(course1==null || !Objects.equals(course1.getYear(), year) || !Objects.equals(course.getTerm(), term)){
                throw new OutOfRangeInput("there is no course with this id!");
            }
         if(courseStudentService.score(student,course1)>0){
             System.out.println("you can not delete this unit! it has already been scored!");
             return;
         }
         courseStudentService.Delete(student,course1);
        }catch (InputMismatchException e){
            System.out.println("please enter a number!");
            input.nextLine();
        }catch (OutOfRangeInput e){
            System.out.println(e.getMessage());
        }

    }
}
