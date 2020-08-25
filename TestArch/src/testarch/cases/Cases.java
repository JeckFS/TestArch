package testarch.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cases  {
    private List<String> cases;

    public Cases(List<String> cases) {
        this.cases = cases;
    }

    public final void fillAllData(String[] object){
        cases.addAll(Arrays.asList(object));
    }
    public final void fillOne(String object){
        cases.add(object);
    }

    public List<String> getCases() {
        return cases;
    }
}
