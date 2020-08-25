package testarch;
import testarch.custom_gene_cases_method.IntGeneCasesMethod;
import testarch.customtest.MyTest;

public class MainTest {
    public static void main(String[] args) throws Exception {
        MyTest myTest = new MyTest(new IntGeneCasesMethod(100, 2, 1000, false), "waitfortest.YFS", "waitfortest.YLG");
        for (int i = 0; i < 50; i++) { //随机生成测试用例，并测试50次
            System.out.print("第"+i+"次：");
            myTest.run();
        }
    }
}
