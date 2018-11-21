package com.arc.tests.graph;

import com.arc.entity.graph.Client;
import com.arc.tests.BaseTest;
import com.arc.tracker.AssertSqlCount;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.QueryHints;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
public class NamedEntityGraphTest extends BaseTest {

    @Test
    public void testGraph() {
        List<Client> clients = findClientsQuery()
                .setHint(QueryHints.FETCHGRAPH, session.getEntityGraph(Client.ACCOUNTS_GRAPH))
                .getResultList();
        clients.forEach( p -> log.info(p.getName() + " has " + p.getAccounts().size() + " accounts"));
        AssertSqlCount.assertSelectCount(1);
    }

    private TypedQuery<Client> findClientsQuery() {
        return session.createQuery("select c from com.arc.entity.graph.Client c where c.age > :age", Client.class)
                .setParameter("age", 18);
    }
}
