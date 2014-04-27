package gobject.hello.shooting;

import static common.CommonLogic.*;
import gobject.GameObject;
import gobject.GameScene;
import gobject.hello.shooting.bullet.GStgBullet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ShootingManager extends GameScene {
    private static ShootingManager instance;
    private List<GStgCharacter> friendlies = new ArrayList<GStgCharacter>();
    private List<GStgCharacter> enemies = new ArrayList<GStgCharacter>();
    private List<GStgBullet> bullets = new ArrayList<GStgBullet>();
    private List<GStgBullet> generatingBullets = new ArrayList<GStgBullet>();

    private ShootingManager() {
        setClearColorWhite();
        friendlies.add((GStgCharacter) addGO(new MyShip()));
        enemies.add((GStgCharacter) addGO(new EnemyShip()));
    }

    public static ShootingManager getInstance() {
        if (instance == null) {
            instance = new ShootingManager();
        }
        return instance;
    }

    @Override
    public void update() {
        for (Iterator<GameObject> ite = getGOIterator(); ite.hasNext();) {
            GameObject go = ite.next();
            go.update();
            if (go.canDispose()) {
                go.dispose();
                ite.remove();
                friendlies.remove(go);
                enemies.remove(go);
                bullets.remove(go);
            }
        }
        for (GStgBullet bullet : generatingBullets) {
            bullets.add((GStgBullet) addGO(bullet));
        }
        generatingBullets.clear();
    }

    public void shoot(GStgBullet bullet) {
        generatingBullets.add(bullet);
    }

    public List<GStgCharacter> getFriendries() {
        return friendlies;
    }

    public List<GStgCharacter> getEnemies() {
        return enemies;
    }
}
