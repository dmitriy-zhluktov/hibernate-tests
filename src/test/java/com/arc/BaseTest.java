package com.arc;

import com.arc.config.DBConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DBConfig.class})
public class BaseTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void before() {
        session = sessionFactory.openSession();
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @After
    public void after() {
        session.close();
        sessionFactory.close();
    }
}
