package hh.lemmikkikauppa.lemmikkikauppaprojekti.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.Customer;
import hh.lemmikkikauppa.lemmikkikauppaprojekti.domain.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/customers")
    public ResponseEntity<?> registerCustomer(@RequestBody RegistrationRequest request) {
        List<Customer> existingCustomers = customerRepository.findByEmail(request.email);
        if (!existingCustomers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(Map.of("message", "Email already exists"));
        }
        
        Customer customer = new Customer();
        customer.setFirstName(request.firstName);
        customer.setLastName(request.lastName);
        customer.setEmail(request.email);
        
        String password = request.password != null ? request.password : request.passwordHash;
        customer.setPasswordHash(passwordEncoder.encode(password));
        
        Customer savedCustomer = customerRepository.save(customer);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedCustomer.getId());
        response.put("firstName", savedCustomer.getFirstName());
        response.put("lastName", savedCustomer.getLastName());
        response.put("email", savedCustomer.getEmail());
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest) {
        List<Customer> customers = customerRepository.findByEmail(loginRequest.email);
        
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("message", "Invalid email or password"));
        }
        
        Customer customer = customers.get(0);
        
        String password = loginRequest.password != null ? loginRequest.password : loginRequest.passwordHash;
        
        if (passwordEncoder.matches(password, customer.getPasswordHash())) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", customer.getId());
            response.put("firstName", customer.getFirstName());
            response.put("lastName", customer.getLastName());
            response.put("email", customer.getEmail());
            
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("message", "Invalid email or password"));
        }
    }
    
    public static class LoginRequest {
        public String email;
        public String password;
        public String passwordHash; 
    }
    
    public static class RegistrationRequest {
        public String firstName;
        public String lastName;
        public String email;
        public String password;
        public String passwordHash;
    }
}
