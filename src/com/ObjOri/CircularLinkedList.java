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
    public void insert(String data) {
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
    public void add(String data) {
        this.insert(data);
        this.advance();
    }

    /**
     * Print Function
     * Uses a current node as a cursor to traverse the circular linked list
     */
    public void printLL() {
        Node temp = current;
        while (temp.next != tail.next) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.print(tail.data);
        System.out.println();
    }
}
