package org.example.DuplicateController;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Duplicate Controller for demo purpose")
@RequestMapping("/getcontact")
public class DuplicateController {
    @GetMapping("/contactInfo1")
    private ResponseEntity<String> contactInfo(){
        return new ResponseEntity<>("Anandan Umasankar \n anandanumasankar@gmail.com", HttpStatus.OK);
    }
}
