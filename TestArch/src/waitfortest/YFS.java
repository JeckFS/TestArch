package waitfortest;

import testarch.io.BaseIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class YFS extends BaseIO {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        // 查找下边界
        int lastFloorFirstYeZi;
        for (lastFloorFirstYeZi = 0; lastFloorFirstYeZi < n; lastFloorFirstYeZi = 2 * lastFloorFirstYeZi + 1)
            ; //查找最后一层，从左到右第一个叶子节点
        lastFloorFirstYeZi = (lastFloorFirstYeZi - 1) / 2;
        List<Integer> rightYeZi = new ArrayList<>();
        List<Integer> leftYeZi = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (i * 2 + 1 >= n) { //叶子节点
                if (i < lastFloorFirstYeZi) {
                    rightYeZi.add(i);
                } else {
                    leftYeZi.add(i);
                }
            }
        }

        // 直接输出左边界
        int left = 0;
        while (left < lastFloorFirstYeZi) {
            System.out.print(arr[left] + " ");
            left = left * 2 + 1;
        }
        // 输出下边界
        for (Integer i : leftYeZi) {
            System.out.print(arr[i] + " ");
        }
        for (Integer i : rightYeZi) {
            System.out.print(arr[i] + " ");
        }
        // 输出右边界
        List<Integer> r = new ArrayList<>();
        for (int right = 0; right < n; right = 2 * right + 2) {
            if (right*2+1 < n)
                r.add(right);
        }
        Collections.reverse(r);
        for (int k = 0; k < r.size() - 1; k++) {
            System.out.print(arr[r.get(k)] + " ");
        }
//        System.out.println(" ");
    }
}
