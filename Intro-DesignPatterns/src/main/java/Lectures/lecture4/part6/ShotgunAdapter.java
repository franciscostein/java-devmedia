package Lectures.lecture4.part6;

import Lectures.lecture4.part6.dependency.Shotgun;

//      Dessa forma conseguimos utilizar uma classe de terceiros que não temos acesso ao fonte
public class ShotgunAdapter extends Shotgun implements Weapon {

    @Override
    public void load() {
        this.loadGun();
    }

    @Override
    public void shoot() {
        this.shootToKill();
    }

    @Override
    public void aim() {
        this.targetEnemy();
    }
}
