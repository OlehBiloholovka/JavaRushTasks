package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable{
    Entry<String> root = new Entry<>("0");

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
//        return super.set(index, element);
    }

    @Override
    public boolean add(String s) {
            Queue<Entry> entryQueue = new LinkedList<>();
            entryQueue.offer(this.root);
            while (!entryQueue.isEmpty()){
                Entry entry = entryQueue.poll();
                if (entry.isAvailableToAddChildren()){
                    if (entry.availableToAddLeftChildren){
                        entry.leftChild = new Entry(s);
                        entry.leftChild.parent = entry;
                    }else {
                        entry.rightChild = new Entry(s);
                        entry.rightChild.parent = entry;
                    }
                    break;
                }else {
                    if (entry.leftChild != null)
                        entryQueue.offer(entry.leftChild);
                    if (entry.rightChild != null)
                        entryQueue.offer(entry.rightChild);
                }
            }
        return true;
    }

    public boolean remove(Object o){
        if (o instanceof String){
            if (this.root == null && this.root.elementName.equals(o)){
                return false;
            }else {
                Queue<Entry> entryQueue = new LinkedList<>();
                entryQueue.offer(this.root);

                while (!entryQueue.isEmpty()){
                    Entry entry = entryQueue.poll();
                    if (entry.leftChild != null){
                        if (entry.leftChild.elementName.equals(o)){
                            entry.leftChild = null;
                            return true;
                        }else {
                            entryQueue.offer(entry.leftChild);
                        }
                    }
                    if (entry.rightChild != null){
                        if (entry.rightChild.elementName.equals(o)){
                            entry.rightChild = null;
                            return true;
                        }else {
                            entryQueue.offer(entry.rightChild);
                        }
                    }
                }
            }




//                Queue<Entry> entryQueue = new LinkedList<>();
//
//
//                if (this.root.leftChild != null){
//                    entryQueue.offer(this.root.leftChild);
//                }
//                if (this.root.rightChild != null){
//                    entryQueue.offer(this.root.rightChild);
//                }
//
//                entryQueue.offer(this.root);
//                while (!entryQueue.isEmpty()){
//                    Entry entry = entryQueue.poll();
//                    if (entry.equals(o)){
//                        if (entry.equals(this.root)){
//                            return false;
//                        }else {
//                            Entry parent = entry.parent;
//                            if (parent.leftChild.equals())
//                            return true;
//                        }
//                    }
//                }
//                return false;
//            }
        }else {
            return false;
        }
        return false;
    }

    public String getParent(String s){
        if (this.root.elementName.equals(s)){
            return null;
        }else {
            Queue<Entry> entryQueue = new LinkedList<>();
            entryQueue.offer(this.root);
            while (!entryQueue.isEmpty()){
                Entry entry = entryQueue.poll();
                if (entry.elementName.equals(s)){
                    return entry.parent.elementName;
                }else {
                    if (!entry.availableToAddLeftChildren && entry.leftChild != null){
                        entryQueue.offer(entry.leftChild);
                    }
                    if (!entry.availableToAddRightChildren && entry.rightChild != null){
                        entryQueue.offer(entry.rightChild);
                    }
                }
            }
        }
        return null;
    }


    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
//        super.add(index, element);
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
//        return super.remove(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
//        return super.subList(fromIndex, toIndex);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
//        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
//        return super.addAll(index, c);
    }



    @Override
    public int size() {
        int result = 0;
        Queue<Entry> entryQueue = new LinkedList<>();
        entryQueue.offer(this.root);
        while (!entryQueue.isEmpty()){
            Entry entry = entryQueue.poll();
            result++;
            if (entry.leftChild != null){
                entryQueue.offer(entry.leftChild);
            }
            if (entry.rightChild != null){
                entryQueue.offer(entry.rightChild);
            }
        }
        return result-1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }




    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String string){
            this.elementName = string;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren(){
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null)  availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren(){
            checkChildren();
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CustomTree strings = (CustomTree) o;

        return root != null ? root.equals(strings.root) : strings.root == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (root != null ? root.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list.size());
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println(list.size());
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }
}
