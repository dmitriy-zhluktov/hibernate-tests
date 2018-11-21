package com.arc.tests.graph;

import com.arc.entity.graph.Client;
import com.arc.tests.BaseTest;
import com.arc.tracker.AssertSqlCount;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.QueryHints;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
public class NamedEntityGraphTest extends BaseTest {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Test
    public void testGraph() {
        List<Client> clients = findClientsQuery()
                .setHint(QueryHints.FETCHGRAPH, entityManager.getEntityGraph(Client.ACCOUNTS_GRAPH))
                .getResultList();
        clients.forEach( p -> log.info(p.getName() + " has " + p.getAccounts().size() + " accounts"));
        AssertSqlCount.assertSelectCount(1);
    }

    private TypedQuery<Client> findClientsQuery() {
        return entityManager.createQuery("select c from com.arc.entity.graph.Client c where c.age > :age", Client.class)
                .setParameter("age", 18);
    }
}
