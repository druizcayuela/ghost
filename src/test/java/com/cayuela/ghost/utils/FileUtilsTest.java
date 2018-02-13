package com.cayuela.ghost.utils;

import com.cayuela.ghost.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class FileUtilsTest {

    private static final int TOTAL_NUMBER_OF_WORDS_IN_FILE = 354911;

    /**
     * Test case to check if read the whole txt
     */
    @Test
    public void readWordListTest() {
        StopWatch stopWatch = new Log4JStopWatch("readWordListTest");
        List<String> wordList = FileUtils.getInstance().readWordList();
        Assert.assertEquals(TOTAL_NUMBER_OF_WORDS_IN_FILE, wordList.size());
        stopWatch.stop();
    }
}
