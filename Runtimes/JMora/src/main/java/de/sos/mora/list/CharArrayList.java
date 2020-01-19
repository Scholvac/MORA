package de.sos.mora.list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import de.sos.mora.Common;

public class CharArrayList extends AbstractList<Character> implements List<Character> {

	private transient char[] 		mBuffer;
	private int						mSize;
	
	
	public CharArrayList(char[] initial) {
		mBuffer = initial;
		mSize= initial.length;
	}
	public CharArrayList() {
		this(32);
	}
	public CharArrayList(int initialCapacity) {
		mBuffer = new char[initialCapacity];
		mSize = 0;
	}

	public char[] getArray() { return Arrays.copyOf(mBuffer, mSize); }
	/** @warn this method returns the buffer directly. In most cases the <code>getArray()</code> method should be used, that creates a copy of the buffer */
	public char[] _getBuffer() { return mBuffer; }
	
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
	public boolean addChar(char element) {
		ensureCapacityInternal(mSize + 1);  // Increments modCount!!
        mBuffer[mSize++] = element;
        return true;
	}
	public void addChar(int index, char element) {
		if (index == mSize)
			addChar(element);
		else {
			int oldSize = mSize;
	        mSize++;
	        if (mBuffer.length == oldSize)
	        {
	        	char[] newItems = new char[this.sizePlusFiftyPercent(oldSize)];
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
	
	public char removeChar(int index) {
		char previous = mBuffer[index];
        int totalOffset = mSize - index - 1;
        if (totalOffset > 0)
        {
            System.arraycopy(mBuffer, index + 1, mBuffer, index, totalOffset);
        }
        --mSize;
        mBuffer[mSize] = 0;
        return previous;
	}
	public char setChar(int index, char element) {
		char old = mBuffer[index];
		mBuffer[index] = element;
		return old;
	}
	public char getChar(int index) {
		return mBuffer[index];
	}
	
	public void clearChar() {
		Arrays.fill(mBuffer, (char)0);
		mSize = 0;
	}
	
	@Override
	public int size() {
		return mSize;
	}
	
	
	//----------------	List-API -------------------//
	
	@Override
	public void add(int index, Character element) {
		addChar(index, element);
	}
	@Override
	public Character remove(int index) {
		return removeChar(index);
	}
	@Override
	public Character set(int index, Character element) {
		return setChar(index, element);
	}
	
	@Override
	public Character get(int index) {
		return getChar(index);
	}
	
	@Override
	public void clear() {
		clearChar();
	}

	

}
