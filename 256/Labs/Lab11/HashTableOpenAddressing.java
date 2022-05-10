package Labs.Lab11;

import java.util.Iterator;
import java.util.NoSuchElementException;

    public abstract class HashTableOpenAddressing<K, V> implements MapInterface<K, V> {
        protected int numEntries;
        protected static final int DEFAULT_CAPACITY = 27;
        protected static final int MAX_CAPACITY = 10000;
        protected Entry<K, V>[] table;
        protected double loadFactor;
        protected static final double DEFAULT_LOAD_FACTOR = 0.5;

        //****************************TableEntry**************************
        public static class Entry<K, V> {
            private K key;
            private V value;
            private States state;    // Flag for the state of Entry in the table
            private enum States {CURRENT, REMOVED} // Possible values of state, null is empty

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
                state = States.CURRENT;
            }

            protected K getKey() {
                return key;
            }

            protected V getValue()	{
                return value;
            }

            protected void setValue(V newValue)	{
                value = newValue;
            }

            // Returns true if this entry is currently in the hash table.
            protected boolean isIn() {
                return state == States.CURRENT;
            }

            // Returns true if this entry has been removed from the hash table.
            protected boolean isRemoved()	{
                return state == States.REMOVED;
            }

            // Sets the state of this entry to removed.
            protected void setToRemoved()	{
                // Entry not in use, deleted from table and set State to REMOVED
                state = States.REMOVED;
            }

            public String toString() {
                return "Key-" + key + ": Value-" + value;
            }
        }
        //****************************TableEntry**************************

        // Constructors
        public HashTableOpenAddressing() {
            this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
        }

        public HashTableOpenAddressing(int initialCapacity, double loadFactorIn) {
            numEntries = 0;
            if (loadFactorIn <= 0 || initialCapacity <= 0) {
                throw new IllegalArgumentException("Initial capacity and load " +
                        "factor must be greater than 0");
            }
            else if (initialCapacity > MAX_CAPACITY)
                throw new IllegalStateException("Attempt to create a dictionary " +
                        "whose capacity is larger than " + MAX_CAPACITY);

            loadFactor = loadFactorIn;
            // Set up hash table:
            // Initial size of hash table is same as initialCapacity if it is prime;
            // otherwise increase it until it is prime size
            int tableSize = getNextPrime(initialCapacity);

            @SuppressWarnings("unchecked")
            Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[tableSize];
            table = temp;
        }

        // Method to expand the hash table array as needed
        protected void enlargeHashTable() {
            Entry<K, V>[] oldTable = table;
            int capacity = getNextPrime(oldTable.length * 2);

            // The case is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[capacity];
            table = temp;
            numEntries = 0;

            // Rehash dictionary entries from old array to the new
            for (int index = 0; index < oldTable.length; index++) {
                if ((oldTable[index] != null) && oldTable[index].isIn())
                    put(oldTable[index].getKey(), oldTable[index].getValue());
            }
        }

        // Returns a prime integer that is >= the given integer.
        private int getNextPrime(int integer) {
            // if even, add 1 to make odd
            if (integer % 2 == 0) {
                integer++;
            }

            // test odd integers
            while (!isPrime(integer)) {
                integer = integer + 2;
            }
            return integer;
        }

        // Returns true if the given integer is prime.
        private boolean isPrime(int integer) {
            boolean result;
            boolean done = false;

            // 1 and even numbers are not prime
            if ((integer == 1) || (integer % 2 == 0)) {
                result = false;
            }
            // 2 and 3 are prime
            else if ((integer == 2) || (integer == 3)) {
                result = true;
            }
            else  {				// integer is odd and >= 5
                result = true; 	// assume prime
                for (int divisor = 3; !done && (divisor * divisor <= integer);  																	divisor = divisor + 2) {
                    if (integer % divisor == 0) {
                        result = false; // divisible; not prime
                        done = true;
                    }
                }
            }
            return result;
        }


        /** Task: Gets the size of the dictionary.
         *  @return the number of entries (key-value pairs) currently
         *          in the dictionary
         */
        @Override
        public int getSize() {
            return numEntries;
        }

        /** Task: Sees whether the dictionary is empty.
         *  @return true if the dictionary is empty
         */
        @Override
        public boolean isEmpty() {
            return numEntries == 0;
        }

        /** Task: Sees whether the dictionary is full.
         *  @return true if the dictionary  the number of elements
         *  stored in the hash table is greater than the load factor will
         *  allow for this hash table
         */
        @Override
        public boolean isFull() {
            return numEntries > table.length * loadFactor;
        }

        /** Task: Removes all entries from the dictionary. */
        @Override
        public void clear() {
            @SuppressWarnings("unchecked")
            Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[table.length];
            table = temp;
            numEntries = 0;
        }

        protected int getHashIndex(K key)	{
            int hashIndex =  Math.abs(key.hashCode() % 10) % table.length;
            return hashIndex;
        }

        public String toString() {
            String result = "";
            for(int i = 0; i < table.length; i++) {
                result += i + " ";
                if(table[i] == null)
                    result += "null\n";
                else{
                    if(table[i].isRemoved() )
                        result += "has been set to \"removed\"\n";
                    else
                        result += table[i].getKey() + " " + table[i].getValue() + "\n";
                }
            }
            return result;
        }

        @Override
        public Iterator<K> getKeyIterator() {
            return new KeyIterator();
        }

        @Override
        public Iterator<V> getValueIterator() {
            return new ValueIterator();
        }

        //****************************KeyIterator**************************
        private class KeyIterator implements Iterator<K>{
            private int currentIndex; // Current position in hash table
            private int numberLeft;   // Number of entries left in iteration

            private KeyIterator() 	{
                currentIndex = 0;
                numberLeft = numEntries;
            }

            public boolean hasNext() {
                return numberLeft > 0;
            }

            public K next()	{
                K result = null;

                if (hasNext()) {
                    // Skip table locations that do not contain a current entry
                    while ((table[currentIndex] == null)
                            || table[currentIndex].isRemoved()){
                        currentIndex++;
                    }
                    result = table[currentIndex].getKey();
                    numberLeft--;
                    currentIndex++;
                }
                else
                    throw new NoSuchElementException();

                return result;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        //****************************ValueIterator**************************
        private class ValueIterator implements Iterator<V> {
            private int currentIndex; // Current position in hash table
            private int numberLeft;   // Number of entries left in iteration

            private ValueIterator() 	{
                currentIndex = 0;
                numberLeft = numEntries;
            }

            public boolean hasNext() {
                return numberLeft > 0;
            }

            public V next() {
                V result = null;

                if (hasNext()) {
                    // Skip table locations that do not contain a current entry
                    while ((table[currentIndex] == null)
                            || table[currentIndex].isRemoved()){
                        currentIndex++;
                    }
                    result = table[currentIndex].getValue();
                    numberLeft--;
                    currentIndex++;
                }
                else
                    throw new NoSuchElementException();

                return result;
            }
        }

        /** Task: Adds a new entry to the dictionary. If the given search
         *        key already exists in the dictionary, replaces the
         *        corresponding value.
         *  @param key    an object search key of the new entry
         *  @param value  an object associated with the search key
         *  @return either null if the new entry was added to the dictionary
         *          or the value that was associated with key if that value
         *          was replaced*/
        public abstract V put(final K key, final V value);


        /** Task: Removes a specific entry from the dictionary.
         *  @param key  an object search key of the entry to be removed
         *  @return either the value that was associated with the search key
         *          or null if no such object exists*/
        public abstract V remove(final K key);


        /** Task: Retrieves the value associated with a given search key.
         *  @param key  an object search key of the entry to be retrieved
         *  @return either the value that is associated with the search key
         *          or null if no such object exists */
        public abstract V getValue(final K key);


        /** Task: Sees whether a specific entry is in the dictionary.
         *  @param key  an object search key of the desired entry
         *  @return true if key is associated with an entry in the
         *          dictionary */
        public abstract boolean contains(final K key);

    }
