package ru.leonid.ClientTelegramSpring.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFromTG {
    @JsonProperty("status")
    boolean ok;
    @JsonProperty("result")
    List<TelegramUpdate> telegramUpdates = new ArrayList<>();

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<TelegramUpdate> getTelegramUpdates() {
        return telegramUpdates;
    }

    public void setTelegramUpdates(List<TelegramUpdate> telegramUpdates) {
        this.telegramUpdates = telegramUpdates;
    }
}
