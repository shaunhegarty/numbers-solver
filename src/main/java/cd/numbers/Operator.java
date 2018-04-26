package cd.numbers;

enum Operator {
    ADD("+"), SUBTRACT("-"), MULTIPLY("x"), DIVIDE("÷");

    private final String operatorCharacter;

    Operator(String operatorCharacter) {
        this.operatorCharacter = operatorCharacter;
    }

    public String character() {
        return operatorCharacter;
    }
}
