package com.bsf.repository;

import com.bsf.dao.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sandeepreddy on 19/04/21.
 */

@Repository
public interface AccountRepository extends CrudRepository<Account,Long>{

}
