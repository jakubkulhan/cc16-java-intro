package cz.codecamp.intro;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DenseGenericVector<T> implements Iterable<T> {

    private T[] arr;

    @SuppressWarnings("unchecked")
    public DenseGenericVector(Class<T> klass, int n) {
        arr = (T[]) Array.newInstance(klass, n);
    }

    public DenseGenericVector set(int i, T v) {
        arr[i] = v;
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return new DenseGenericVectorIterator();
    }

    private class DenseGenericVectorIterator implements Iterator<T> {

        private int i = -1;

        @Override
        public boolean hasNext() {
            return i < arr.length - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return arr[++i];
        }
    }

    public static void main(String[] args) {
        DenseGenericVector<Float> floats
                = new DenseGenericVector<>(Float.class, 3);

        DenseGenericVector<Integer> ints
                = new DenseGenericVector<>(Integer.class, 3);

        // ...
    }

}
