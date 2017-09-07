package com.ObjOri;

public class CircularLinkedList {
    private Node current;
    private Node tail;

    public Node getCurrent() {
        return current;
    }

    public void advance() {
        if (current != null) {
            tail = current;
            current = tail.next;
        }
    }

    /**
     * Insert Function
     * @param data
     *
     * Inserts data as the first node or at the end of the linked list.
     * Maintains tail
     */
    public void insert(char data) {
        Node newNode = new Node(data);
        if(current == null) {
            current = newNode;
            tail = newNode;
            newNode.next = newNode;
        }
        else {
            tail.next = newNode;
            newNode.next = current;
            current = newNode;
        }
    }

    /**
     * Add Function
     * @param data
     *
     * 
     */
    public void add(char data) {
        this.insert(data);
        this.advance();
    }
}
