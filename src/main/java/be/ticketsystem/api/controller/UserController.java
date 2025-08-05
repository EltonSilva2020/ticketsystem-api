package be.ticketsystem.api.controller;


import be.ticketsystem.api.dto.type.UserDTO;
import be.ticketsystem.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final String USER_ENDPOINT = "/users";
    private static final String USER_ENDPOINT_ID = USER_ENDPOINT + "/{id}";

    @Autowired
    private UserService userService;

    @GetMapping(path = USER_ENDPOINT_ID)
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") String id) {

        UserDTO user = userService.get(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(path = USER_ENDPOINT)
    public ResponseEntity<Object> getAllusers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "") String orderBy,
            @RequestParam(required = false, defaultValue = "") String sort) {

        var users = userService.getAll(page, pageSize, orderBy, sort);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping(path = USER_ENDPOINT)
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {

        userService.create(userDTO);

        return new ResponseEntity<>("The resource was successfully created.", HttpStatus.CREATED);
    }

    @PutMapping(path = USER_ENDPOINT_ID)
    public ResponseEntity updateTicket(UserDTO ticketDTO) {
        userService.update(ticketDTO);

        return new ResponseEntity<>("The resource was successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = USER_ENDPOINT_ID)
    public ResponseEntity deleteUser() {

        return new ResponseEntity<>("The resource was successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
