package LinearDataStructures;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StaticStack {
    int[] data;
    int top;

    public StaticStack() {
        data = new int[10];
        top = -1;
    }

    private boolean isEmpty() {
        return top==-1;
    }

    public void push(int x) {
        top++;
        data[top]=x;
    }

    public int pop(){
        if(!isEmpty()) return data[top--];
        throw new EmptyStackException();
    }

    public int peek(){
        if(!isEmpty()) return data[top];
        throw new EmptyStackException();
    }

    private void resizeData(){
        data = Arrays.copyOf(data, data.length*2);
    }
}
