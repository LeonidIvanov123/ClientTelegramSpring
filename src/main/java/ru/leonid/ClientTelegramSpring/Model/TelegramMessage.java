package ru.leonid.ClientTelegramSpring.Model;

import jakarta.persistence.*;

import java.sql.Date;

@Embeddable
public class TelegramMessage {

    long message_id;
    @ManyToOne
    TelegramUser from;
    @ManyToOne
    TelegramChat chat;
    String date;
    String text;

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "message_id=" + message_id +
                ", from=" + from +
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
        return from;
    }

    public void setFrom(TelegramUser from) {
        this.from = from;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
