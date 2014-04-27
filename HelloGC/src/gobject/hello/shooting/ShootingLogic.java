package gobject.hello.shooting;

import gobject.GameObject;
import gobject.GameUnitManager;
import gobject.hello.shooting.bullet.GStgBullet;

import java.util.ArrayList;
import java.util.List;

public final class ShootingLogic extends GameUnitManager {
    private static ShootingLogic instance_;
    private List<GStgCharacter> friendlies_;
    private List<GStgCharacter> enemies_;
    private List<GStgCharacter> bullets_;

    @SuppressWarnings("unchecked")
    private ShootingLogic() {
        bullets_ = addControlList(new ArrayList<GStgBullet>());
        friendlies_ = addControlList(new ArrayList<GStgCharacter>());
        enemies_ = addControlList(new ArrayList<GStgCharacter>());
        friendlies_.add(new MyShip());
        enemies_.add(new EnemyShip());
    }

    public static ShootingLogic getInstance() {
        if (instance_ == null) {
            instance_ = new ShootingLogic();
        }
        return instance_;
    }

    public void shoot(GStgBullet bullet) {
        bullets_.add(bullet);
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
        return friendlies_;
    }

    public List<GStgCharacter> getEnemies() {
        return enemies_;
    }
}
