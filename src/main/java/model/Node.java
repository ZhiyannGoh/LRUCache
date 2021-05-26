package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Slf4j
public class Node {
    private int key = 0;
    private int value = 0;
    private Node prevNode = null;
    private Node nextNode = null;

    @Builder
    public Node(int key) {
        Random rand = new Random();
        this.key = key;
        this.value = rand.nextInt(1001);
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
