package Lectures.lecture4.part6;

//  Pattern Class Adapter
public class TestLecture4Part6 {

    public static void main(String[] args) {
        Weapon handgun = new HandGun();
        Lectures.lecture4.part6.Character leon = new Lectures.lecture4.part6.Character("Leon", handgun);

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

        Weapon shotgun = new ShotgunAdapter();
        leon.setWeapon(shotgun);

        System.out.println("\n\n" + leon.getName() + " playing with his shotgun");
        leon.load();
        leon.zoom();
        leon.useWeapon();
    }
}
