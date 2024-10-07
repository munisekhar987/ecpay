package model;

import lombok.Data;

@Data
public class TransactDto {
    private Double transaction_amount ;
    private String sender_name ;
    private String receiver_name ;
}
