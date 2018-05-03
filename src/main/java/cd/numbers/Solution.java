package cd.numbers;

import java.util.ArrayList;
import java.util.List;

class Solution implements Cloneable{
    private int value;
    private boolean valid = true;

    private List<Operation> operations = new ArrayList<>();

    public Solution() {
        //initial operation just adds starting number to zero
    }

    public Solution(int startingNumber) {
        //initial operation just adds starting number to zero
        operate(Operator.ADD, startingNumber);
    }

    @Override
    public Solution clone() {
        Solution solution = new Solution();
        for(Operation operation : operations) {
            solution.operate(operation.operator, operation.number);
        }
        return solution;
    }

    public Solution operate(Operator operator, int number) {
        operations.add(new Operation(operator, number));
        calculate();
        return this;
    }

    public int steps() {
        return operations.size();
    }

    private void calculate() {
        int result = 0;
        for(Operation operation : operations) {
            if(operation.operator == Operator.ADD) {
                result += operation.number;
            } else if (operation.operator == Operator.SUBTRACT) {
                result -= operation.number;
            } else if (operation.operator == Operator.MULTIPLY) {
                result *= operation.number;
            } else if (operation.operator == Operator.DIVIDE) {
                if(result % operation.number == 0) {
                    result  /= operation.number;
                } else {
                    valid = false;
                    break;
                }
            }
        }
        this.value = result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < operations.size(); i++) {
            Operation operation = operations.get(i);
            if(operation.operator == Operator.ADD || operation.operator == Operator.SUBTRACT) {
                if(i > 0) {
                    s.append(operation.operator.character());
                }
                s.append(operation.number);
            } else {
                if(i > 0) {
                    Operation previousOperation = operations.get(i - 1);
                    if(previousOperation.operator == Operator.ADD || previousOperation.operator == Operator.SUBTRACT) {
                        s.insert(0, "(");
                        s.append(")");
                    }
                }
                s.append(operation.operator.character());
                s.append(operation.number);
            }
        }
        s.append(" = " + getValue());
        return s.toString();
    }

    public int getValue() {
        return value;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Solution)){
            return false;
        }
        return obj.toString().equals(this.toString());

    }
}
