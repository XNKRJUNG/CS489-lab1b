package edu.miu.cs.cs489;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.miu.cs.cs489.model.Employee;
import edu.miu.cs.cs489.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String [] args) throws JsonProcessingException {
        Employee emp1 = new Employee(1L,"Daniel","Agar", LocalDate.of(2018,1,17),105945.50);
        Employee emp2 = new Employee(2L,"Benard","Shaw", LocalDate.of(2022,9,3),197750.00);
        Employee emp3 = new Employee(3L,"Carly","Agar", LocalDate.of(2014,5,16),842000.75);
        Employee emp4 = new Employee(4L,"Wesley","Schneider", LocalDate.of(2022,7,21),74500.00);
        Employee emp5 = new Employee(5L,"Anna","Wiltord", LocalDate.of(2022,6,15),85750.00);
        Employee emp6 = new Employee(6L,"Yosef","Tesfalem", LocalDate.of(2022,10,31),100000.00);

        List<Employee> employeeList = Arrays.asList(emp1,emp2,emp3,emp4,emp5,emp6);

//        Employee.printAllEmployee(employeeList);

        PensionPlan pP1 = new PensionPlan("EX1089", LocalDate.of(2023,1,17), 100.00);
        PensionPlan pP2 = new PensionPlan("SM2307", LocalDate.of(2019,11,4), 1555.50);
        emp1.setPensionPlan(pP1);
        emp3.setPensionPlan(pP2);

        Employee.printsQuarterlyUpcomingEnrollees(employeeList);
    }
}
