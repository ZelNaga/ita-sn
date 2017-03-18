package com.ita.repositories;

import com.ita.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zelnaga on 07.02.17.
 */

public interface AccountsRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);

}
