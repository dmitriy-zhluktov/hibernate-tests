package com.arc;

import com.arc.config.DBConfig;
import com.arc.entity.Account;
import com.arc.entity.Client;
import com.arc.tracker.AssertSqlCount;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
@Slf4j
public class BaseTest {

    @Autowired
    private SessionFactory sessionFactory;
    protected Session session;

    @Before
    public void before() {
        session = sessionFactory.openSession();
        log.info("Session opened");
    }

    /**
     * Проверяем кеш 1 уровня
     */
    @Test
    public void test() {
        Client client = session.get(Client.class, 1);
        client = session.get(Client.class, 1);
        AssertSqlCount.assertSelectCount(1);
    }

    /**
     * Проверяем Lazy
     */
    @Test
    public void test1() {
        Client client = session.get(Client.class, 2);
        AssertSqlCount.assertSelectCount(1);
        List<Account> list = client.getAccounts();
        AssertSqlCount.assertSelectCount(list.size() + 1);
    }

    @After
    public void after() {
        session.close();
        log.info("Session closed");
        sessionFactory.close();
        log.info("SessionFactory closed");
    }
}
