package gobject.scene.shooting;

import static common.CommonMethod.*;
import gobject.GameObject;
import gobject.scene.GameSceneImpl;
import gobject.scene.shooting.bullet.GStgBullet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ShootingScene extends GameSceneImpl {
    private static ShootingScene instance;
    private List<GStgCharacter> friendlies = new ArrayList<GStgCharacter>();
    private List<GStgCharacter> enemies = new ArrayList<GStgCharacter>();
    private List<GStgBullet> bullets = new ArrayList<GStgBullet>();
    private List<GStgBullet> generatingBullets = new ArrayList<GStgBullet>();

    private ShootingScene() {
        BackGroundColor.WHITE.set();
        friendlies.add((GStgCharacter) add(new MyShip()));
        enemies.add((GStgCharacter) add(new EnemyShip()));
    }

    public static ShootingScene getInstance() {
        if (instance == null) {
            instance = new ShootingScene();
        }
        return instance;
    }

    @Override
    public void update() {
        for (Iterator<GameObject> ite = iterator(); ite.hasNext();) {
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
            bullets.add((GStgBullet) add(bullet));
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
