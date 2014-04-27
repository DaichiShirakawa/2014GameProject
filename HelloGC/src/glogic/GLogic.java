package glogic;

import gobject.GameObject;
import gobject.GameUnitManager;
import gobject.hello.galaxy.SolarSystem;

import java.util.ArrayList;
import java.util.List;

public class GLogic implements GameObject {
    private List<GameUnitManager> units = new ArrayList<>();

    public GLogic() {
        //        units.add(new Flowers());
        //        units.add(new DotTest());
        //        units.add(new TextTest());
        units.add(new SolarSystem());
        //        units_.add(ShootingLogic.GetInstance());
    }

    @Override
    public void update() {
        for (GameUnitManager unit : units) {
            unit.update();
        }
    }

    @Override
    public void render() {
        for (GameUnitManager unit : units) {
            unit.render();
        }
    }

    public void dispose() {
        for (GameUnitManager unit : units) {
            unit.dispose();
        }
    }

    @Override
    public boolean canDispose() {
        return false;
    }
}
