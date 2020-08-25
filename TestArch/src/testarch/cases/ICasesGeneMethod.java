package testarch.cases;

import testarch.exception.ArgumentException;
import testarch.exception.UnknownException;

public interface ICasesGeneMethod {
    Cases geneCases() throws ArgumentException, UnknownException;
}
