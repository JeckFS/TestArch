package testarch.custom_gene_cases_method;

import testarch.cases.Cases;
import testarch.cases.DefaultGeneCasesUtils;
import testarch.cases.ICasesGeneMethod;

import java.util.ArrayList;
import java.util.List;

public class IntGeneCasesMethod implements ICasesGeneMethod {
    private int length;
    private int minValue;
    private int maxValue;
    private boolean unique;

    /**
     * @param length 集合大小
     * @param minValue 最大元素值
     * @param maxValue 最小元素值
     * @param unique 是否允许元素重复
     */
    public IntGeneCasesMethod(int length, int minValue, int maxValue, boolean unique) {
        this.length = length;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unique = unique;
    }

    @Override
    public Cases geneCases() {
        List<String> integers = new ArrayList<>();
        integers.add(length+" ");
        integers.addAll(DefaultGeneCasesUtils.geneCases(length, minValue, maxValue, unique));
        return new Cases(integers);
    }
}
