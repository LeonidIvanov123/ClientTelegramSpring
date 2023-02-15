package ru.leonid.ClientTelegramSpring.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
}
