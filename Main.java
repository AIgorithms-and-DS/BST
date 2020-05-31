package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void print_menu () {
        System.out.println ("\n1 - добавление\n" +
                "2 - поиск\n" +
                "3 - левое удаление \n" +
                "4 - правое удаление \n" +
                "5 - 3 способа обхода дерева \n" +
                "6 - глубина высота и уровень узла \n" +
                "7 - вывод дерева на экран\n" +
                "18 - Найти все вершины, через которые проходит четное число путей максимальной длины, и удалить (правым удалением) ту из них, ключ которой наименьший. Выполнить прямой (левый) обход полученного дерева.\n" +
                "0 - выход");
        System.out.print("> ");
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        boolean ch = true;
        int n;

        while (ch) {
            print_menu();
            int com = scan.nextInt();
            switch (com) {
                case 1: {
                    System.out.print("Узел > ");
                    n = scan.nextInt();
                    bst.insert(n);
                    break;
                }
                case 2: {
                    System.out.print("Ищем > ");
                    n = scan.nextInt();
                    Node search_node = bst.search(bst.getRoot(), n);
                    if (search_node == null) {
                        System.out.println("Такого нет! ");
                    }
                    else {
                        System.out.println("Есть: " + search_node.getKey());
                    }
                    break;
                }
                case 3: {
                    System.out.print("Удалить левым удалением (узел)> ");
                    n = scan.nextInt();
                    bst.deleteKey(n, "left");
                    break;
                }
                case 4: {
                    System.out.print("Удалить правым удалением (узел)> ");
                    n = scan.nextInt();
                    bst.deleteKey(n, "right");
                    break;
                }
                case 5: {
                    System.out.println("\nПрямой: ");
                    bst.straight_bypass(bst.getRoot());
                    System.out.println("\nОбратный: ");
                    bst.reverse_bypass(bst.getRoot());
                    System.out.println("\nСимметричный: ");
                    bst.symmetrical_bypass(bst.getRoot());
                    System.out.println();
                    break;
                }
                case 6: {
                    System.out.print("Узел > ");
                    n = scan.nextInt();
                    System.out.println("Глубина: " + bst.depth(bst.getRoot(), n));
                    System.out.println("Высота: " + bst.height(bst.search(bst.getRoot(), n)));
                    System.out.println("Уровень: " + bst.level(bst.getRoot(), n));
                    break;
                }
                case 7: {
                    System.out.println();
                    System.out.println(bst.PrintBinaryTreeStr(bst.getRoot()));
                    break;
                }
                case 18: {

                    int max_depth = 1;
                    ArrayList<Integer> max_depth_nodes = new ArrayList<Integer>();
                    for (int i = 0; i < bst.allnodeskeys.size(); i++) {
                        int tmp = bst.allnodeskeys.get(i);
                        int tmp_depth = bst.depth(bst.getRoot(), tmp);
                        if (tmp_depth == max_depth) {
                            max_depth_nodes.add(tmp);
                        }
                        else if (tmp_depth > max_depth) {
                            max_depth_nodes.clear();
                            max_depth = tmp_depth;
                            max_depth_nodes.add(tmp);
                        }
                    }
                    System.out.println("Путей с максимальной длиной = " + max_depth_nodes.size() );
                    System.out.println("Эти элементы");
                    for (Integer max_depth_node : max_depth_nodes) {
                        System.out.println(max_depth_node);
                    }

                    ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
                    ArrayList<Integer> tm = new ArrayList<Integer>();
                    ArrayList<Integer> all = new ArrayList<>();
                    ArrayList<Integer> unic = new ArrayList<>();
                    for (Integer max_depth_node : max_depth_nodes) {
                        bst.way.clear();
                        tm = bst.way_to_node(bst.getRoot(), max_depth_node);
                        System.out.println(tm);
                        aList.add(tm);
                        all.addAll(tm);
                        for (Integer z : tm) {
                            if (!unic.contains(z))
                                unic.add(z);
                        }
                    }

                    System.out.println("Уникальные: " + unic.toString());

                    ArrayList<Integer> unic_chet = new ArrayList<Integer>();
                    for (Integer value : unic) {
                        int count = 0;
                        for (Integer j : all) {
                            if (value.equals(j)) {
                                count++;
                            }
                        }
                        if (count % 2 == 0) {
                            unic_chet.add(value);
                        }
                    }
                    System.out.println("Уникальные встреч. четное число раз: " + unic_chet.toString());

                    if (unic_chet.size() == 0 ) {
                        System.out.println("Встреч. четн. число раз нет");
                    }
                    else {
                        int min = Integer.MAX_VALUE;
                        for (Integer i : unic_chet) {
                            if (i < min) {
                                min = i;
                            }
                        }

                        System.out.println("MIN = " + min);

                        bst.deleteKey(min, "right");

                        System.out.println("\nПрямой обход: ");
                        bst.straight_bypass(bst.getRoot());
                    }


                    break;
                }
                case 0: {
                    ch = false;
                    System.out.println("exit");
                    break;
                }
                default:
                    System.out.println("Вводите команды в соответствии с меню");
            }
        }


    }
}