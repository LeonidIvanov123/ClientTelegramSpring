package ru.leonid.ClientTelegramSpring.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelegramUpdateRepository extends JpaRepository<TelegramUpdate, Long> {
    // @Query(value = "SELECT MAX(update_id) FROM telegram_update")
    //Long UpdateIdMax();
}