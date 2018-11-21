package com.arc.npo;

import com.arc.BaseTest;
import com.arc.entity.npo.Client;
import com.arc.tracker.AssertSqlCount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class NPlusOneTest extends BaseTest {

    @Test
    public void testNPO1() {
        List<Client> clients = session.createQuery("select c from com.arc.entity.npo.Client c where c.age > :age")
                .setParameter("age", 18)
                .list();
        clients.forEach( p -> log.info(p.getName() + " has " + p.getAccounts().size() + " accounts"));
        // failed because of lazy
        AssertSqlCount.assertSelectCount(1);
    }

    @Test
    public void testNPOFetch() {
        List<Client> clients = session.createQuery("select c from com.arc.entity.npo.Client c join fetch c.accounts where c.age > :age")
                .setParameter("age", 18)
                .list();
        clients.forEach( p -> log.info(p.getName() + " has " + p.getAccounts().size() + " accounts"));
        // passed because of join fetch
        AssertSqlCount.assertSelectCount(1);
    }
}
