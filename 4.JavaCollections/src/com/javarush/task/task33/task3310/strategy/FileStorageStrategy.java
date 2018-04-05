package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;


//    private static final int DEFAULT_INITIAL_CAPACITY = 16;
//    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
//    private int size;
//    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
//    private float loadFactor = DEFAULT_LOAD_FACTOR;


    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return containsNullValue();

        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i].getEntry() ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;


//        for (int i = 0; i < table.length; i++) {
//            if (table[i] == null)
//                continue;
//
//            Entry entry = table[i].getEntry();
//            while (entry != null) {
//                if (entry.value.equals(value))
//                    return true;
//
//                entry = entry.next;
//            }
//        }
//        return false;

    }

    private boolean containsNullValue() {
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i].getEntry() ; e != null ; e = e.next)
                if (e.value == null)
                    return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
//        for (Entry entry : table) {
//            if (entry.getValue().equals(value)){
//                return entry.getKey();
//            }
//        }
//        return null;

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null)
                continue;

            Entry entry = table[i].getEntry();

            while (entry != null) {
                if (entry.value.equals(value))
                    return entry.key;
                entry = entry.next;
            }
        }
        return 0L;

    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null)
            return entry.value;

        return null;
    }

    final static int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash, int length){
        return hash & (length-1);
    }

    private Entry getEntry(Long key){
        int hash = hash(key);
        int index =indexFor(hash, table.length);

        if (table[index] != null){
            for (Entry e = table[index].getEntry(); e != null; e = e.next){
                Long k;
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
        }
        return null;
    }

    private void resize(int newCapacity){
//        table = Arrays.copyOf(table, newCapacity);

//        FileBucket[] oldTable = table;
//        int oldCapacity = oldTable.length;
//
//
//
//        if (oldCapacity == 1 << 30) {
//            threshold = Integer.MAX_VALUE;
//            return;
//        }
//
//        Entry[] newTable = new Entry[newCapacity];
//        transfer(newTable);
//        table = newTable;
//        threshold = (int)(newCapacity * loadFactor);


        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;

    }

    private void transfer(FileBucket[] newTable){
//        FileBucket[] src = table;
////        Entry[] src = table;
//        int newCapacity = newTable.length;
//        for (int j = 0; j < src.length; j++) {
////            Entry fb = src[j];
//            FileBucket fb = src[j];
//            if (fb != null) {
//                src[j] = null;
//                do {
//                    Entry next = fb.next;
//                    int i = indexFor(fb.hash, newCapacity);
//                    fb.next = newTable[i];
//                    newTable[i] = fb;
//                    fb = next;
//                } while (fb != null);
//            }
//        }



        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                } else {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            table[i].remove();
        }

    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

}
