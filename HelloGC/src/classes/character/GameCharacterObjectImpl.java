package classes.character;

import static common.Commons.*;
import static common.CommonMethod.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Point;

import texture.Texture;

public abstract class GameCharacterObjectImpl implements GameCharacterObject {
	private boolean disposed = false;
	private boolean canDispose = false;
	private int disposeTimer = -1;
	private boolean enable = true;
	private boolean visible = true;

	private GameCharacterBasePoint basePoint = GameCharacterBasePoint.CENTER;
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
	private GameCharacterMoveMode xMoveMode = GameCharacterMoveMode.UNLIMITED;
	private GameCharacterMoveMode yMoveMode = GameCharacterMoveMode.UNLIMITED;

	@Override
	public void update() {
		inputProcess();

		if (disposeTimer > 0) {
			disposeTimer--;
		}
		if (disposeTimer == 0) {
			setDispose();
		}
		if (!isEnable()) {
			return;
		}

		setScale(getScale() + getVScale());
		setAngle(getAngle() + getVAngle());
		setAlpha(getAlpha() + getVAlpha());
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
	public void inputProcess() {
		// 必要に応じてオーバーライドする
	}

	@Override
	public void move() {
		x = xMoveMode.move(WIDTH, width, x, vx);
		if (xMoveMode == GameCharacterMoveMode.DISPOSE_WITH_FADEOUT
				&& (x + width / 2 < 0 || x - width / 2 > WIDTH)) {
			setDispose();
		}

		y = yMoveMode.move(HEIGHT, height, y, vy);
		if (yMoveMode == GameCharacterMoveMode.DISPOSE_WITH_FADEOUT
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
		disposed = true;
		if (getTexture() != null) {
			getTexture().dispose();
			setTexture(null);
		}
	}
	
	@Override
	public boolean isDisposed() {
		return disposed;
	}

	@Override
	public boolean canDispose() {
		return canDispose;
	}

	@Override
	public void draw() {
		if (!isVisible()) {
			return;
		}
		int x = getBasePoint().getX(getPixcelX(), getWidth());
		int y = getBasePoint().getY(getPixcelY(), getHeight());

		glLoadIdentity();
		glTranslatef(x, y, 0);
		glRotatef(getAngle(), 0, 0, 1);
		setGlColor4f(getColor(), getAlpha());

		if (getTexture() != null) {
			drawTexture(getTexture(), getWidth(), getHeight());
		}
	}

	@Override
	public Texture getTexture() {
		return texture;
	}

	@Override
	public GameCharacterObject setTexture(Texture texture) {
		this.texture = texture;
		return this;
	}

	@Override
	public GameCharacterBasePoint getBasePoint() {
		return basePoint;
	}

	@Override
	public GameCharacterObject setBasePont(GameCharacterBasePoint basePoint) {
		this.basePoint = basePoint;
		return this;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public int getPixcelX() {
		return (int) x;
	}

	@Override
	public GameCharacterObject setX(float x) {
		this.x = x;
		return this;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public int getPixcelY() {
		return (int) y;
	}

	@Override
	public GameCharacterObject setY(float y) {
		this.y = y;
		return this;
	}

	@Override
	public float getVX() {
		return vx;
	}

	@Override
	public GameCharacterObject setVx(float vx) {
		this.vx = vx;
		return this;
	}

	@Override
	public float getVY() {
		return vy;
	}

	@Override
	public GameCharacterObject setVy(float vy) {
		this.vy = vy;
		return this;
	}

	@Override
	public int getWidth() {
		return (int) (width * scale);
	}

	@Override
	public GameCharacterObject setWidth(int width) {
		assert (width % 2 == 0) : "widthは偶数でなくてはならない";
		this.width = width;
		return this;
	}

	@Override
	public int getHeight() {
		return (int) (height * scale);
	}

	@Override
	public GameCharacterObject setHeight(int height) {
		assert (height % 2 == 0) : "heightは偶数でなくてはならない";
		this.height = height;
		return this;
	}

	@Override
	public float getScale() {
		return scale;
	}

	@Override
	public GameCharacterObject setScale(float scale) {
		this.scale = scale;
		return this;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public GameCharacterObject setAngle(float angle) {
		this.angle = angle;
		return this;
	}

	@Override
	public float getAlpha() {
		return alpha;
	}

	@Override
	public GameCharacterObject setAlpha(float alpha) {
		this.alpha = alpha;
		return this;
	}

	@Override
	public float getVScale() {
		return vScale;
	}

	@Override
	public GameCharacterObject setVScale(float vScale) {
		this.vScale = vScale;
		return this;
	}

	@Override
	public float getVAngle() {
		return vAngle;
	}

	@Override
	public GameCharacterObject setVAngle(float vAngle) {
		this.vAngle = vAngle;
		return this;
	}

	@Override
	public float getVAlpha() {
		return vAlpha;
	}

	@Override
	public GameCharacterObject setVAlpha(float vAlpha) {
		if (vAlpha > 1f) {
			vAlpha = 1f;
		}
		if (vAlpha < 0f) {
			vAlpha = 0f;
		}
		this.vAlpha = vAlpha;
		return this;
	}

	@Override
	public GameCharacterObject setMoveModeX(GameCharacterMoveMode moveMode) {
		this.xMoveMode = moveMode;
		return this;
	}

	@Override
	public GameCharacterObject setMoveModeY(GameCharacterMoveMode moveMode) {
		this.yMoveMode = moveMode;
		return this;
	}

	@Override
	public void setDispose() {
		canDispose = true;
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
	public GameCharacterObject show() {
		visible = true;
		return this;
	}

	@Override
	public GameCharacterObject hide() {
		visible = false;
		return this;
	}

	@Override
	public GameCharacterObject toggleVisible() {
		visible = !visible;
		return this;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void enable() {
		enable = true;
	}

	@Override
	public void disable() {
		enable = false;
		hide();
		setVx(0);
		setVy(0);
		setVAlpha(0);
		setVScale(0);
	}

	@Override
	public boolean isEnable() {
		return enable;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public GameCharacterObject setColor(Color color) {
		this.color = color;
		return this;
	}

	@Override
	public boolean checkHit(GameCharacterObject target) {
		if (!isEnable()) {
			return false;
		}
		Point selfP1 = new Point(getPixcelX() - getWidth() / 2, getPixcelY()
				+ getHeight() / 2);
		Point selfP2 = new Point(selfP1.x + getWidth(), selfP1.y);
		Point selfP3 = new Point(selfP1.x, selfP1.y - getHeight());

		Point targP1 = new Point(target.getPixcelX() - target.getWidth() / 2,
				target.getPixcelY() + target.getHeight() / 2);
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
