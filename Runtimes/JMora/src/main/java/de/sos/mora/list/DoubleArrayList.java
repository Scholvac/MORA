package de.sos.mora.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;

public class DoubleArrayList extends AbstractList<Double> implements List<Double> {

	private transient double[] 		mBuffer;
	private int						mSize;
	
	
	public DoubleArrayList(double[] initial) {
		mBuffer = initial;
		mSize= initial.length;
	}
	public DoubleArrayList() {
		this(32);
	}
	public DoubleArrayList(int initialCapacity) {
		mBuffer = new double[initialCapacity];
		mSize = 0;
	}

	public double[] getArray() { return Arrays.copyOf(mBuffer, mSize); }
	/** @warn this method returns the buffer directly. In most cases the <code>getArray()</code> method should be used, that creates a copy of the buffer */
	public double[] _getBuffer() { return mBuffer; }
	
	//----------------	Internal Stuff -------------------//
	private void ensureCapacityInternal(int minCapacity) {
		if (minCapacity - mBuffer.length > 0)
            grow(minCapacity);
	}
	private void grow(int minCapacity) {
		int oldCapacity = mBuffer.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - Common.MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        mBuffer = Arrays.copyOf(mBuffer, newCapacity);
	}
	private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > Common.MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : Common.MAX_ARRAY_SIZE;
    }
	private int sizePlusFiftyPercent(int oldSize)
    {
        int result = oldSize + (oldSize >> 1) + 1;
        return result < oldSize ? Common.MAX_ARRAY_SIZE : result;
    }
	
	//----------------	primitive access Stuff -------------------//	
	public boolean addDouble(double element) {
		ensureCapacityInternal(mSize + 1);  // Increments modCount!!
        mBuffer[mSize++] = element;
        return true;
	}
	public void addDouble(int index, double element) {
		if (index == mSize)
			addDouble(element);
		else {
			int oldSize = mSize;
	        mSize++;
	        if (mBuffer.length == oldSize)
	        {
	        	double[] newItems = new double[this.sizePlusFiftyPercent(oldSize)];
	            if (index > 0)
	            {
	                System.arraycopy(mBuffer, 0, newItems, 0, index);
	            }
	            System.arraycopy(mBuffer, index, newItems, index + 1, oldSize - index);
	            mBuffer = newItems;
	        }
	        else
	        {
	            System.arraycopy(mBuffer, index, mBuffer, index + 1, oldSize - index);
	        }
	        mBuffer[index] = element;
		}
	}
	
	public double removeDouble(int index) {
		double previous = mBuffer[index];
        int totalOffset = mSize - index - 1;
        if (totalOffset > 0)
        {
            System.arraycopy(mBuffer, index + 1, mBuffer, index, totalOffset);
        }
        --mSize;
        mBuffer[mSize] = 0;
        return previous;
	}
	public double setDouble(int index, double element) {
		double old = mBuffer[index];
		mBuffer[index] = element;
		return old;
	}
	public double getDouble(int index) {
		return mBuffer[index];
	}
	
	public void clearDouble() {
		Arrays.fill(mBuffer, 0.);
		mSize = 0;
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	
	//----------------	List-API -------------------//
	
	@Override
	public void add(int index, Double element) {
		addDouble(index, element);
	}
	@Override
	public Double remove(int index) {
		return removeDouble(index);
	}
	@Override
	public Double set(int index, Double element) {
		return setDouble(index, element);
	}
	
	@Override
	public Double get(int index) {
		return getDouble(index);
	}
	
	@Override
	public void clear() {
		clearDouble();
	}

	

}
