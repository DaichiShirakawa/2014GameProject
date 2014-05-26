package glogic;

import static common.Commons.*;
import gobject.GameScene;
import gobject.hello.TextTest;
import gobject.hello.dottest.DotTest;
import gobject.hello.shooting.ShootingManager;
import gobject.hello.solarsystem.SolarSystemManager;
import gobject.weather.FlowerStormManager;

public class SceneManager extends GameScene {
    GameScene scene;

    public SceneManager() {
        scene = new TitleScene();
    }

    @Override
    public void update() {
        sceneScan();
        scene.update();
    }

    private void sceneScan() {
        if (KEYBOARD.isPressed(KEY_F1)) {
            changeScene(new FlowerStormManager());
        } else if (KEYBOARD.isPressed(KEY_F2)) {
            changeScene(new DotTest());
        } else if (KEYBOARD.isPressed(KEY_F3)) {
            changeScene(new TextTest());
        } else if (KEYBOARD.isPressed(KEY_F4)) {
            changeScene(new SolarSystemManager());
        } else if (KEYBOARD.isPressed(KEY_F5)) {
            changeScene(ShootingManager.getInstance());
        }
        //        units.add(new FlowerStormManager());
        //        units.add(new DotTest());
        //                units.add(new TextTest());
        //        units.add(new SolarSystemManager());
        //        units.add(ShootingManager.getInstance());

    }

    private void changeScene(GameScene scene) {
        if (this.scene != null) {
            this.scene.dispose();
        }
        this.scene = scene;
    }

    @Override
    public void render() {
        scene.render();
    }

}
