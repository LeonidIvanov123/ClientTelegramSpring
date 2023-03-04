package ru.leonid.ClientTelegramSpring.Model;

import org.springframework.data.repository.CrudRepository;

public interface UserAppRepository extends CrudRepository<UserApp, Long> {
    UserApp findByUsername(String username);
}
