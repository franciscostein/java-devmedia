package Lectures.lecture4.part5;

public class TestLecture4Part5 {

    public static void main(String[] args) {
        Weapon handgun = new HandGun();
        Character leon = new Character("Leon", handgun);

        System.out.println(leon.getName() + " playing with his handgun");
        leon.load();
        leon.zoom();
        leon.useWeapon();

        Weapon pistol = new Pistol();
        leon.setWeapon(pistol);

        System.out.println("\n\n" + leon.getName() + " playing with his pistol");
        leon.load();
        leon.zoom();
        leon.useWeapon();

        Weapon rifle = new Rifle();
        leon.setWeapon(rifle);

        System.out.println("\n\n" + leon.getName() + " playing with his rifle");
        leon.load();
        leon.zoom();
        leon.useWeapon();
    }
}
