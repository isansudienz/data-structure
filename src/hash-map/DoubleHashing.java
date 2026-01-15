package com.mycompany.practicum8;   
import java.util.Scanner;
public class DoubleHashing {
    int[] table;
    int size;

    public DoubleHashing(int size) {
        this.size = size;
        table = new int[size];
        for (int i = 0; i < size; i++) {
            table[i] = -1; // -1 menandakan sel kosong
        }
    }

    // Fungsi hash pertama
    private int hashFunc1(int key) {
        return key % size;
    }

    // Fungsi hash kedua
    private int hashFunc2(int key) {
        int c = 5; // Konstanta prima
        return c - (key % c);
    }

    // Insert data dengan double hashing
    public void insert(int key) {
        int index = hashFunc1(key);
        int step = hashFunc2(key);

        int i = 0;
        int newIndex;
        do {
            newIndex = (index + i * step) % size;
            if (table[newIndex] == -1) {
                table[newIndex] = key;
                System.out.println("Kunci " + key + " disimpan di indeks " + newIndex);
                return;
            }
            i++;
            if (i >= size) {
                System.out.println("Tabel penuh! Kunci " + key + " tidak bisa disimpan.");
                return;
            }
        } while (true);
    }
    public void delete(int key) {
        int index = hashFunc1(key);
        int step = hashFunc2(key);

        int i = 0;
        int searchIndex;
        do {
            searchIndex = (index + i * step) % size;
            if (table[searchIndex] == -1) {
                System.out.println("Kunci " + key + " tidak ditemukan di tabel.");
                return;
            }
            if (table[searchIndex] == key) {
                table[searchIndex] = -1;
                System.out.println("Kunci " + key + " telah dihapus dari indeks " + searchIndex);
                return;
            }
            i++;
            if (i >= size) {
                System.out.println("Kunci " + key + " tidak ditemukan di tabel.");
                return;
            }
        }while(true);
    }

    // Tampilkan tabel
    public void display() {
        System.out.println("Isi Tabel Hash:");
        for (int i = 0; i < size; i++) {
            System.out.println("Indeks " + i + ": " + (table[i] == -1 ? "Kosong" : table[i]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoubleHashing dh = new DoubleHashing(12); // Ukuran tabel 12

        // Sisipkan data awal
        int[] keys = {472, 178, 769, 236, 62, 537, 539, 887,10};
        for (int key : keys) {
            dh.insert(key);
        }
        // Tampilkan tabel akhir
        dh.display();
    }
}