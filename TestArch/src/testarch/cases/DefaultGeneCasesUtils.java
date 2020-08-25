package testarch.cases;

import testarch.exception.ArgumentException;
import testarch.exception.UnknownException;

import java.util.*;

public class DefaultGeneCasesUtils {
    private static Random random = new Random();

    /**
     * 生成包含 n 个元素的【整数数组】。
     * 数组元素乱序排列。
     *
     * @param length 数组大小
     * @param minValue 元素的最小值
     * @param maxValue 元素的最大值
     * @param unique 标识元素是否唯一。true标识唯一，false标识不唯一。
     * @return 返回测试用例的整型集合
     */
    public static List<String> geneCases(int length, int minValue, int maxValue, boolean unique) {
        ++maxValue;
        if (unique) {
            HashSet<String> set = new HashSet<>();
            int i = 0;
            while (i < length) {
                int t = random.nextInt(maxValue);
                if (minValue <= t && t <= maxValue) {
                    if (set.add(t+""))
                        ++i;
                }
            }
            return new ArrayList<>(set);
        }
        ArrayList<String> res = new ArrayList<>(length);
        int i = 0;
        while (i < length) {
            int t = random.nextInt(maxValue);
            if (minValue <= t && t <= maxValue) {
                res.add(t+"");
                ++i;
            }
        }
        return res;
    }

    /**
     * 将ArrayList中的元素打乱。
     *
     * @param list 待打乱的集合
     * @return 打乱后的集合
     */
    public static List<Integer> shuffle(List<Integer> list) {
        Collections.shuffle(list);
        return list;
    }

    /**
     * 产生字符low到high之间的【可显示字符】
     */
    private static char geneSingleChar(char low, char high) throws ArgumentException, UnknownException {
        if (low < 32) low = 32;
        if (low > high) {
            throw new ArgumentException("【【low > high】】");
        }
        int t;
        while ((t = random.nextInt(high + 1)) < low);
        return (char) t;
    }

    /**
     * 随机生成长度为 n 的字符串。<br>
     * 若指定包含特定字符，特定字符可能连续出现。
     *
     * @param length    字符串的长度
     * @param kind <br>  0表示字符串中都为小写字母；
     *             <br>  1表示字符串中都为大写字母；
     *             <br>  2表示字符串中包含任意字符；
     *             <br>  3表示包含大小写字母的字符串；
     *             <br>  4表示大小写字符串中包含指定字符；
     *             <br>  5表示小写字符串中包含指定字符；
     *             <br>  6表示大写字符串中包含指定字符；
     * @return 返回长度为 n 的字符串。
     */
    public static String geneString(int length, int kind, Character ch) throws ArgumentException, UnknownException {
        StringBuilder sb = new StringBuilder();
        if (4 <= kind && kind <= 6) { // 包含指定字符
            if (ch == null) throw new ArgumentException("请指定包含的字符");
            int idx, count, cnt = 0;
            while ((count = random.nextInt(length)) == 0);
            int i = 0;
            int ranges = 101, hit = 20; //默认指定字符出现的概率为1/5
            char c;
            while (i < length) {
                switch (kind) {
                    case 4:
                        if (random.nextInt(ranges) < hit && cnt < count) {
                            sb.append(ch);
                            ++cnt;
                        } else {
                            c = geneSingleChar('A', 'z');
                            if ('[' <= c && c <= '`') continue;
                            sb.append(c);
                        }
                        break;
                    case 5:
                        if (random.nextInt(ranges) < hit && cnt < count) {
                            sb.append(ch);
                            ++cnt;
                        } else {
                            c = geneSingleChar('a', 'z');
                            sb.append(c);
                        }
                        break;
                    case 6:
                        if (random.nextInt(ranges) < hit && cnt < count) {
                            sb.append(ch);
                            ++cnt;
                        } else {
                            c = geneSingleChar('A', 'Z');
                            sb.append(c);
                        }
                        break;
                }
                ++i;
            }
        } else {
            int i = 0;
            char c;
            while (i < length) {
                switch (kind) {
                    case 0:
                        sb.append(geneSingleChar('a', 'z'));
                        break;
                    case 1:
                        sb.append(geneSingleChar('A', 'Z'));
                        break;
                    case 2:
                        sb.append(geneSingleChar(' ','~'));
                        break;
                    case 3:
                        c = geneSingleChar('A', 'z');
                        if ('[' <= c && c <= '`') continue;
                        sb.append(c);
                }
                ++i;
            }
        }
        return sb.toString();
    }
}
