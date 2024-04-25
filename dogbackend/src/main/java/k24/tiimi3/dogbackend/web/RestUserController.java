package k24.tiimi3.dogbackend.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import k24.tiimi3.dogbackend.domain.AppUser;
import k24.tiimi3.dogbackend.domain.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins =  "*")

@RestController
@RequestMapping("/api")
public class RestUserController {
    
    @Autowired
    private AppUserRepository userRepository;

    @GetMapping("/appusers")
    public Iterable<AppUser> getMethodName(){
        return userRepository.findAll();
    }
    
}