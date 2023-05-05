package com.example.testapp.service;

import com.example.testapp.entity.Department;
import com.example.testapp.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@org.springframework.stereotype.Service
public class DepartmentService {

    DepartmentRepo departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        if (department != null && department.getHeadOfDepartment() != null) {
            System.out.println("Глава отделения " + departmentName + ": " + department.getHeadOfDepartment().getName());
        } else {
            System.out.println("Отделение не найдено или у него нет главы.");
        }
    }

    public void showStatisticsByDepartment(String departmentName) {
        long assistantsCount = departmentRepository.countAssistantsByDepartmentName(departmentName);
        long associateProfessorsCount = departmentRepository.countAssociateProfessorsByDepartmentName(departmentName);
        long professorsCount = departmentRepository.countProfessorsByDepartmentName(departmentName);
        System.out.println("Статистика для отделения " + departmentName + ":");
        System.out.println("ассистенты - " + assistantsCount);
        System.out.println("доценты - " + associateProfessorsCount);
        System.out.println("профессора - " + professorsCount);
    }


}
