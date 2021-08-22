class IndividualBusinessman extends LegalPerson {
    private static final double FEE_MORE_1000 = 0.005;
    private static final double FEE_LESS_1000 = 0.01;

    protected double getAmountWithCommissionMore_1000(double amount) {
        return amount * FEE_MORE_1000;
    }

    protected double getAmountWithCommissionLess_1000(double amount) {
        return amount * FEE_LESS_1000;
    }

    @Override
    protected void put(double amountToPut) {
        if (amountToPut < 1000) {
            super.put(amountToPut - getAmountWithCommissionLess_1000(amountToPut));

        } else
            super.put(amountToPut - getAmountWithCommissionMore_1000(amountToPut));
    }

    @Override
    protected void info() {
        System.out.println("Пополнение:");
        System.out.println("При сумме более 1000 ед. - комиссмя 0,5%:");
        System.out.println("При сумме =/менее 1000 ед. - комиссмя 1%:");
        System.out.println("Пополнение:");
        System.out.println("Снятие - без комиссии");
    }
}

