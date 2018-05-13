package com.company.ArrayCollection;

import java.util.Collection;
import java.util.Iterator;

public class ArrayCollection1 <T> implements Collection<T> {

    private T[] m = (T[])new Object[1];

    private int size ;

    @Override
    public int size() {

        return this.size;

    }

    @Override
    public boolean isEmpty() {

        return size == 0 ;

    }

    @Override
    public boolean contains(final Object o) {

        for (T element : m ){
            if (element.equals(o)) return true;
        }

        return false;

    }

    @Override
    public Iterator<T> iterator() {

        return new ElementsIterator();

    }

    @Override
    public Object[] toArray() {

        final T[] newM = (T[])new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;

    }

    @Override

    public <T1> T1[] toArray(T1[] a) {

        return (T1[])this.toArray();

    }

    @Override
    public boolean add(final T t) {

        if (size() == m.length ){
            final T[] oldArr = m;
            m = (T[])new Object[size() * 2];
            System.arraycopy(oldArr,0,m,0,oldArr.length);
        }
        m[size++] = t;
        return true;

    }

    @Override
    public boolean remove(final Object o) {

        for(int i = 0; i < m.length; i++){
            if (m[i].equals(o)){

                if(i != this.size - 1)
                System.arraycopy(m,i + 1, m, i, this.size - i - 1);
                size--;
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean containsAll(final Collection<?> c) {

        for (Object o : c){
            if (!this.contains(o)) return false;
        }
        return true;

    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {

        for (T element : c ){
            this.add(element);
        }
        return true;

    }

    @Override
    public boolean removeAll(final Collection<?> c) {

        for (final Object item : c) {
            remove(item);
        }
        return true;


    }

    @Override
    public boolean retainAll(final Collection<?> c) {

        for(Object o : this){
            if(!c.contains(o)) this.remove(o);
            return true;
        }
        return false;

    }

    @Override
    public void clear() {

        m = (T[])new Object[1];
        size = 0;

    }

    private class ElementsIterator implements Iterator<T> {
        private int size;
        @Override
        public boolean hasNext() {
           return ArrayCollection1.this.size() > size;
        }

        @Override
        public T next() {
            return ArrayCollection1.this.m[size++];
        }

    }
}

