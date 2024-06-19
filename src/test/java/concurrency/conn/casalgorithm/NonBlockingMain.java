package concurrency.conn.casalgorithm;

import java.util.concurrent.atomic.AtomicReference;

class NonBlockingMain {
    public static void main(String[] args) {
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class LockFreeStack<T>{
    private AtomicReference<Node<T>> head = new AtomicReference<>();

    private static class Node<T> {
        final T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public void push(T value) {
        Node<T> newHead = new Node<>(value);
        Node<T> oldHead;
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while (!head.compareAndSet(oldHead, newHead));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.value;
    }
}
