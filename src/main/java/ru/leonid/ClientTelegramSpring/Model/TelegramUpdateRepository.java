package ru.leonid.ClientTelegramSpring.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TelegramUpdateRepository extends JpaRepository<TelegramUpdate, Long> {
    @Query(value = "SELECT MAX(update_id) FROM TelegramUpdate")
    Optional<Long> UpdateIdMax();
}