package Queue;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

public class Queue <T>{
    private Deque <T> a, b;
    public Queue(){
        a = new ArrayDeque<>();
        b = new ArrayDeque<>();
    }

    public void add(T num){
        a.add(num);
    }

    public T remove(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        else if (!b.isEmpty()){
            return b.pop();
        }else{
            reverse();
            return b.pop();
        }
    }

    private void reverse(){
        while(!a.isEmpty()){
            b.add(a.remove());
        }
    }

    public boolean isEmpty(){
        return(a.isEmpty()) && (b.isEmpty());
    }

}
