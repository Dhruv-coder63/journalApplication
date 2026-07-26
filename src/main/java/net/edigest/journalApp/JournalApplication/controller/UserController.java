package net.edigest.journalApp.JournalApplication.controller;

import net.edigest.journalApp.JournalApplication.api_response.WeatherResponse;
import net.edigest.journalApp.JournalApplication.entity.JournalEntry;
import net.edigest.journalApp.JournalApplication.entity.User;
import net.edigest.journalApp.JournalApplication.repository.UserRepository;
import net.edigest.journalApp.JournalApplication.service.JournalEntryService;
import net.edigest.journalApp.JournalApplication.service.UserService;
import net.edigest.journalApp.JournalApplication.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userIndb =userService.findByUserName(username);
        if (userIndb != null){
            userIndb.setUsername(user.getUsername());
            userIndb.setPassword(user.getPassword());
            userService.saveUser(userIndb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse =weatherService.getWeather("Delhi");
        String greeting="";
        if (weatherResponse != null){
            greeting=", Weather feels like "+weatherResponse.getMain().getFeelsLike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting,HttpStatus.OK);
    }
}