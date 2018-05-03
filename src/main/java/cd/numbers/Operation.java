package cd.numbers;

class Operation {
    Operator operator;
    int number;

    public Operation(Operator operator, int number) {
        this.operator = operator;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Operation)) {
            return false;
        }

        Operation operation = (Operation) o;
        return operation.operator == this.operator || operation.number == this.number;
    }
}
