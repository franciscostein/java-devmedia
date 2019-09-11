package Lectures.lecture7.part3.controllers;

public class TestControllers {

    public static void main(String[] args) {
        testingAccountingController();
    }

    private static void testingAccountingController() {
        System.out.println("Creating Accounting Controller");
        AccountingController accountingController = new AccountingController();

        System.out.println("Testing integration of Accounting Controller and Itautec Accounting System");
        accountingController.createAccountingAdapter("Itautec");
        accountingController.calculateTax();

        System.out.println("Testing integration of Accounting Controller and IBM Accounting System");
        accountingController.createAccountingAdapter("IBM");
        accountingController.calculateTax();
    }
}
