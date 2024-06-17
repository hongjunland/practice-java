package concurrency.conn.waitandnotify;

import java.util.LinkedList;
import java.util.Queue;

class MessageQueue {
    final Queue<Integer> queue = new LinkedList<>();
    final int MAX_SIZE = 5;
    final Object lock = new Object();
}
