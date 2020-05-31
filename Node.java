package com.company;

public class Node {
    public int key;
    public Node left;
    public Node right;

    public int getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node(int k) {
        key = k;
        left = null;
        right = null;
    }

}
