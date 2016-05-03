package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Random;


public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;
    TrieNode trieNode2=new TrieNode();
    TrieNode trieNode=new TrieNode();
    TrieNode trieNode3=new TrieNode();
    TrieNode trieNode4=new TrieNode();
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        int i=0;
        char c=s.charAt(i);
            if(this.children.containsKey(c))
            {
                trieNode2=trieNode2.children.get(c);
                i++;
                c=s.charAt(i);
                while(trieNode2.children.containsKey(c))
                {
                    trieNode2=trieNode2.children.get(c);
                    i++;
                    c=s.charAt(i);
                }
                while(i<s.length()) {
                    c=s.charAt(i);
                    trieNode2.children.put(c + "", new TrieNode());
                    trieNode2=trieNode2.children.get(c);
                    i++;
                }
                trieNode2.isWord=true;
            }
        else
            {
                this.children.put(c+"",new TrieNode());
            }
    }

    public boolean isWord(String s) {
        int i = 0;
        char c = s.charAt(i);
        if (this.children.containsKey(c)) {
            trieNode = trieNode.children.get(c);
            i++;
            c = s.charAt(i);
            while (trieNode.children.containsKey(c)) {
                trieNode = trieNode.children.get(c);
                if (i == s.length() - 1) {
                    if (trieNode.isWord == true) {
                        return true;
                    }
                }
                i++;
                c = s.charAt(i);
            }
        }
        return false;
    }

    String str="";
    String str1="";
    int randomnum;
    public String getAnyWordStartingWith(String s) {
        if(s==null)
        {
            Random ran = new Random();
            int randomInt = 0 + ran.nextInt(25 - 0 + 1);
            char check= (char) (97+randomInt);
            str=str+check;
                trieNode3=this.children.get(check);
                randomInt = 0 + ran.nextInt(25 - 0 + 1);
                check= (char) (97+randomInt);
                while(trieNode3.isWord==false) {
                    if (trieNode3.children.containsKey(check)) {
                        trieNode3 = trieNode3.children.get(check);
                        str=str+check;
                        randomInt = 0 + ran.nextInt(25 - 0 + 1);
                        check = (char) (97 + randomInt);
                    } else {
                        randomInt = 0 + ran.nextInt(25 - 0 + 1);
                        check = (char) (97 + randomInt);
                    }
                }
            return str;
            }
        else
        {
            int i = 0;
            char c = s.charAt(i);
            str1=str1+c;
                trieNode4 = this.children.get(c);
                    while(i<s.length()-1)
                    {

                        i++;
                        c=s.charAt(i);
                        if(trieNode4.children.containsKey(c))
                        {
                            trieNode4 = trieNode4.children.get(c);
                            str1=str1+c;

                    }
                        else
                         return null;
                }
            return str1;
        }
    }

    public String getGoodWordStartingWith(String s) {
        return "";
    }
}
