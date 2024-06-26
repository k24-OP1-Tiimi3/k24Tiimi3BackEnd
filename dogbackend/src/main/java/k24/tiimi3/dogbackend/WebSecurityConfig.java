package k24.tiimi3.dogbackend;

import k24.tiimi3.dogbackend.web.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
        @Autowired
        private UserDetailServiceImpl userDetailsService;

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {

                http
                                // .cors() // Enable CORS
                                // .and()
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(antMatcher("/css/**")).permitAll()
                                                .requestMatchers(antMatcher("/signup")).permitAll()
                                                .requestMatchers(antMatcher("/saveuser")).permitAll()
                                                .requestMatchers(antMatcher("/api/**")).permitAll()
                                                .anyRequest().authenticated())
                                .headers(headers -> headers
                                                .frameOptions(frameoptions -> frameoptions.disable())) // for h2 console
                                .formLogin(formlogin -> formlogin
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/index", true)
                                                .permitAll())
                                .logout(logout -> logout.permitAll())
                                .csrf(csrf -> csrf.disable()); // not for production, just for development

                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }

}
