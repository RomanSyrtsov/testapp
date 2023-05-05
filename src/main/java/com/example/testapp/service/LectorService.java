package com.example.testapp.service;

import com.example.testapp.entity.Lector;
import com.example.testapp.repo.LectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LectorService {

    LectorRepo lectorRepository;

    @Autowired
    public LectorService(LectorRepo lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public void showAverageSalaryByDepartmentName(String departmentName) {

        double averageSalary = lectorRepository.findAverageSalaryByDepartmentName(departmentName);

        System.out.printf("Средняя зарплата %s составляет %.2f\n", departmentName, averageSalary);
    }
    public void showLectorsCountByDepartmentName(Scanner scanner) {
        System.out.print("Введите название отдела: ");
        String departmentName = scanner.nextLine();

        int lectCount = lectorRepository.findLectorsCountByDepartmentName(departmentName);

        System.out.printf("Количество сотрудников %s составляет %d\n", departmentName, lectCount);
    }

    public void showLectorsNamesByTemplateSearch(String template) {
        List<String> matchingLectors = lectorRepository.findByNameContaining(template);

        System.out.println("Результаты поиска:");

        for (String name : matchingLectors) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }

}
