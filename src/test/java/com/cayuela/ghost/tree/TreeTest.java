package com.cayuela.ghost.tree;

import com.cayuela.ghost.config.AppConfig;
import com.cayuela.ghost.tree.Node;
import com.cayuela.ghost.tree.Tree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class TreeTest {


    /**
     * Test case to check if read the whole txt
     */
    @Test
    public void buildACompleteTree() {
        StopWatch stopWatch = new Log4JStopWatch("buildACompleteTree");
        Node root = Tree.getInstance().getRoot();
        Assert.assertNotNull(root);
        stopWatch.stop();
    }
}
