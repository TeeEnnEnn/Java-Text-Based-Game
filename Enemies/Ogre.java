package Enemies;

public class Ogre extends Enemies{
    public Ogre(){
        this.name = "Ogre";
        this.hp = 10;
        this.damage = 9;
        this.defence = 0;
        this.type = "rock";
        this.score = 7;
    }

    @Override
    public void aliveText() {
        System.out.printf("""
                The ground begins to shake.
                A blood thirsty %s stands before you.
                How long to you have to live?
                """, this.name);
    }

    @Override
    public void deadText() {
        System.out.printf("""
                The ground is still shaking.
                But the %s is dead.
                You must be getting closer.
                """, this.name);
    }
}
