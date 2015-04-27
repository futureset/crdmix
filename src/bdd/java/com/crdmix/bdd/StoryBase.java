package com.crdmix.bdd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crdmix.bdd.configuration.JBehaveConfig;
import com.crdmix.bdd.configuration.JBehaveRule;
import com.crdmix.bdd.configuration.TestConfiguration;

@ContextConfiguration(classes = { JBehaveConfig.class, TestConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class StoryBase implements Story {

    @Rule
    @Autowired
    public JBehaveRule jBehaveRule;

    @Test
    public void run() throws Exception {

    }

}
