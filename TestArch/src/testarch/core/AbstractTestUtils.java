package testarch.core;

import testarch.cases.Cases;
import testarch.cases.ICasesGeneMethod;
import testarch.exception.ArgumentException;
import testarch.exception.GlobalException;
import testarch.exception.UnknownException;
import testarch.io.BaseIO;
import testarch.io.Output;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 该类提供了验证两个程序结果是否一致的主要逻辑。
 * 主要逻辑方法有：
 * void geneCasesToFile(String path);
 * boolean compareResult(String path1, String path2);
 *
 * @author yfs
 * @version 1.0
 */
public abstract class AbstractTestUtils {
    private ICasesGeneMethod casesGeneMethod;
    private String path = "src/cases.txt";
    private String res1 = "src/res1.txt";
    private String res2 = "src/res2.txt";
    private Cases cases;
    private String p1, p2;
    private PrintStream out = System.out;


    /**
     * @param casesGeneMethod 生成测试用例的方法，不是生成完整的测试用例，它只是生成测试用例中的元素。<br>
     *                        如第一行表示元素个数，第二行表示n个元素
     *                        5
     *                        1 2 3 4 5
     *                        生成测试用例的方法只能生成：1 2 3 4 5组成的String数组。且必须生成String类型数组。
     * @param p1              待测程序1的全类名
     * @param p2              待测程序2的全类名
     */
    public AbstractTestUtils(ICasesGeneMethod casesGeneMethod, String p1, String p2) {
        this.casesGeneMethod = casesGeneMethod;
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * 将测试用例写入文件中
     */
    public void casesToFile() throws IOException {
        BufferedWriter writer = Output.getWriter(this.path);
        List<String> cases = this.cases.getCases();
        for (String s : cases) {
            writer.write(s + " ");
        }
        writer.close();
    }

    public void run() throws Exception {
        try {
            geneCases();
            casesToFile();
            resultToFile();
            if (!compareResult()) {
                System.out.println("Error");
                throw new GlobalException("出现两个目标程序结果不一致的测试用例, 请打开cases.txt查看该测试用例");
            } else {
                System.out.println("Right");
            }
        } catch (ArgumentException | UnknownException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成测试用例，测试用例保存在实例变量cases中
     */
    public void geneCases() throws ArgumentException, UnknownException {
        this.cases = casesGeneMethod.geneCases(); // 测试用例保存在AbstractTestUtils中
    }

    /**
     * 对比两个程序的输出结果是否相同
     *
     * @return 返回true表示两程序结果相同; 返回false表示两程序结果不同. 需要修正程序代码.
     */
    public boolean compareResult() throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(res1)));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(res2)));
        String line1, line2;
        while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
            if (!line1.equals(line2))
                return false;
        }
        return true;

        // Scanner和System.in只能全局唯一，否则随着函数的执行完毕，Scanner中的System.in也释放掉了
//        Scanner sc1 = new Scanner(new File(res1));
//        Scanner sc2 = new Scanner(new File(res2));
//        while (sc1.hasNext() && sc2.hasNext()) {
//            String next1 = sc1.next();
//            String next2 = sc2.next();
//            if (!next1.equals(next2)) {
//                sc1.close();
//                sc2.close();
//                return false;
//            }
//        }
//        sc1.close();
//        sc2.close();
//        if (sc1.hasNext() || sc2.hasNext())
//            return false;
//        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream("src/res2.txt")));
        String s = reader1.readLine();
        System.out.println(s);
    }

    /**
     * 将两个测试程序输出到各自的res.txt文件中
     */
    public void resultToFile() throws Exception {
        BaseIO.sc = BaseIO.in = new Scanner(new File(path));

        PrintStream printStream1 = new PrintStream(new FileOutputStream(res1));
        System.setOut(printStream1);
        Class<?> clz1 = Class.forName(p1);
        Method main1 = clz1.getMethod("main", String[].class);
        String[] args1 = {res1};
        main1.invoke(null, (Object) args1);
        printStream1.close();

        BaseIO.sc = BaseIO.in = new Scanner(new File(path));

        PrintStream printStream2 = new PrintStream(new FileOutputStream(res2));
        System.setOut(printStream2);
        Class<?> clz2 = Class.forName(p2);
        Method main2 = clz2.getMethod("main", String[].class);
        String[] args2 = {res2};
        main2.invoke(null, (Object) args2);
        printStream2.close();

        System.setOut(out);
    }
}
