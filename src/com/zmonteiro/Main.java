package com.zmonteiro;

import com.zmonteiro.entities.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        String path = "/home/me/inputs.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> employeeList = new ArrayList<>();
            String line = br.readLine();
            while (line != null){
                String fields [] = line.split(",");
                employeeList.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter salary: " );
            double value = sc.nextDouble();

            List<String> emailsList = employeeList.stream()
                    .filter(emp -> emp.getSalary() > value)
                    .map(emp -> emp.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than " + value);
            emailsList.forEach(System.out::println);

            System.out.print("Enter a letter: " );
            char letter = sc.next().charAt(0);

            double sum = employeeList.stream()
                    .filter(emp -> emp.getName().charAt(0) == letter)
                    .map(emp -> emp.getSalary())
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary of people whose name starts with " + letter + ": " + String.format("%.2f", sum));



        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
