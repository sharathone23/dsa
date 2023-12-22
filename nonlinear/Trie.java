package nonlinear;

import java.util.HashMap;
public class Trie {
    TrieNode root;

    Trie(){
        TrieNode node = new TrieNode();
        this.root = node;
    }

    /**
     * Inserts the string word into the trie
     * @param word
     */
    void insert(String word){
        if(word == null || word.isEmpty()) return;
        TrieNode currentNode = root;
        for(int i=0;i<word.length();i++){
            Character currChar = word.charAt(i);
            TrieNode currCharTrieNode = currentNode.children.get(currChar);
            if(currCharTrieNode == null){
                currCharTrieNode = new TrieNode();
            }
            currentNode.children.put(currChar, currCharTrieNode);
            currentNode = currCharTrieNode;
        }
        currentNode.isWord = true;
    }

    /**
     * Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
     * @param word
     * @return
     */
    boolean search(String word){
        if(word == null || word.isEmpty()) return false;
        TrieNode lastCharNode = getLastCharTrieNode(word);
        return lastCharNode != null && lastCharNode.isWord;
    }

   /**
    * Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
    */
    boolean startsWith(String prefix){
        if(prefix == null || prefix.isEmpty()) return false;
        TrieNode lastCharNode = getLastCharTrieNode(prefix);
        return lastCharNode != null;
    }

    private TrieNode getLastCharTrieNode(String word){
        TrieNode currentNode = root;
        for(int i=0;i<word.length();i++){
            Character currChar = word.charAt(i);
            TrieNode currCharTrieNode = currentNode.children.get(currChar);
            if(currCharTrieNode == null){
                return null;
            }
            currentNode = currCharTrieNode;
        }
        return currentNode;
    }

}

class TrieNode{
    HashMap<Character, TrieNode> children;
    boolean isWord;

    TrieNode(){
        this.children = new HashMap<>();
    }
}
