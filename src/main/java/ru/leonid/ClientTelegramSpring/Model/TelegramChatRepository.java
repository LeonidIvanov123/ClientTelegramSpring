package ru.leonid.ClientTelegramSpring.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramChatRepository extends JpaRepository<TelegramChat, Long> {
}
