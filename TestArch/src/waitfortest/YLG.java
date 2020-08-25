package waitfortest;

import testarch.io.BaseIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class YLG extends BaseIO {
    public static void main(String[] args)  {
//        Scanner in  = new Scanner(System.in);

        int n = in.nextInt();
        if (n<1) return;
        List<Integer> pre = new ArrayList<>(), rear = new ArrayList<>(), midleaf = new ArrayList<>(), leaf = new ArrayList<>();
        int l = 0, maxLev = (int)Math.floor(Math.log(n)/Math.log(2));
        long num = 1;

        if (maxLev==0) {
            pre.add(in.nextInt());
            for (Integer i : pre) {
                System.out.print(i+" ");
            }
            return;
        }
        while (l<=maxLev){
            if (l==maxLev){// last lev
                for (int i = 0; i < n - Math.pow(2, maxLev) +1; i++) {
                    leaf.add(in.nextInt());
                }
                break;
            }
            pre.add(in.nextInt());
            num++;
            int lenLev = (int)Math.pow(2,l)-1;
            while (lenLev>1){
                if (2*num>n) {// 叶子节点
                    midleaf.add(in.nextInt());
                }else{
                    in.nextInt();
                }
                num++;
                lenLev--;
            }
            if (l!=0) {
                rear.add(in.nextInt());
                num++;
            }
            ++l;
        }
        Collections.reverse(rear);
        pre.addAll(leaf);
        pre.addAll(midleaf);
        pre.addAll(rear);

        for (Integer i : pre) {
            System.out.print(i+" ");
        }
    }
}
