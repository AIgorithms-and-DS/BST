package com.company;

import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;
    public ArrayList<Integer> allnodeskeys = new ArrayList<Integer> ();

    public BinarySearchTree() {
        root = null;
    }


    void insert(int key) {
        root = insertRec(root, key);
        allnodeskeys.add(key);
    }

    public Node getRoot() {
        return root;
    }

    public Node insertRec(Node root, int key) {

        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }


    public Node search(Node node, int key) {
        if (node == null || node.key == key)
            return node;
        if (node.key > key)
            return search(node.left, key);
        return search(node.right, key);
    }

    ArrayList <Integer> way = new ArrayList<Integer>();
    public ArrayList<Integer> way_to_node(Node node, int key) {
        if (node.key == key) {
            return way;
        } else {
            if (node.key > key) {
                way.add(node.key);
                return way_to_node(node.left, key);
            } else {
                way.add(node.key);
                return way_to_node(node.right, key);
            }
        }
    }


    public int depth(Node node, int key) {
        if (node == null || node.key == key)
            return 0;
        if (node.key > key)
            return 1 + depth(node.left, key);
        return 1 + depth(node.right, key);
    }


    public int height(Node node) {
        if (node == null)
            return 0;
        return Math.max(height(node.right), height(node.left));
    }


    public int level(Node node, int key) {
        return depth(node, key) - height(search(node, key));
    }

    void deleteKey(int key, String type) {
        if (type.equals("right"))
            root = deleteRight(root, key);
        else if (type.equals("left"))
            root = deleteLeft(root, key);
        allnodeskeys.remove((Integer)key);
    }

    public int minValue(Node root) {
        int min = root.key;
        while (root.left != null) {
            min = root.left.key;
            root = root.left;
        }
        return min;
    }
    public int maxValue(Node root){
        int max = root.key;
        while(root.right != null) {
            max = root.right.key;
            root = root.right;
        }
        return max;
    }

    Node deleteRight(Node ro, int key) {
        if (ro == null)
            return null;
        if (key < ro.key)
            ro.left = deleteRight(ro.left, key);
        else if (key > ro.key)
            ro.right = deleteRight(ro.right, key);
        else {
            if (ro.left == null)
                return ro.right;
            else if (ro.right == null)
                return ro.left;
            ro.key = minValue(ro.right);
            ro.right = deleteRight(ro.right, ro.key);
        }
        return ro;
    }

    Node deleteLeft(Node root, int key) {
        if (root == null)
            return null;
        if (key < root.key)
            root.left = deleteLeft(root.left, key);
        else if (key > root.key)
            root.right = deleteLeft(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.key = maxValue(root.left);
            root.left = deleteLeft(root.left, root.key);
        }
        return root;
    }

    void straight_bypass(Node n){
        if (n == null){
            return;
        }
        else{
            System.out.print(n.key + " ");
            straight_bypass(n.left);
            straight_bypass(n.right);
        }
    }

    void reverse_bypass(Node n){
        if (n == null) {
            return;
        }
        else {
            reverse_bypass(n.left);
            reverse_bypass(n.right);
            System.out.print(n.key + " ");
        }
    }

    void symmetrical_bypass(Node n){
        if (n == null){
            return;
        }
        else{
            symmetrical_bypass(n.left);
            System.out.print(n.key + " ");
            symmetrical_bypass(n.right);
        }
    }

    public String PrintBinaryTreeStr(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.getKey());
        String pointerRight = "└──R:";
        String pointerLeft = (root.getRight() != null) ? "├──L:" : "└──R:";
        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);
        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getKey());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }
            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──R:";
            String pointerLeft = (node.getRight() != null) ? "├──L:" : "└──R:";
            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }
    //---

}
