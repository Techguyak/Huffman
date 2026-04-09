import java.util.HashMap;

public class Huffman {
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    public class Node implements Comparable<Node> {
        Character data;
        int freq;
        Node left;
        Node right;

        public Node(Character data, int freq) {
            this.data = data;
            this.freq = freq;
            this.left = null;
            this.right = null;

        }

        @Override
        public int compareTo(Node other) {
            return this.freq - other.freq;
        }

    }

    public Huffman() {
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();
    }

    public void HuffmanEncoder(String msg) throws Exception {
        if (msg == null || msg.isEmpty()) {
            throw new Exception("Message is null or empty");
        }
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : msg.toCharArray()) {
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }
        Heap<Node> minHeap = new Heap<>();
        for (Character c : freqMap.keySet()) {
            Node node = new Node(c, freqMap.get(c));
            minHeap.insert(node);
        }

        while (minHeap.size() != 1) {
            Node first = minHeap.remove();
            Node second = minHeap.remove();

            Node newNode = new Node('\0', first.freq + second.freq);
            newNode.left = first;
            newNode.right = second;
            minHeap.insert(newNode);
        }
        Node root = minHeap.remove();
        huffmanHelper(root, "");

    }

    public void huffmanHelper(Node root, String code) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            this.encoder.put(root.data, code);
            this.decoder.put(code, root.data);
        } else {
            huffmanHelper(root.left, code + "0");
            huffmanHelper(root.right, code + "1");
        }
    }

    public String encode(String msg) {
        String ans = "";
        for (char c : msg.toCharArray()) {
            ans += this.encoder.get(c);
        }
        return ans;
    }

    public String decode(String decodedMsg) {
        String ans = "";
        String currentcode = "";
        for (char c : decodedMsg.toCharArray()) {
            currentcode += c;
            if (this.decoder.containsKey(currentcode)) {
                ans += this.decoder.get(currentcode);
                currentcode = "";
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Huffman huffman = new Huffman();
        String msg = "aabbccda";
        huffman.HuffmanEncoder(msg);
        String encoded = huffman.encode(msg);
        System.out.println("Encoded: " + encoded);
        String decoded = huffman.decode(encoded);
        System.out.println("Decoded: " + decoded);
    }
}

//huffman encoding is a lossless data compression algorithm that assigns variable-length codes to input characters, with shorter codes assigned to more frequent characters. The algorithm builds a binary tree based on the frequency of characters in the input message, and then generates the codes by traversing the tree. The resulting encoded message can be decoded back to the original message using the same tree structure.

// The time complexity of the Huffman encoding algorithm is O(n log n), where n is the number of unique characters in the input message. This is because we need to build a binary tree based on the frequency of characters, which takes O(n log n) time. The encoding and decoding processes take O(m) time, where m is the length of the input message, since we need to traverse the tree for each character in the message. Overall, the algorithm is efficient for compressing data with a large number of unique characters.