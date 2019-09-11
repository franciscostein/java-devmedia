package Lectures.lecture10.without_pattern.part6;

public class SlotMachineTest {

    public static void main(String[] args) {
        SlotMachine machine = new SlotMachine(300);
        System.out.println(machine);

        machine.insertCoin();
        machine.pullLever();
        System.out.println(machine);

        machine.insertCoin();
        machine.ejectCoin();
        machine.pullLever();
        System.out.println(machine);

        machine.insertCoin();
        machine.pullLever();
        System.out.println(machine);

        machine.insertCoin();
        machine.pullLever();
        System.out.println(machine);

        machine.ejectCoin();
        System.out.println(machine);

        machine.supplyMachine(200);
        System.out.println(machine);

        machine.insertCoin();
        machine.insertCoin();
        machine.pullLever();
        System.out.println(machine);

        machine.insertCoin();
        machine.pullLever();
        System.out.println(machine);
    }
}
