package de.sos.mora.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;

public class FloatArrayList extends AbstractList<Float> implements List<Float> {

	private transient float[] 		mBuffer;
	private int						mSize;
	
	
	public FloatArrayList(float[] initial) {
		mBuffer = initial;
		mSize= initial.length;
	}
	public FloatArrayList() {
		this(32);
	}
	public FloatArrayList(int initialCapacity) {
		mBuffer = new float[initialCapacity];
		mSize = 0;
	}
	
	public float[] getArray() { return Arrays.copyOf(mBuffer, mSize); }
	/** @warn this method returns the buffer directly. In most cases the <code>getArray()</code> method should be used, that creates a copy of the buffer */
	public float[] _getBuffer() { return mBuffer; }
	
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
	public boolean addFloat(float element) {
		ensureCapacityInternal(mSize + 1);  // Increments modCount!!
        mBuffer[mSize++] = element;
        return true;
	}
	public void addFloat(int index, float element) {
		if (index == mSize)
			addFloat(element);
		else {
			int oldSize = mSize;
	        mSize++;
	        if (mBuffer.length == oldSize)
	        {
	        	float[] newItems = new float[this.sizePlusFiftyPercent(oldSize)];
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
	
	public float removeFloat(int index) {
		float previous = mBuffer[index];
        int totalOffset = mSize - index - 1;
        if (totalOffset > 0)
        {
            System.arraycopy(mBuffer, index + 1, mBuffer, index, totalOffset);
        }
        --mSize;
        mBuffer[mSize] = 0;
        return previous;
	}
	public float setFloat(int index, float element) {
		float old = mBuffer[index];
		mBuffer[index] = element;
		return old;
	}
	public float getFloat(int index) {
		return mBuffer[index];
	}
	
	public void clearFloat() {
		Arrays.fill(mBuffer, 0.f);
		mSize = 0;
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	
	//----------------	List-API -------------------//
	
	@Override
	public void add(int index, Float element) {
		addFloat(index, element);
	}
	@Override
	public Float remove(int index) {
		return removeFloat(index);
	}
	@Override
	public Float set(int index, Float element) {
		return setFloat(index, element);
	}
	
	@Override
	public Float get(int index) {
		return getFloat(index);
	}
	
	@Override
	public void clear() {
		clearFloat();
	}

	

}
