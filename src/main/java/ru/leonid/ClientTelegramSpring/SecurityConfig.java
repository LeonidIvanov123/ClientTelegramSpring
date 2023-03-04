package ru.leonid.ClientTelegramSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.leonid.ClientTelegramSpring.Model.UserApp;
import ru.leonid.ClientTelegramSpring.Model.UserAppRepository;
import ru.leonid.ClientTelegramSpring.Service.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserAppRepository userAppRepository) {
        return username -> {
            UserApp user = userAppRepository.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
      return httpSecurity.authorizeHttpRequests().requestMatchers("/register").permitAll().and()
              .authorizeHttpRequests().anyRequest().authenticated().
              and().formLogin().and().build();
    }
}
