package com.tikal.accounts;

import com.tikal.auth.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopher on 22/03/2017.
 */
public interface AccountsRepository extends CrudRepository<Account, Long> {
    public Account findByUsername(String userName);
}
