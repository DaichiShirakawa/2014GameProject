package gobject.hello.shooting;

import gobject.GameObject;
import gobject.GameUnitManager;
import gobject.hello.shooting.bullet.GStgBullet;

import java.util.ArrayList;
import java.util.List;

public final class ShootingLogic extends GameUnitManager {
    private static ShootingLogic instance;
    private List<GStgCharacter> friendlies;
    private List<GStgCharacter> enemies;
    private List<GStgCharacter> bullets;

    @SuppressWarnings("unchecked")
    private ShootingLogic() {
        bullets = addControlList(new ArrayList<GStgBullet>());
        friendlies = addControlList(new ArrayList<GStgCharacter>());
        enemies = addControlList(new ArrayList<GStgCharacter>());
        friendlies.add(new MyShip());
        enemies.add(new EnemyShip());
    }

    public static ShootingLogic getInstance() {
        if (instance == null) {
            instance = new ShootingLogic();
        }
        return instance;
    }

    public void shoot(GStgBullet bullet) {
        bullets.add(bullet);
    }

    @Override
    public void update() {
        for (List<GameObject> list : getControlLists()) {
            for (int i = 0; i < list.size(); i++) {
                GameObject go = list.get(i);
                go.update();
                if (go.canDispose()) {
                    go.dispose();
                    list.remove(i);
                }
            }
        }
    }

    @Override
    public void render() {
        for (List<GameObject> list : getControlLists()) {
            for (GameObject go : list) {
                go.render();
            }
        }
    }

    @Override
    public void dispose() {
        for (List<GameObject> list : getControlLists()) {
            for (GameObject go : list) {
                go.dispose();
            }
        }
    }

    public List<GStgCharacter> getFriendries() {
        return friendlies;
    }

    public List<GStgCharacter> getEnemies() {
        return enemies;
    }
}
