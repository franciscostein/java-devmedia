package Lectures.lecture7.part4.controllers;

public class TestControllers {

    public static void main(String[] args) {
        testAccountingController();
        testInventoryController();
    }

    private static void testInventoryController() {
        System.out.println("Creating Inventory Controller");
        InventoryController inventoryController = new InventoryController();

        System.out.println("Testing integration of Inventory Controller and Itautec Accounting System");
        inventoryController.createInvetoryAdapter("Itautec");
        inventoryController.updateInventory();

        System.out.println("Testing integration of Inventory Controller and IBM Accounting System");
        inventoryController.createInvetoryAdapter("IBM");
        inventoryController.updateInventory();
    }

    private static void testAccountingController() {
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
