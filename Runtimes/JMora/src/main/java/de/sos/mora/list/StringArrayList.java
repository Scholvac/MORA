package de.sos.mora.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;

public class StringArrayList extends AbstractList<String> implements List<String> {

	private transient String[] 		mBuffer;
	private int						mSize;
	
	
	public StringArrayList(String[] initial) {
		mBuffer = initial;
		mSize= initial.length;
	}
	public StringArrayList() {
		this(32);
	}
	public StringArrayList(int initialCapacity) {
		mBuffer = new String[initialCapacity];
		mSize = 0;
	}

	public String[] getArray() { return Arrays.copyOf(mBuffer, mSize); }
	/** @warn this method returns the buffer directly. In most cases the <code>getArray()</code> method should be used, that creates a copy of the buffer */
	public String[] _getBuffer() { return mBuffer; }
	
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
	public boolean addString(String element) {
		ensureCapacityInternal(mSize + 1);  // Increments modCount!!
        mBuffer[mSize++] = element;
        return true;
	}
	public void addString(int index, String element) {
		if (index == mSize)
			addString(element);
		else {
			int oldSize = mSize;
	        mSize++;
	        if (mBuffer.length == oldSize)
	        {
	        	String[] newItems = new String[this.sizePlusFiftyPercent(oldSize)];
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
	
	public String removeString(int index) {
		String previous = mBuffer[index];
        int totalOffset = mSize - index - 1;
        if (totalOffset > 0)
        {
            System.arraycopy(mBuffer, index + 1, mBuffer, index, totalOffset);
        }
        --mSize;
        mBuffer[mSize] = null;
        return previous;
	}
	public String setString(int index, String element) {
		String old = mBuffer[index];
		mBuffer[index] = element;
		return old;
	}
	public String getString(int index) {
		return mBuffer[index];
	}
	
	public void clearString() {
		Arrays.fill(mBuffer, (short)0);
		mSize = 0;
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	
	//----------------	List-API -------------------//
	
	@Override
	public void add(int index, String element) {
		addString(index, element);
	}
	@Override
	public String remove(int index) {
		return removeString(index);
	}
	@Override
	public String set(int index, String element) {
		return setString(index, element);
	}
	
	@Override
	public String get(int index) {
		return getString(index);
	}
	
	@Override
	public void clear() {
		clearString();
	}

	

}
