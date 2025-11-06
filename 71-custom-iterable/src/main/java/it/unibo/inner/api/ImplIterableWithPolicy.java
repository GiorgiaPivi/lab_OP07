package it.unibo.inner.api;

import java.util.ArrayList;
import java.util.Iterator;


public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T>{
    ArrayList<T> list = new ArrayList<>();
    Predicate<T> filter;
    public ImplIterableWithPolicy(ArrayList<T> list){
        this.list = list;
    }
    public ImplIterableWithPolicy(T[] elem , Predicate<T> filter){
        for(T e : elem){
            list.add(e);
        }
        this.filter = filter;
    }
    public ImplIterableWithPolicy(T[] elem){
        this(elem, t -> true);
    }
    public void setIterationPolicy(Predicate<T> filter){
        this.filter=filter;
    }
    public Iterator<T> iterator(){
        return new ImplementIterator();
    }
    public class ImplementIterator implements Iterator<T>{
        private int index;
        public boolean hasNext(){
            while(index < list.size()){
                if(filter == null || filter.test(list.get(index))){
                    return true;
                }    
                index++;
            }
            return false;    
        }
        public T next(){
            return list.get(index++);
        }
        public void remove(){
            list.remove(index--);
        }
    }
}
