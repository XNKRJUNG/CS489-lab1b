package edu.miu.cs.cs489.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private Double yearlySalary;
    private PensionPlan pensionPlan;

    public Employee(Long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = null;
    }

    public static void printAllEmployee(List<Employee> employees) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List <Employee> sortedEmployeeList = employees.stream().sorted(Comparator.comparing(Employee::getYearlySalary).reversed().thenComparing(Employee::getLastName)).toList();
        for (Employee e : sortedEmployeeList){
        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(e);
        System.out.println(result);
        }
    }

    public static void printsQuarterlyUpcomingEnrollees(List<Employee> employees) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        LocalDate today = LocalDate.now();
        LocalDate nextQuarterStart, nextQuarterEnd;

        if (today.getMonthValue() >= 10) { // Q4 → Next Q1 (Jan 1 - Mar 31)
            nextQuarterStart = LocalDate.of(today.getYear() + 1, Month.JANUARY, 1);
            nextQuarterEnd = LocalDate.of(today.getYear() + 1, Month.MARCH, 31);
        } else if (today.getMonthValue() >= 7) { // Q3 → Next Q4 (Oct 1 - Dec 31)
            nextQuarterStart = LocalDate.of(today.getYear(), Month.OCTOBER, 1);
            nextQuarterEnd = LocalDate.of(today.getYear(), Month.DECEMBER, 31);
        } else if (today.getMonthValue() >= 4) { // Q2 → Next Q3 (July 1 - Sep 30)
            nextQuarterStart = LocalDate.of(today.getYear(), Month.JULY, 1);
            nextQuarterEnd = LocalDate.of(today.getYear(), Month.SEPTEMBER, 30);
        } else { // Q1 → Next Q2 (Apr 1 - Jun 30)
            nextQuarterStart = LocalDate.of(today.getYear(), Month.APRIL, 1);
            nextQuarterEnd = LocalDate.of(today.getYear(), Month.JUNE, 30);
        }

        List<Employee> upcomingEnrollees = employees.stream()
                .filter(x-> x.getPensionPlan() == null)
                .filter(emp -> emp.getEmploymentDate().plusYears(3).isAfter(nextQuarterStart.minusDays(1)) &&
                        emp.getEmploymentDate().plusYears(3).isBefore(nextQuarterEnd.plusDays(1)))
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .toList();

        for (Employee e : upcomingEnrollees){
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(e);
            System.out.println(result);
        }
    }
}
