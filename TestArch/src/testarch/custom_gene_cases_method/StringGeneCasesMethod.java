package testarch.custom_gene_cases_method;

import testarch.cases.Cases;
import testarch.cases.DefaultGeneCasesUtils;
import testarch.cases.ICasesGeneMethod;
import testarch.exception.ArgumentException;
import testarch.exception.UnknownException;

import java.util.ArrayList;
import java.util.List;

public class StringGeneCasesMethod implements ICasesGeneMethod {
    private int length;
    private int kind;
    private Character ch;

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
     * @param ch 需要包含的特定字符
     * @return 返回长度为 n 的字符串。
     */
    public StringGeneCasesMethod(int length, int kind, Character ch) {
        this.length = length;
        this.kind = kind;
        this.ch = ch;
    }

    @Override
    public Cases geneCases() throws ArgumentException, UnknownException {
        String s = DefaultGeneCasesUtils.geneString(length, kind, ch);
        List<String> list = new ArrayList<>();
        list.add(s);
        return new Cases(list);
    }
}
