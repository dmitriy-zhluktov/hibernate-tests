package com.arc.tests.subselect;

import com.arc.tests.BaseTest;
import com.arc.entity.subselect.Client;
import com.arc.tracker.AssertSqlCount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class SubselectTest extends BaseTest {

    /**
     * Client has @Fetch(FetchMode.SUBSELECT)
     */
    @Test
    public void testSubselect() {
        List<Client> clients = session.createQuery("select c from com.arc.entity.subselect.Client c where c.age > :age")
                .setParameter("age", 18)
                .list();
        clients.forEach( p -> log.info(p.getName() + " has " + p.getAccounts().size() + " accounts"));
        AssertSqlCount.assertSelectCount(2);
    }
}
