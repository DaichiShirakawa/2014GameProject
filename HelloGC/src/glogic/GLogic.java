package glogic;

import gobject.GameObject;
import gobject.GameScene;

import java.util.ArrayList;
import java.util.List;

public class GLogic implements GameObject {
    private List<GameScene> units = new ArrayList<>();

    public GLogic() {
        units.add(new SceneManager());
        //        units.add(new FlowerStormManager());
        //        units.add(new DotTest());
        //                units.add(new TextTest());
        //        units.add(new SolarSystemManager());
        //        units.add(ShootingManager.getInstance());
    }

    @Override
    public void update() {
        for (GameScene unit : units) {
            unit.update();
        }
    }

    @Override
    public void render() {
        for (GameScene unit : units) {
            unit.render();
        }
    }

    public void dispose() {
        for (GameScene unit : units) {
            unit.dispose();
        }
    }

    @Override
    public boolean canDispose() {
        return false;
    }
}
