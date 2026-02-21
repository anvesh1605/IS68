package DSA_Assignment_01;

import java.util.*;

public class ques1 {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple", 10);
        trie.insert("apps", 7);
        trie.insert("apxs", 4);
        trie.insert("apbcs", 9);
        trie.insert("appppp", 3);
        trie.insert("apzzzz", 8);

        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("as"));

        System.out.println(trie.getTop5("ap"));   
        System.out.println(trie.getTop5("ba"));   
    }
}

class Word {
    String word;
    int freq;

    public Word(String word, int freq){
        this.word = word;
        this.freq = freq;
    }
}

class Node{
    Node[] links;
    boolean flag;

    // minheap - smallest frequency on top
    PriorityQueue<Word> top5;

    public Node(){
        links = new Node[26];
        flag = false;

        top5 = new PriorityQueue<>((a, b) -> 
                a.freq == b.freq ? b.word.compareTo(a.word) : a.freq - b.freq
        );
    }

    public boolean contains(char ch){
        return links[ch-'a'] != null;
    }

    public void put(char ch, Node node){
        links[ch-'a'] = node;
    }

    public Node get(char ch){
        return links[ch-'a'];
    }

    public void setEnd(){
        flag = true;
    }

    public boolean isEnd(){
        return flag;
    }

    public void updateTop5(String word, int freq){
        top5.removeIf(w -> w.word.equals(word)); //remove same old ele first if they exist-avoid repeatition
        top5.offer(new Word(word, freq));

        if (top5.size() > 5) top5.poll(); // remove smallest
    }

    public List<String> getSortedTop5(){
        List<Word> list = new ArrayList<>(top5);

        list.sort((a, b) -> 
                b.freq == a.freq ? a.word.compareTo(b.word) : b.freq - a.freq);

        List<String> result = new ArrayList<>();
        for (Word w : list) result.add(w.word);

        return result;
    }
}


class Trie {
    Node root; 

    public Trie() {
        root = new Node();    
    }
    
    public void insert(String word, int freq) {
        Node node = root;

        for (char ch : word.toCharArray()){
            if (!node.contains(ch)) node.put(ch, new Node());
            node = node.get(ch);

            node.updateTop5(word, freq);
        }

        node.setEnd();
    }
    
    public boolean search(String word) {
        Node node = root;
        for (char ch : word.toCharArray()){
            if (!node.contains(ch)) return false;
            node = node.get(ch);
        }
        return node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()){
            if (!node.contains(ch)) return false;
            node = node.get(ch);
        }
        return true;
    }

    public List<String> getTop5(String prefix) {
        Node node = root;

        for (char ch : prefix.toCharArray()){
            if (!node.contains(ch)) return new ArrayList<>();
            node = node.get(ch);
        }

        return node.getSortedTop5();
    }
}
