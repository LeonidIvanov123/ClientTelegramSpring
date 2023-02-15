package ru.leonid.ClientTelegramSpring.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUpdateRepository extends JpaRepository<TelegramUpdate, Long> {
}