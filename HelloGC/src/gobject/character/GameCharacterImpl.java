package gobject.character;

import static common.CommonMethod.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Point;

import texture.Texture;

public abstract class GameCharacterImpl implements GameCharacter {
	private boolean disposeFlag = false;
	private int disposeTimer = -1;
	private boolean enableFlag = true;
	private boolean visible = true;

	private float x;
	private float vx;
	private float y;
	private float vy;
	private int width;
	private int height;
	private float scale = 1;
	private float vScale = 0;
	private float angle = 0;
	private float vAngle = 0;
	private float alpha = 1;
	private float vAlpha = 0;

	private Color color;
	private Texture texture;
	private MoveMode xMoveMode = MoveMode.UNLIMITED;
	private MoveMode yMoveMode = MoveMode.UNLIMITED;

	@Override
	public void update() {
		if (disposeTimer > 0) {
			disposeTimer--;
		}
		if (disposeTimer == 0) {
			setDispose();
		}
		if (!isEnable()) {
			return;
		}

		scale += vScale;
		angle += vAngle;
		alpha += vAlpha;
		if (alpha > 1f) {
			alpha = 1f;
			vAlpha = 0f;
		}
		if (alpha < 0f) {
			alpha = 0f;
			vAlpha = 0f;
		}
		move();
	}

	@Override
	public void move() {
		x = xMoveMode.move(WIDTH, width, x, vx);
		if (xMoveMode == MoveMode.DISPOSE_WITH_FADEOUT
				&& (x + width / 2 < 0 || x - width / 2 > WIDTH)) {
			setDispose();
		}

		y = yMoveMode.move(HEIGHT, height, y, vy);
		if (yMoveMode == MoveMode.DISPOSE_WITH_FADEOUT
				&& (y + height / 2 < 0 || y - height / 2 > HEIGHT)) {
			setDispose();
		}
	}

	@Override
	public void render() {
		if (!isEnable()) {
			return;
		}
		draw();
	}

	@Override
	public void dispose() {
		if (getTexture() != null) {
			getTexture().dispose();
		}
	}

	@Override
	public boolean canDispose() {
		return disposeFlag;
	}

	@Override
	public void draw(final Texture texture, Color color, float alpha) {
		if (!isVisible()) {
			return;
		}
		glLoadIdentity();
		glTranslatef(getX(), getY(), 0);
		glRotatef(getAngle(), 0, 0, 1);
		setGlColor4f(color, alpha);

		drawTexture(texture, getWidth(), getHeight());
	}

	@Override
	public void draw() {
		draw(getTexture(), getColor(), getAlpha());
	}

	@Override
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public int getX() {
		return (int) x;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return (int) y;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getVx() {
		return vx;
	}

	@Override
	public void setVx(float vx) {
		this.vx = vx;
	}

	@Override
	public float getVy() {
		return vy;
	}

	@Override
	public void setVy(float vy) {
		this.vy = vy;
	}

	@Override
	public int getWidth() {
		return (int) (width * scale);
	}

	@Override
	public void setWidth(int width) {
		assert width % 2 == 0 : "widthは偶数でなくてはならない";
		this.width = width;
	}

	@Override
	public int getHeight() {
		return (int) (height * scale);
	}

	@Override
	public void setHeight(int height) {
		assert height % 2 == 0 : "heightは偶数でなくてはならない";
		this.height = height;
	}

	@Override
	public float getScale() {
		return scale;
	}

	@Override
	public void setScale(float scale) {
		this.scale = scale;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public float getAlpha() {
		return alpha;
	}

	@Override
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	@Override
	public float getvScale() {
		return vScale;
	}

	@Override
	public void setvScale(float vScale) {
		this.vScale = vScale;
	}

	@Override
	public float getvAngle() {
		return vAngle;
	}

	@Override
	public void setvAngle(float vAngle) {
		this.vAngle = vAngle;
	}

	@Override
	public float getvAlpha() {
		return vAlpha;
	}

	@Override
	public void setvAlpha(float vAlpha) {
		if (vAlpha > 1f) {
			vAlpha = 1f;
		}
		if (vAlpha < 0f) {
			vAlpha = 0f;
		}
		this.vAlpha = vAlpha;
	}

	@Override
	public void setXMoveMode(MoveMode moveMode) {
		this.xMoveMode = moveMode;
	}

	@Override
	public void setYMoveMode(MoveMode moveMode) {
		this.yMoveMode = moveMode;
	}

	@Override
	public void setDispose() {
		disposeFlag = true;
		disable();
	}

	/**
	 * seconds秒経過後に破棄するタイマーをセットする。
	 */
	@Override
	public void disposeAfter(float seconds) {
		// 現状のロジックでは1/FPS秒程度の誤差がある。
		disposeTimer = (int) (FPS * seconds);
	}

	@Override
	public int getDisposeTimer() {
		return disposeTimer;
	}

	@Override
	public void show() {
		visible = true;
	}

	@Override
	public void hide() {
		visible = false;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void enable() {
		enableFlag = true;
	}

	@Override
	public void disable() {
		enableFlag = false;
		hide();
	}

	@Override
	public boolean isEnable() {
		return enableFlag;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean checkHit(GameCharacter target) {
		Point selfP1 = new Point(getX() - getWidth() / 2, getY() + getHeight()
				/ 2);
		Point selfP2 = new Point(selfP1.x + getWidth(), selfP1.y);
		Point selfP3 = new Point(selfP1.x, selfP1.y - getHeight());

		Point targP1 = new Point(target.getX() - target.getWidth() / 2,
				target.getY() + target.getHeight() / 2);
		Point targP2 = new Point(targP1.x + target.getWidth(), targP1.y);
		Point targP3 = new Point(targP1.x, targP1.y - target.getHeight());

		if (selfP2.x >= targP1.x && selfP1.x <= targP2.x) {
			if (selfP3.y <= targP1.y && selfP1.y >= targP3.y) {
				return true;
			}
		}
		return false;
	}
}
