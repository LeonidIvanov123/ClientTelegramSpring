package ru.leonid.ClientTelegramSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.leonid.ClientTelegramSpring.Model.UserApp;
import ru.leonid.ClientTelegramSpring.Model.UserAppRepository;
import ru.leonid.ClientTelegramSpring.Service.UserDetailsService;

import java.io.IOException;

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
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity.csrf().disable()
              .authorizeHttpRequests().requestMatchers("/register", "/images/**").permitAll().and()
              .authorizeHttpRequests().anyRequest().authenticated().
              and().formLogin().loginPage("/login").permitAll().and()
              .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
              addLogoutHandler(((request, response, authentication) -> {
                  try {response.sendRedirect("/login");} catch (IOException e) {
                      throw new RuntimeException(e);
                  }
              })).
              logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)).
              invalidateHttpSession(true).clearAuthentication(true).and().
              sessionManagement().
              maximumSessions(1).
              maxSessionsPreventsLogin(true).and().and().build();//build();
    }
}
