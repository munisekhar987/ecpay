package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
    @Id
    private Long id ;
    private String name ;
    private String username ;
    private String password ;
    private Double amount ;
    private String email ;
    private String phone ;
}
