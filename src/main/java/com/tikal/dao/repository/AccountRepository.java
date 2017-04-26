package com.tikal.dao.repository;

import com.tikal.dao.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sopher on 23/03/2017.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserName(String userName);
}
