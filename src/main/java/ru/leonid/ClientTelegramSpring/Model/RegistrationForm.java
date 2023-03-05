package ru.leonid.ClientTelegramSpring.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
Use in RegistrationController for Build Entity UserApp
 **/
public class RegistrationForm {
    @Size(min=5, message="Длина никнейма не менее 5 символов")
    private String username;
    @Size(min=5, message="Длина пароля не менее 5 символов")
    private String password;
    @NotNull(message = "Необходимо указать полное имя")
    private String fullname;
    private String street;
    private String city;

    public UserApp toUserApp(PasswordEncoder passwordEncoder){
        return new UserApp(this.username,
                passwordEncoder.encode(password),
                fullname,
                street,
                city);
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
