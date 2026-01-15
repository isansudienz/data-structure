/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicum8;

/**
 *
 * @author Admin
 */
class DataItem {
    private int iData;

    public DataItem(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }
}

class HashTable {
    private DataItem[] hashArray;
    private int size;

    public HashTable(int size) {
        this.size = size;
        hashArray = new DataItem[size];
    }

    public int hashFunc(int key) {
        return key % size;
    }

    public void insert(int key) {
        DataItem item = new DataItem(key);
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            ++hashVal;
            hashVal %= size;
        }
        hashArray[hashVal] = item;
    }

    public DataItem find(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= size;
        }
        return null;
    }

    public void displayTable() {
        System.out.println("Table: ");
        for (int j = 0; j < size; j++) {
            if (hashArray[j] != null) {
                System.out.println(" " + j + ". " + hashArray[j].getKey());
            } else {
                System.out.println(" " + j + ". --");
            }
        }
    }
}

class HashTableApp {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(15);

        // Insert 10 items
        int[] keys = {10, 25, 40, 55, 70, 85, 100, 115, 130, 145};
        for (int key : keys) {
            hashTable.insert(key);
        }

        // Display the table
        hashTable.displayTable();
        // Memasukkan 5 item lagi

        int[] additionalKeys = {20, 35, 50, 65, 80};
        for (int key : additionalKeys) {
            hashTable.insert(key);
        }

        // Menampilkan tabel dengan 15 item
        System.out.println("\nTabel dengan 15 item:");
        hashTable.displayTable();
        
        // Mencari kunci 40
        int searchKey = 40;
        DataItem item = hashTable.find(searchKey);
        System.out.println("Mencari kunci " + searchKey + ": " + 
            (item != null ? "Ditemukan di indeks " + (searchKey % 15) : "Tidak ditemukan"));
        hashTable.displayTable();
    }
}