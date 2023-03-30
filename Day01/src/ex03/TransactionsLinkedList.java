package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        private Transaction data;
        private Node next;
        private Node last;

        public Node(Node next, Node last, Transaction data) {
            this.data = data;
            this.next = next;
            this.last = last;
        }
        public Node getNext() { return next; }
        public Node getLast() { return last; }
        public Transaction getData() { return data; }
        
        public void setNext(Node next) { this.next = next; }
        public void setLast(Node last) { this.last = last; }
        public void setData(Transaction data) { this.data = data; }


    }

    private int size = 0;
    private Node newest = new Node(null, null, null);
    private Node latest = new Node(null, null, null);

    public TransactionsLinkedList() {
        newest.setNext(latest);
        latest.setLast(newest);
    }

    @Override
    public void addTrans(Transaction transaction) {
        Node temp = new Node(null, null, transaction);
        if (size == 0) {
            newest = temp;
            latest = temp;
            latest.setLast(newest);
            newest.setNext(latest);
        } else {
            latest.setNext(temp);
            temp.setLast(latest);
            latest = temp;
        }
        size += 1;
    }

    @Override
    public void deleteTrans(UUID id) {
        Node temp = newest;
        while (temp != null) {
            if (temp.getData().getID().equals(id)) {
                if (temp.getLast() != null) {
                    temp.getLast().setNext(temp.getNext());
                }
                if (temp.getNext() != null) {
                    if (temp.getLast() == null) {
                        temp = temp.getNext();
                    }
                    temp.getNext().setLast(temp.getLast());
                }
                size -= 1;
                return;
            }
            temp = temp.getNext();
        }
        throw new TransactionNotFoundException("Transaction not found");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] tempArr = new Transaction[size];
        Node temp = newest;
        if (temp.getData() != null) {
            for (int i = 0; i < size; ++i) {
                tempArr[i] = temp.getData();
                temp = temp.getNext();
            }
        }
        return tempArr;
    }
}
