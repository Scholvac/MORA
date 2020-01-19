package de.sos.mora.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;

public class BoolArrayList extends AbstractList<Boolean> implements List<Boolean> {

	private transient boolean[] 	mBuffer;
	private int						mSize;
	
	public BoolArrayList(boolean[] initial) {
		mBuffer = initial;
		mSize= initial.length;
	}
	public BoolArrayList() {
		this(32);
	}
	public BoolArrayList(int initialCapacity) {
		mBuffer = new boolean[initialCapacity];
		mSize = 0;
	}
	
	public boolean[] getArray() { return Arrays.copyOf(mBuffer, mSize); }
	/** @warn this method returns the buffer directly. In most cases the <code>getArray()</code> method should be used, that creates a copy of the buffer */
	public boolean[] _getBuffer() { return mBuffer; }

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
	public boolean addBool(boolean element) {
		ensureCapacityInternal(mSize + 1);  // Increments modCount!!
        mBuffer[mSize++] = element;
        return true;
	}
	public void addBool(int index, boolean element) {
		if (index == mSize)
			addBool(element);
		else {
			int oldSize = mSize;
	        mSize++;
	        if (mBuffer.length == oldSize)
	        {
	            boolean[] newItems = new boolean[this.sizePlusFiftyPercent(oldSize)];
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
	
	public boolean removeBool(int index) {
		boolean previous = mBuffer[index];
        int totalOffset = mSize - index - 1;
        if (totalOffset > 0)
        {
            System.arraycopy(mBuffer, index + 1, mBuffer, index, totalOffset);
        }
        --mSize;
        mBuffer[mSize] = false;
        return previous;
	}
	public boolean setBool(int index, boolean element) {
		boolean old = mBuffer[index];
		mBuffer[index] = element;
		return old;
	}
	public boolean getBool(int index) {
		return mBuffer[index];
	}
	
	public void clearBool() {
		Arrays.fill(mBuffer, false);
		mSize = 0;
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	
	//----------------	List-API -------------------//
	
	@Override
	public void add(int index, Boolean element) {
		addBool(index, element);
	}
	@Override
	public Boolean remove(int index) {
		return removeBool(index);
	}
	@Override
	public Boolean set(int index, Boolean element) {
		return setBool(index, element.booleanValue());
	}
	
	@Override
	public Boolean get(int index) {
		return getBool(index);
	}
	
	@Override
	public void clear() {
		clearBool();
	}

	

}
