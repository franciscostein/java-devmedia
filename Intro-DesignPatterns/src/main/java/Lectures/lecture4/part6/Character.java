package Lectures.lecture4.part6;

public class Character {

    private String name;

    private Weapon weapon;

    public Character(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    public void useWeapon() {
        System.out.print("\n" + this.name + " says: ");
        weapon.shoot();
    }

    public void load() {
        System.out.print(this.name + " is ");
        weapon.load();
    }

    public void zoom() {
        System.out.print("\n" + this.name + " says: ");
        weapon.aim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
