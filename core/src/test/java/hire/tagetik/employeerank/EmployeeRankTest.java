package hire.tagetik.employeerank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRankTest {

    @Test
    void testEmployeeManagerRank() {
        Employee employee = new Manager(10.0);
        assertEquals(20.0 , employee.getRank());
    }

    @Test
    void testEmployeeDeveloperRank() {
        Employee employee = new Developer(10.0);
        assertEquals(15.0 , employee.getRank());
    }
}
