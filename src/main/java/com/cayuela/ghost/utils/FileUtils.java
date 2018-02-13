package com.cayuela.ghost.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String FILENAME = "gosthGameDict.txt";

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    //Static holder singleton pattern ...
    private static class SingletonHolder {
        static final FileUtils uniqueInstance = new FileUtils();

        private SingletonHolder() {
            throw new IllegalStateException("This class should not be instantiated");
        }
    }

    public static FileUtils getInstance() {
        return SingletonHolder.uniqueInstance;
    }

    private List<String> wordList;

    private FileUtils() {
        wordList = readWordList();
    }

    public List<String> getWordList() {
        return wordList;
    }

    // Not to have these lists around in memory when not needed anymore...
    public void deleteWordLists() {

        wordList.clear();
        wordList = null;
    }

    /**
     * Return the list of the words from the dictionary
     *
     * @return List<String>
     */
    public List<String> readWordList() {
        List<String> words = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader =
                    new BufferedReader(new InputStreamReader(FileUtils.class
                            .getClassLoader().getResourceAsStream(FILENAME)));

            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word.trim());
            }

        } catch (Exception exception) {
            logger.error("Failed to read... " + exception.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error("Failed to close bufferedReader... " + e.getMessage());
                }
            }
        }

        return words;
    }

}
