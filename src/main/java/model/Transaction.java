package model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
public class Transaction {
    @Id
    private Long transaction_id;
    private Long sender_id;
    private Long receiver_id ;
    private Double transaction_amount ;
    private String sender_name ;
    private String receiver_name ;
    private LocalDateTime transaction_time ;
}
