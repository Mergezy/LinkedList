package com.example.myapplication.ui.theme;
import java.io.Serializable;

public class LinkedList implements Serializable {
    private Node head;
    private static LinkedList instance;

    private LinkedList() {
        // Приватный конструктор для предотвращения создания экземпляров извне.
    }

    public static LinkedList getInstance() {
        if (instance == null) {
            instance = new LinkedList();
        }
        return instance;
    }

    // Метод для вставки элемента в конец списка
    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Метод для вставки элемента "Null" в конец списка
    public void insertNull() {
        insert(-1); // Используем -1 (или любое другое значение), чтобы пометить "Null"
    }

    // Метод для удаления элемента по индексу
    public void deleteByIndex(int index) {
        if (index < 0) {
            return;
        }

        if (index == 0) {
            if (head != null) {
                head = head.next;
            }
        } else {
            Node current = head;
            int currentIndex = 0;
            while (current != null && currentIndex < index - 1) {
                current = current.next;
                currentIndex++;
            }
            if (current != null && current.next != null) {
                current.next = current.next.next;
            }
        }
    }

    // Метод для сортировки списка по возрастанию
    public void sortAscending() {
        head = quickSort(head, true);
    }

    // Метод для сортировки списка по убыванию
    public void sortDescending() {
        head = quickSort(head, false);
    }

    // Метод для вывода элементов списка
    public String display() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        int index = 0;

        while (current != null) {
            result.append("[").append(current.data).append("|");
            if (current.next == null) {
                result.append("Null]");
            } else {
                result.append(index).append("] -> ");
            }
            index++;
            current = current.next;
        }

        if (head == null) {
            result.append("[Empty]");
        }

        return result.toString();
    }

    private Node quickSort(Node head, boolean ascending) {
        if (head == null || head.next == null) {
            return head;
        }

        int pivot = head.data;
        Node lesserHead = null, equalHead = null, greaterHead = null;
        Node current = head;

        while (current != null) {
            if (current.data < pivot) {
                if (lesserHead == null) {
                    lesserHead = new Node(current.data);
                } else {
                    lesserHead.insert(current.data);
                }
            } else if (current.data == pivot) {
                if (equalHead == null) {
                    equalHead = new Node(current.data);
                } else {
                    equalHead.insert(current.data);
                }
            } else {
                if (greaterHead == null) {
                    greaterHead = new Node(current.data);
                } else {
                    greaterHead.insert(current.data);
                }
            }
            current = current.next;
        }

        Node sortedList = null;

        if (ascending) {
            sortedList = concatenate(quickSort(lesserHead, ascending), equalHead);
            sortedList = concatenate(sortedList, quickSort(greaterHead, ascending));
        } else {
            sortedList = concatenate(quickSort(greaterHead, ascending), equalHead);
            sortedList = concatenate(sortedList, quickSort(lesserHead, ascending));
        }

        return sortedList;
    }

    private Node concatenate(Node a, Node b) {
        if (a == null) {
            return b;
        }

        Node current = a;
        while (current.next != null) {
            current = current.next;
        }
        current.next = b;

        return a;
    }
    public void clear() {
        head = null;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }



    private class Node implements Serializable {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }


        void insert(int data) {
            if (this.next == null) {
                this.next = new Node(data);
            } else {
                this.next.insert(data);
            }
        }
    }
}