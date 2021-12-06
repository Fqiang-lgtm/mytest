package edu.soft2.pojo;

import edu.soft2.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
//import javax.mail.Address;
import javax.xml.ws.soap.Addressing;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String username;
    String pwd;
    int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Data birthday;
    Address address;
}
