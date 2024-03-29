package ru.leonid.ClientTelegramSpring.Model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class TelegramChat {
    @Id
    long id;
    String first_name;
    String last_name;
    String username;
    String type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TelegramMessage> messages = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TelegramChat{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
