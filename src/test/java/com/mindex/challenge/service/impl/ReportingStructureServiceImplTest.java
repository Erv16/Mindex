package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String reportingStructureUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    Employee seniorEmployee;
    Employee juniorEmployee1;
    Employee juniorEmployee2;
    List<Employee> directReports = new ArrayList<>();


    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reporting/{employeeId}";

        seniorEmployee = new Employee();
        seniorEmployee.setEmployeeId(UUID.randomUUID().toString());
        seniorEmployee.setFirstName("Bruce");
        seniorEmployee.setLastName("Wayne");
        seniorEmployee.setDepartment("Technology");
        seniorEmployee.setPosition("Team Lead");

        juniorEmployee1 = new Employee();
        juniorEmployee1.setEmployeeId(UUID.randomUUID().toString());
        juniorEmployee1.setFirstName("Dick");
        juniorEmployee1.setLastName("Grayson");
        juniorEmployee1.setDepartment("Technology");
        juniorEmployee1.setPosition("Developer III");

        juniorEmployee2 = new Employee();
        juniorEmployee2.setEmployeeId(UUID.randomUUID().toString());
        juniorEmployee2.setFirstName("Jason");
        juniorEmployee2.setLastName("Todd");
        juniorEmployee2.setDepartment("Technology");
        juniorEmployee2.setPosition("Junior Developer");

    }

    @After
    public void cleanup() {}

    @Test
    public void testNumberOfReports() {

        Employee createdReportingEmployee1 = restTemplate.postForEntity(employeeUrl, juniorEmployee1, Employee.class).getBody();
        assertNotNull(createdReportingEmployee1.getEmployeeId());
        directReports.add(createdReportingEmployee1);

        Employee createdReportingEmployee2 = restTemplate.postForEntity(employeeUrl, juniorEmployee2, Employee.class).getBody();
        assertNotNull(createdReportingEmployee2.getEmployeeId());
        directReports.add(createdReportingEmployee2);

        seniorEmployee.setDirectReports(directReports);
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, seniorEmployee, Employee.class).getBody();
        assertNotNull(createdEmployee.getEmployeeId());

        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdEmployee.getEmployeeId()).getBody();
        assertEquals(reportingStructure.getEmployee().getEmployeeId(), createdEmployee.getEmployeeId());
        assertEquals(reportingStructure.getNumberOfReports(), 2);
    }

}
