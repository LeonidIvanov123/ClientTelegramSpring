package ru.leonid.ClientTelegramSpring.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramUpdate {
    @Id
    @JsonProperty("update_id")
    long update_id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("message")
    TelegramMessage message; //в update только 1 сообщение

    @Override
    public String toString() {
        return "TelegramUpdate{" +
                "update_id=" + update_id +
                ", message=" + message +
                '}';
    }

    public long getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(long update_id) {
        this.update_id = update_id;
    }

    public TelegramMessage getMessage() {
        return message;
    }

    public void setMessage(TelegramMessage message) {
        this.message = message;
    }
}
