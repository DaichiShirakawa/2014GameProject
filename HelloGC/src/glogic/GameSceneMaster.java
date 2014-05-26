package glogic;

import static common.Commons.*;
import gobject.GameScene;
import gobject.hello.TextTest;
import gobject.hello.dottest.DotTest;
import gobject.hello.shooting.ShootingManager;
import gobject.hello.solarsystem.SolarSystemManager;
import gobject.weather.FlowerStormManager;

public class GameSceneMaster extends GameScene {
    GameScene currentScene;

    public GameSceneMaster() {
        currentScene = new TitleScene();
    }

    @Override
    public void update() {
        sceneScan();
        currentScene.update();
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
    }

    private void changeScene(GameScene scene) {
        if (this.currentScene != null) {
            this.currentScene.dispose();
        }
        this.currentScene = scene;
    }

    @Override
    public void render() {
        currentScene.render();
    }

}
