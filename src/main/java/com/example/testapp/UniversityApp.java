package com.example.testapp;

import com.example.testapp.entity.Degree;
import com.example.testapp.entity.Department;
import com.example.testapp.entity.Lector;
import com.example.testapp.repo.DegreeRepo;
import com.example.testapp.repo.DepartmentRepo;
import com.example.testapp.repo.LectorRepo;
import com.example.testapp.service.DepartmentService;
import com.example.testapp.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class UniversityApp {

    private static DepartmentRepo departmentRepository;
    private static LectorRepo lectorRepository;

    private static DegreeRepo degreeRepository;

    private static DepartmentService departmentService;

    private static LectorService lectorService;

    @Autowired
    public UniversityApp(DepartmentRepo departmentRepository, LectorRepo lectorRepository, DegreeRepo degreeRepository, DepartmentService departmentService, LectorService lectorService) {
        UniversityApp.departmentRepository = departmentRepository;
        UniversityApp.lectorRepository = lectorRepository;
        UniversityApp.degreeRepository = degreeRepository;
        UniversityApp.departmentService = departmentService;
        UniversityApp.lectorService = lectorService;
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Print command: ");
            String command = scanner.nextLine();
            Pattern pattern = Pattern.compile("Who is head of department (\\w+)");
            Matcher matcher = pattern.matcher(command);

            if (matcher.find()) {
                String departmentName = matcher.group(1);
                departmentService.getHeadOfDepartment(departmentName);
            }


            Pattern pattern1 = Pattern.compile("Show (\\w+) statistics");
            Matcher matcher1 = pattern1.matcher(command);

            if (matcher1.find()) {
                String departmentName = matcher1.group(1);
                departmentService.showStatisticsByDepartment(departmentName);
            }

            Pattern pattern2 = Pattern.compile("Show the average salary for the department (\\w+)");
            Matcher matcher2 = pattern2.matcher(command);

            if (matcher2.find()) {
                String departmentName = matcher2.group(1);
                lectorService.showAverageSalaryByDepartmentName(departmentName);
            }

            Pattern pattern3 = Pattern.compile("^Global search by (.+)$", Pattern.CASE_INSENSITIVE);
            Matcher matcher3 = pattern3.matcher(command);
            if (matcher3.matches()) {
                String template = matcher3.group(1);
                lectorService.showLectorsNamesByTemplateSearch(template);
            }

            if(command.equals("exit")) {
                exit = true;
            }

        }
    }
    private static void addDegrees() {
        Degree degree = new Degree();
        degree.setName("Assistant");
        degree.setSalary(5000);
        degreeRepository.save(degree);

        degree = new Degree();
        degree.setName("Associate Professor");
        degree.setSalary(7000);
        degreeRepository.save(degree);

        degree = new Degree();
        degree.setName("Professor");
        degree.setSalary(9000);
        degreeRepository.save(degree);
    }
    private static void addLectors() {
       Lector lector = new Lector();
       lector.setName("Ivan Ivanov");
       List<Department> departments = new ArrayList<>();
       departments.add(departmentRepository.findById(1L));
       Degree degree = degreeRepository.findById(1L);
       lector.setDegree(degree);
       lector.setDepartments(departments);
       lectorRepository.save(lector);
        lector = new Lector();
        lector.setName("Ivan Petrov");
        departments = new ArrayList<>();
        departments.add(departmentRepository.findById(1L));
        departments.add(departmentRepository.findById(2L));
        degree = degreeRepository.findById(2L);
        lector.setDegree(degree);
        lector.setDepartments(departments);
        lectorRepository.save(lector);
        lector = new Lector();
        lector.setName("Petro Poroshenko");
        departments = new ArrayList<>();
        departments.add(departmentRepository.findById(2L));
        departments.add(departmentRepository.findById(3L));
        degree = degreeRepository.findById(3L);
        lector.setDegree(degree);
        lector.setDepartments(departments);
        lectorRepository.save(lector);
    }

    private static void addDepartments() {
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
            department.setName("Department" + (i + 1));
            Lector lector = lectorRepository.findById(i + 1);
            department.setHeadOfDepartment(lector);
            departmentRepository.save(department);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(UniversityApp.class, args);
        Scanner scanner = new Scanner(System.in);
        //addDegrees();
        //addLectors();
        //addDepartments();
        run();

    }

}
