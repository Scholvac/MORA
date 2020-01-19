package de.sos.mora.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;

public class IntArrayList extends AbstractList<Integer> implements List<Integer> {

	private transient int[] 		mBuffer;
	private int						mSize;
	
	
	public IntArrayList(int[] initial) {
		mBuffer = initial;
		mSize= initial.length;
	}
	public IntArrayList() {
		this(32);
	}
	public IntArrayList(int initialCapacity) {
		mBuffer = new int[initialCapacity];
		mSize = 0;
	}

	public int[] getArray() { return Arrays.copyOf(mBuffer, mSize); }
	/** @warn this method returns the buffer directly. In most cases the <code>getArray()</code> method should be used, that creates a copy of the buffer */
	public int[] _getBuffer() { return mBuffer; }
	
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
	public boolean addInt(int element) {
		ensureCapacityInternal(mSize + 1);  // Increments modCount!!
        mBuffer[mSize++] = element;
        return true;
	}
	public void addInt(int index, int element) {
		if (index == mSize)
			addInt(element);
		else {
			int oldSize = mSize;
	        mSize++;
	        if (mBuffer.length == oldSize)
	        {
	        	int[] newItems = new int[this.sizePlusFiftyPercent(oldSize)];
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
	
	public int removeInt(int index) {
		int previous = mBuffer[index];
        int totalOffset = mSize - index - 1;
        if (totalOffset > 0)
        {
            System.arraycopy(mBuffer, index + 1, mBuffer, index, totalOffset);
        }
        --mSize;
        mBuffer[mSize] = 0;
        return previous;
	}
	public int setInt(int index, int element) {
		int old = mBuffer[index];
		mBuffer[index] = element;
		return old;
	}
	public int getInt(int index) {
		return mBuffer[index];
	}
	
	public void clearInt() {
		Arrays.fill(mBuffer, 0);
		mSize = 0;
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	
	//----------------	List-API -------------------//
	
	@Override
	public void add(int index, Integer element) {
		addInt(index, element);
	}
	@Override
	public Integer remove(int index) {
		return removeInt(index);
	}
	@Override
	public Integer set(int index, Integer element) {
		return setInt(index, element);
	}
	
	@Override
	public Integer get(int index) {
		return getInt(index);
	}
	
	@Override
	public void clear() {
		clearInt();
	}

	

}
