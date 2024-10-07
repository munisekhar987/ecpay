package controller;

import model.TransactDto;
import model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TransactionService;

@RestController
@RequestMapping("/transact")
public class TransactionController {
    @Autowired
    private TransactionService transactionService ;

    @PostMapping("send")
    public ResponseEntity<Transaction> sendAmount(@RequestBody TransactDto transactDto){
        return transactionService.makeTransaction(transactDto);
    }
}
