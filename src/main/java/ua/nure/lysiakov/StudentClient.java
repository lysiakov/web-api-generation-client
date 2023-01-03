package ua.nure.lysiakov;

import java.util.List;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ua.nure.lysiakov.model.Student;
import ua.nure.lysiakov.studentwebservice.wsdl.AddStudentRequest;
import ua.nure.lysiakov.studentwebservice.wsdl.AddStudentResponse;
import ua.nure.lysiakov.studentwebservice.wsdl.DeleteStudentRequest;
import ua.nure.lysiakov.studentwebservice.wsdl.DeleteStudentResponse;
import ua.nure.lysiakov.studentwebservice.wsdl.GetAllStudentsRequest;
import ua.nure.lysiakov.studentwebservice.wsdl.GetAllStudentsResponse;
import ua.nure.lysiakov.studentwebservice.wsdl.GetStudentByIdRequest;
import ua.nure.lysiakov.studentwebservice.wsdl.GetStudentByIdResponse;
import ua.nure.lysiakov.studentwebservice.wsdl.StudentResponse;
import ua.nure.lysiakov.studentwebservice.wsdl.UpdateStudentRequest;
import ua.nure.lysiakov.studentwebservice.wsdl.UpdateStudentResponse;

public class StudentClient extends WebServiceGatewaySupport {

  public List<Student> findAllStudents() {
    GetAllStudentsRequest request = new GetAllStudentsRequest();
    GetAllStudentsResponse response =
        (GetAllStudentsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return response.getStudent().stream()
        .map(this::mapToModel)
        .toList();
  }

  public Student findStudentById(Long studentId) {
    GetStudentByIdRequest request = new GetStudentByIdRequest();
    request.setStudentId(studentId);
    GetStudentByIdResponse response =
        (GetStudentByIdResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return mapToModel(response.getStudent());
  }

  public Student createStudent(String name, String email, Long groupId) {
    AddStudentRequest request = new AddStudentRequest();
    request.setEmail(email);
    request.setName(name);
    request.setGroupId(groupId);
    AddStudentResponse response =
        (AddStudentResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return mapToModel(response.getStudent());
  }

  public Student updateStudent(Student student) {
    UpdateStudentRequest request = new UpdateStudentRequest();
    request.setId(student.getId());
    request.setEmail(student.getEmail());
    request.setName(student.getName());
    UpdateStudentResponse response =
        (UpdateStudentResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return mapToModel(response.getStudent());
  }

  public boolean deleteUser(Long studentId) {
    DeleteStudentRequest request = new DeleteStudentRequest();
    request.setStudentId(studentId);
    DeleteStudentResponse response =
        (DeleteStudentResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    return response.getServiceStatus().getStatusCode().equals("200");
  }

  private Student mapToModel(StudentResponse response) {
    Student student = new Student();
    student.setId(response.getId());
    student.setGroupId(response.getGroupId());
    student.setName(response.getName());
    student.setEmail(response.getEmail());
    return student;
  }
}
