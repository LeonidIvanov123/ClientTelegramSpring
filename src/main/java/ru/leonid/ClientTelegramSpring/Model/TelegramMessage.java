package ru.leonid.ClientTelegramSpring.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class TelegramMessage {
    @Id
    long message_id;
    @JsonProperty("from")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    TelegramUser from_user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    TelegramChat chat;
    @JsonProperty("date")
    String date;
    String text;

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "message_id=" + message_id +
                ", from=" + from_user +
                ", chat=" + chat +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public TelegramUser getFrom() {
        return from_user;
    }

    public void setFrom(TelegramUser from) {
        this.from_user = from;
    }

    public TelegramChat getChat() {
        return chat;
    }

    public void setChat(TelegramChat chat) {
        this.chat = chat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String senddate) {
        this.date = senddate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
