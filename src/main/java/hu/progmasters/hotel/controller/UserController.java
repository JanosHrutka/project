package hu.progmasters.hotel.controller;

import hu.progmasters.hotel.dto.request.RoomForm;
import hu.progmasters.hotel.dto.request.UserRegistrationForm;
import hu.progmasters.hotel.dto.response.UserInfo;
import hu.progmasters.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping()
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserInfo> registrationUser(@RequestBody @Valid UserRegistrationForm userRegistrationForm) {
        UserInfo userInfo = userService.registrationUser(userRegistrationForm);
        return new ResponseEntity(userInfo, HttpStatus.OK);
    }

    @GetMapping("/loginn")
    public ResponseEntity<UserDetails> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (User) authentication.getPrincipal();

        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }


}
