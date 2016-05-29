package calculator.service;

import java.util.Deque;

public interface OperatorService {

    public static final String NAME_PROPERTY = "rpn.service.operator";
    public static final String HELP_PROPERTY = "help operator";
	public boolean getResult(Deque <Float> operandArray, Deque <String> operatorArray);
}
