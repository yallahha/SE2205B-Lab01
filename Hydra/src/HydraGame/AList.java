/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HydraGame;

/**
 *
 * @author Abdelkader
 */
    import java.util.Arrays;
    public class AList<T> implements ListInterface<T> {
    private T[] list; // Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    public AList() {
    this(DEFAULT_CAPACITY);
    } // end default constructor
    public AList(int initialCapacity) {
    if (initialCapacity < DEFAULT_CAPACITY) // Is initialCapacity too small?
    initialCapacity = DEFAULT_CAPACITY;
    else // Is initialCapacity too big?
    checkCapacity(initialCapacity);
    T[] tempList = (T[])new Object[initialCapacity + 1];
    list = tempList;
    numberOfEntries = 0;
    initialized = true;
    } // end constructor

    private void checkCapacity(int capacity) {
    if (capacity > MAX_CAPACITY)
    throw new IllegalStateException("Attempt to create a list whose capacity exceeds allowed maximum.");
    }
    @Override
    public void add(T newEntry) {
    checkInitialization();
    list[numberOfEntries + 1] = newEntry;
    numberOfEntries++;
    ensureCapacity(); // Ensure enough room for next add
    }
    private void checkInitialization(){
    if (!initialized)
    throw new SecurityException ("AList object is not initialized properly.");
    } 
    private void ensureCapacity() {
    int capacity = list.length - 1;
    if (numberOfEntries == capacity) {
    int newCapacity = 2 * capacity;
    //Is capacity too big?
    checkCapacity(newCapacity);
    list = Arrays.copyOf(list, newCapacity + 1);
    } // end if
    } 
        public void add(int newPosition, T newEntry) {
        checkInitialization();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
        if (newPosition <= numberOfEntries)
        makeRoom(newPosition);
        list[newPosition] = newEntry;
        numberOfEntries++;
        ensureCapacity(); // Ensure enough room for next add
        }
        else
        throw new IndexOutOfBoundsException(
        "Given position of add's new entry is out of bounds.");
    }

    private void makeRoom(int newPosition)
    {
    assert (newPosition >= 1) &&
    (newPosition <= numberOfEntries + 1);
    int newIndex = newPosition;
    int lastIndex = numberOfEntries;
    // Move each entry to next higher index, starting at end of
    // list and continuing until the entry at newIndex is moved
    for (int index = lastIndex; index >= newIndex; index--)
    list[index + 1] = list[index];
    } 
    public T remove(int givenPosition){
        checkInitialization();
    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
    {
    assert !isEmpty();
    T result = list[givenPosition]; // Get entry to be removed
    // Move subsequent entries towards entry to be removed,
    // unless it is last in list
    if (givenPosition < numberOfEntries)
    removeGap(givenPosition);
    numberOfEntries--;
    return result;
    }
    else
    throw new IndexOutOfBoundsException(
    "Illegal position given to remove operation.");
    } 
        private void removeGap(int givenPosition) {
    assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
    int removedIndex = givenPosition;
    int lastIndex = numberOfEntries;
    for (int index = removedIndex; index < lastIndex; index++)
    list[index] = list[index + 1];
    } 
            public void clear() {
    checkInitialization();
    // Clear entries but retain array; no need to create a new array
    for (int index = 1; index <= numberOfEntries; index++)
    list[index] = null;
    numberOfEntries = 0;
    } 
            public T replace(int givenPosition, T newEntry){
                checkInitialization();
                if ((givenPosition >= 1)&&(givenPosition <= numberOfEntries)) {
                assert !isEmpty();
                T originalEntry = list[givenPosition];
                list[givenPosition] = newEntry;
                return originalEntry;
                } else
                throw new IndexOutOfBoundsException(
                "Illegal position given to replace operation.");
                } 
            public T getEntry(int givenPosition) {
                checkInitialization();
                if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
                assert !isEmpty();
                return list[givenPosition];
                } else
                throw new IndexOutOfBoundsException(
                "Illegal position given to getEntry operation.");
                } 
            public int getLength(){
            return numberOfEntries;
            } 
            public boolean isEmpty(){
            return numberOfEntries == 0;
            } 
            public T[] toArray() {
            checkInitialization();
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
            for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
            } // end for
            return result;
            } 
            public boolean contains(T anEntry) {
        checkInitialization();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)){
        if (anEntry.equals(list[index]))
        found = true;
        index++;
        } // end while
        return found;
        } 

    public int getPosition(T anEntry) {
        checkInitialization();
        for (int i = 0; i <= getLength(); i++){
        if (anEntry.equals(list[i])){
        return i;
        }
        }
        return 0;
    }

   // @Override
   // public void add(String chop) {
       // checkInitialization();
     //   list[numberOfEntries + 1] = (T) chop;
   // numberOfEntries++;
    //}
   
    }