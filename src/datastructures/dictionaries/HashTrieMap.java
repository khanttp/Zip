package datastructures.dictionaries;

import cse332.interfaces.misc.BString;
import cse332.interfaces.trie.TrieMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {

    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {


        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }



    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override


    public V insert(K key, V value) {

        // Handle null keys and values
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        V saveValue;
        Iterator<A> iterator = key.iterator();
        HashTrieNode node = (HashTrieNode) this.root;

        // Key is word, iterate through each character of key
        while (iterator.hasNext()) {
            A nextChar = iterator.next();

            // Insert new node if current character does not exist in Trie
            if (!node.pointers.containsKey(nextChar)) {
                node.pointers.put(nextChar, new HashTrieNode());
            }
            node = node.pointers.get(nextChar);

        }

        saveValue = node.value;


        // Increment size if we reached end of word
        if (saveValue == null) {
            this.size++;
        }

        node.value = value;
        return saveValue;
    }

    @Override
    public V find(K key) {


        HashTrieNode current = (HashTrieNode) this.root;
        V value;
        if (key == null) {
            throw new IllegalArgumentException();
        }

        // Walk path of key
        for (A character: key) {
            current = current.pointers.get(character);


            // if path encounters null before end of key, key is not in trie
            if (current == null) {
                return null;
            }
        }

        value = current.value;
        return value;
    }

    @Override
    public boolean findPrefix(K key) {

        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (this.root == null) {
            return false;
        }

        HashTrieNode current = (HashTrieNode) this.root;


        // Walk path of key
        for (A character: key) {
            current = current.pointers.get(character);


            // If any character is null, prefix is not in Trie
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();

        // Handle empty string keys
        } else if (key.toString().equals("")) {
            HashTrieNode current = (HashTrieNode) this.root;
            current.value = null;
            this.size--;
            return;
        }
        delete(key.iterator(),(HashTrieNode)this.root);
    }


    /**
     * Helper method for Delete()
     * removes and returns the mapping for the specified key from this map if present.
     *
     * @param Iterator<A> keyIterator, HashTrieNode current
     *            key iterator for whose mapping is to be removed from the map
     *            Current node
     * @return HashTrieNode current
     *            The current node
     *
     */

    private HashTrieNode delete(Iterator<A> keyIterator, HashTrieNode current) {
        if(keyIterator.hasNext()) {

            // create a key iterator
            A character = keyIterator.next();

            // return current if key not found
            if (!current.pointers.containsKey(character)) {
                this.size = 1;
                return current;
            }

            // set current to null if it is the final character of a word
            if (!keyIterator.hasNext()) {
                current.pointers.get(character).value = null;
                this.size--;
            }

            // recursively call delete() to continue searching
            // if current contains mapping for the key, remove the mapping
            if (delete(keyIterator, current.pointers.get(character)) == null) {
                current.pointers.remove(character);
            }
        }

        // if current has no key-value mappings and the value is null, remove it
        if(current.pointers.isEmpty() && current.value==null){
            current=null;
        }

        return current;
    }

    @Override
    public void clear() {
        this.root = new HashTrieNode();
        this.size = 0;
    }
}
