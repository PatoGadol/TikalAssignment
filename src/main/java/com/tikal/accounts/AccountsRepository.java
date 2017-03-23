package com.tikal.accounts;

import com.tikal.dal.entities.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sopher on 22/03/2017.
 */
public interface AccountsRepository extends CrudRepository<Account, Long> {
}
