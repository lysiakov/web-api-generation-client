package ua.nure.lysiakov;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.nure.lysiakov.model.Student;

@SpringBootTest
public class StudentClientIntegrationTest {

  @Autowired
  private StudentClient studentClient;

  @Test
  public void verifyIntegrationWorks() {
    // Create three users
    Student alex_first = studentClient.createStudent("Alex First", "alexfirst@nure.ua", 1L);
    Assertions.assertNotNull(alex_first.getId());
    Student bob_second = studentClient.createStudent("Bob Second", "bobsecond@nure.ua", 1L);
    Assertions.assertNotNull(bob_second.getId());
    Student john_third = studentClient.createStudent("John Third", "johnthird@nure.ua", 2L);
    Assertions.assertNotNull(john_third.getId());
    // Check if all created
    List<Student> students = studentClient.findAllStudents();
    Assertions.assertEquals(3, students.size());
    // Update user
    bob_second.setName("Bob Second Update");
    Student updateStudent = studentClient.updateStudent(bob_second);
    Assertions.assertEquals("Bob Second Update", updateStudent.getName());
    // Delete all users
    studentClient.deleteUser(alex_first.getId());
    studentClient.deleteUser(bob_second.getId());
    studentClient.deleteUser(john_third.getId());
    // Verify all users deleted
    students = studentClient.findAllStudents();
    Assertions.assertEquals(0, students.size());
  }
}
