package com.arc.tests.batchfetch;

import com.arc.entity.batchfetch.Account;
import com.arc.entity.batchfetch.Client;
import com.arc.tests.BaseTest;
import com.arc.tracker.AssertSqlCount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class BatchFetchTest extends BaseTest {

    /**
     * Client has @BatchSize(size = 2)
     */
    @Test
    public void testBatchFetch() {
        List<Client> clients = session.createQuery("select c from com.arc.entity.batchfetch.Client c where c.age > :age")
                .setParameter("age", 18)
                .list();
        clients.forEach( p -> log.info(p.getName() + " has " + p.getAccounts().size() + " accounts"));
        AssertSqlCount.assertSelectCount(3);
    }

    /**
     * blind guess
     * doesn't work :(
     */
    @Test
    public void testBlindGuess() {
        Account account1 = session.get(Account.class, 1);
        Account account2 = session.get(Account.class, 10);
        log.info(account1.getClient().getName());
        AssertSqlCount.assertSelectCount(3);
    }
}
