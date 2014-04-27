package gobject.hello.shooting.bullet;

import static common.CommonLogic.*;
import static common.Commons.*;
import static java.lang.Math.*;
import gobject.hello.shooting.GStgCharacter;
import gobject.hello.shooting.ShootingLogic;

import java.util.List;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class NormalBullet extends GStgBullet {
    private static final int BULLET_SIZE = 10;
    private static final int RANGE = 200;

    private float startX;
    private float startY;
    private float ySpeed = 5;
    private static final Texture TEXTURE = new TextureLoader()
            .loadTexture(IMAGE_FOLDER_STRING + "flower.png");
    private GStgCharacter target = null;

    public NormalBullet(GStgCharacter shooter) {
        super();
        setDivision(DIVISION.FRIENDLY);
        startX = shooter.getX();
        startY = shooter.getY();
        setX(startX);
        setY(startY);
        setColor(generateColorCosmos());
        setTexture(TEXTURE);
        AlphaBlend.AlphaBlend.config(getTexture());
        setWidth(BULLET_SIZE);
        setHeight(BULLET_SIZE);

        setVx(random(-0.5f, 0.5f));
        setVy(ySpeed);
        setvAngle(12);
    }

    @Override
    public void update() {
        if (!isEnable()) {
            if (target != null && getDisposeTimer() % (FPS / 2) == 0) {
                ShootingLogic.getInstance().shoot(new Effect(target));
            }
            super.update();
            return;
        }
        if (sqrt(pow((float) (startX - getX()), 2f)
                + pow((float) (startY - getY()), 2f)) > RANGE) {
            setDispose();
        }
        super.update();
        checkHit();
    }

    @Override
    public void render() {
        setGlColor4f(getColor(), getAlpha());
        super.render();
    }

    protected void checkHit() {
        List<GStgCharacter> list = null;
        switch (getDivision()) {
        case FRIENDLY:
            list = ShootingLogic.getInstance().getEnemies();
            break;
        case ENEMY:
            list = ShootingLogic.getInstance().getFriendries();
            break;
        default:
            return;
        }

        for (GStgCharacter target : list) {
            if (checkHit(target)) {
                hitEffect(target);
            }
        }
    }

    @Override
    protected void hitEffect(GStgCharacter target) {
        for (int i = 0; i < 3; i++) {
            ShootingLogic.getInstance().shoot(new Effect(this));
        }
        setDisposeTimer(1);
        this.target = target;
        disable();
        target.damage();
    }

    protected class Effect extends GStgBullet {
        protected Effect(GStgCharacter target) {
            setX(target.getX());
            setY(target.getY());
            setTexture(target.getTexture());
            setWidth(target.getWidth() / 3);
            setHeight(target.getHeight() / 3);
            setScale(random(0.5f, 2f));
            setColor(target.getColor());

            float tmp = RND.nextInt(361);
            setVx(4 * (float) sin(tmp) * random(0.2f, 1f));
            setVy(4 * (float) cos(tmp) * random(0.2f, 1f));
            setAngle(tmp);
            setDisposeTimer(0.8f * random(0.5f, 1.5f));
        }

        @Override
        public void update() {
            setVx(getVx() * 0.95f);
            setVy(getVy() * 0.95f);
            super.update();
        }

        @Override
        public void render() {
            setGlColor4f(getColor(), getAlpha());
            super.render();
        }

        @Override
        protected void checkHit() {
            return;
        }

        @Override
        protected void hitEffect(GStgCharacter target) {
            return;
        }

    }
}
