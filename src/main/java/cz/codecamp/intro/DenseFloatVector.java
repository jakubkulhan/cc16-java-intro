package cz.codecamp.intro;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DenseFloatVector implements Iterable<Float> {

    private float[] arr;

    public DenseFloatVector(int n) {
        arr = new float[n];
    }

    public DenseFloatVector set(int i, float v) {
        arr[i] = v;
        return this;
    }

    @Override
    public Iterator<Float> iterator() {
        return new DenseFloatVectorIterator();
    }

    private class DenseFloatVectorIterator implements Iterator<Float> {

        private int i = -1;

        @Override
        public boolean hasNext() {
            return i < arr.length - 1;
        }

        @Override
        public Float next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return arr[++i];
        }
    }

    public static void main(String[] args) {
        DenseFloatVector floats = new DenseFloatVector(3);
        floats.set(0, 21).set(1, 42).set(2, 84);

        floats.forEach((f) -> System.out.println(f));

        // ...
        floats.forEach(System.out::println);
    }

}
