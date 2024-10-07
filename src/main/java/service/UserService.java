package service;

import lombok.extern.slf4j.Slf4j;
import model.User;
import model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.net.http.HttpResponse;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository ;

    public ResponseEntity<UserDto> register(User user) {
        try {
            // Save the user to the repository
            userRepository.save(user);

            // Create UserDto from User
            UserDto userDto = new UserDto();
            userDto.setEmail(user.getEmail());
            userDto.setName(user.getName());
            userDto.setPhone(user.getPhone());
            userDto.setAmount(user.getAmount());
            userDto.setUsername(user.getUsername());

            // Return a success response with UserDto
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Return an error response
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
