package service;

import lombok.extern.slf4j.Slf4j;
import model.TransactDto;
import model.Transaction;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.TransactionRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository; // Repository for Transaction
    @Autowired
    private UserRepository userRepository; // Repository for User

    public ResponseEntity<Transaction> makeTransaction(TransactDto transactDto) {
        try {
            // Assume you have a way to find sender_id and receiver_id based on names
            Optional<User> sender = userRepository.findByUsername(transactDto.getSender_name());
            Optional<User> receiver = userRepository.findByUsername(transactDto.getReceiver_name());

            if (sender.isEmpty() || receiver.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // User not found
            }

            User senderUser = sender.get();
            User receiverUser = receiver.get();

            // Check if the sender has sufficient balance
            if (senderUser.getAmount() < transactDto.getTransaction_amount()) {
                return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED); // Insufficient funds
            }

            // Create a new Transaction
            Transaction transaction = new Transaction();
            transaction.setSender_id(senderUser.getId());
            transaction.setReceiver_id(receiverUser.getId());
            transaction.setTransaction_amount(transactDto.getTransaction_amount());
            transaction.setSender_name(senderUser.getUsername());
            transaction.setReceiver_name(receiverUser.getUsername());
            transaction.setTransaction_time(LocalDateTime.now());

            // Update user balances
            senderUser.setAmount(senderUser.getAmount() - transactDto.getTransaction_amount());
            receiverUser.setAmount(receiverUser.getAmount() + transactDto.getTransaction_amount());

            // Save updated users
            userRepository.save(senderUser);
            userRepository.save(receiverUser);

            // Save the transaction
            Transaction savedTransaction = transactionRepository.save(transaction);

            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception (optional)
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
