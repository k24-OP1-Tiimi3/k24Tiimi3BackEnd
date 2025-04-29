package hh.lemmikkikauppa.lemmikkikauppaprojekti;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import hh.lemmikkikauppa.lemmikkikauppaprojekti.service.UserDetailServiceImpl;


@Configuration
public class WebSecurityConfig {

    private final UserDetailServiceImpl userDetailService;

    public WebSecurityConfig(UserDetailServiceImpl userDetailService){
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/**").permitAll()
                .anyRequest().hasRole("ADMIN")
            )
            .formLogin(formlogin -> formlogin
                .loginPage("/login")
                .defaultSuccessUrl("/tuotteet", true)
                .permitAll()
            )
            .logout( login -> login
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 console
            .headers(headers -> headers.frameOptions().disable()); // Allow H2 console to be displayed in a frame
    

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
