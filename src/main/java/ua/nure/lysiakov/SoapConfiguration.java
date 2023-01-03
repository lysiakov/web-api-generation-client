package ua.nure.lysiakov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller(){
    Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
    jaxb2Marshaller.setContextPath("ua.nure.lysiakov.studentwebservice.wsdl");
    return jaxb2Marshaller;
  }

  @Bean
  public StudentClient studentClient(Jaxb2Marshaller jaxb2Marshaller) {
    StudentClient studentClient = new StudentClient();
    studentClient.setDefaultUri("http://localhost:8080/ws");
    studentClient.setMarshaller(jaxb2Marshaller);
    studentClient.setUnmarshaller(jaxb2Marshaller);
    return studentClient;
  }

}
