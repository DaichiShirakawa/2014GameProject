package glogic;

import gobject.GameObject;
import gobject.GameUnitManager;
import gobject.hello.shooting.ShootingLogic;

import java.util.ArrayList;
import java.util.List;

public class GLogic implements GameObject {
	private List<GameUnitManager> units_ = new ArrayList<>();

	public GLogic() {
//		units_.add(new Flowers());
		// units_.add(new DotTest());
		// units_.add(new TextTest());
		// units_.add(new SolarSystem());
		 units_.add(ShootingLogic.GetInstance());
	}

	@Override
	public void update() {
		for (GameUnitManager unit : units_) {
			unit.update();
		}
	}

	@Override
	public void render() {
		for (GameUnitManager unit : units_) {
			unit.render();
		}
	}

	public void dispose() {
		for (GameUnitManager unit : units_) {
			unit.dispose();
		}
	}

	@Override
	public boolean canDispose() {
		return false;
	}
}
