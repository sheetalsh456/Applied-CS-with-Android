package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    HashSet wordSet = new HashSet();
    ArrayList<String> wordList = new ArrayList<String>();
    HashMap<String, ArrayList<String>> lettersToWord = new HashMap<String, ArrayList<String>>();
    HashMap<Integer, ArrayList<String>> cnt = new HashMap<Integer,ArrayList<String>>();
    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;

        while ((line = in.readLine()) != null) {
            String word = line.trim();
            if (!(cnt.containsKey(word.length()))) {
                ArrayList<String> test1 = new ArrayList<String>();
                test1.add(word);
                cnt.put(word.length(), test1);
            } else {
                ArrayList<String> test2 = new ArrayList<String>();
                test2 = cnt.get(word.length());
                test2.add(word);
                cnt.put(word.length(), test2);
            }
            wordSet.add(word);
            wordList.add(word);
            if (!(lettersToWord.containsKey(helper(word)))) {
                ArrayList<String> al = new ArrayList<String>();
                al.add(word);
                lettersToWord.put(helper(word), al);
            } else {
                ArrayList<String> alnew = new ArrayList<String>();
                alnew = lettersToWord.get(helper(word));
                alnew.add(word);
                lettersToWord.put(helper(word), alnew);

            }
        }
    }

    public String helper(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }

    public boolean isGoodWord(String word, String base) {

        if (wordSet.contains(word) && !(word.contains(base)))
            return true;
        else
            return false;
    }

    String testword;
    String testword3;
    public ArrayList getAnagramsWithOneMoreLetter(String word) {
        ArrayList result = new ArrayList();
        for (char i = 'a'; i <= 'z'; i++) {
            for(char j='a';j<='z';j++) {
                testword = word + i + j;
                if (lettersToWord.containsKey(helper(testword))) {
                    ArrayList<String> test = new ArrayList<String>();
                    test = lettersToWord.get(helper(testword));
                    for (int d = 0; d < test.size(); d++) {
                        testword3 = test.get(d);
                        if (!(testword3.contains(word))) {
                            result.add(testword3);
                        }
                    }
                }
            }
        }
        return result;
    }
    String ret;
    String testword4;
    int len;
    public String pickGoodStarterWord() {
        int k,flag=0;
        int h;
        while(flag==0)
        {
            k= 3 + (int)(Math.random() * MAX_WORD_LENGTH);
            if (cnt.containsKey(k)) {
                ArrayList<String> test3 = new ArrayList<String>();
                test3 = cnt.get(k);
                h = (int)(Math.random() * (test3.size()-1));
                testword4=helper(test3.get(h));
                len=lettersToWord.get(testword4).size();
                if(len>=MIN_NUM_ANAGRAMS)
                {
                    ret= test3.get(h);
                    flag=1;
                }
            }
        }
        return ret;
    }
}
