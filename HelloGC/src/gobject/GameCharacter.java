package gobject;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Point;
import java.util.EnumSet;

import texture.Texture;

public abstract class GameCharacter implements GameObject {
	private boolean disposeFlag_ = false;
	private float x_;
	private float vx_;
	private float y_;
	private float vy_;
	private int width_;
	private int height_;
	private float scale_ = 1;
	private float vScale_ = 0;
	private float angle_ = 0;
	private float vAngle_ = 0;
	private float alpha_ = 1;
	private float vAlpha_ = 0;
	private Color color_;
	private Texture texture_;
	private MOVEMODE xMoveMode_ = MOVEMODE.UNLIMITED;
	private MOVEMODE yMoveMode_ = MOVEMODE.UNLIMITED;

	// if(keyboard.isPress(KEY_UP)){
	// }
	// if(keyboard.isPress(KEY_DOWN)) {
	// }
	// if(keyboard.isPress(KEY_LEFT)) {
	// }
	// if(keyboard.isPress(KEY_RIGHT)) {
	// }
	// if(keyboard.isPress(KEY_SPACE)) {
	// }

	@Override
	public void update() {
		scale_ += vScale_;
		angle_ += vAngle_;
		alpha_ += vAlpha_;
		if (alpha_ > 1f) {
			alpha_ = 1f;
			vAlpha_ = 0f;
		}
		if (alpha_ < 0f) {
			alpha_ = 0f;
			vAlpha_ = 0f;
		}
		move();
	}

	@Override
	public abstract void render();

	@Override
	public void dispose() {
		if (getTexture() != null)
			getTexture().dispose();
	}

	@Override
	public boolean canDispose() {
		return disposeFlag_;
	}

	protected void move() {
		x_ = xMoveMode_.move(WIDTH, width_, x_, vx_);
		if (xMoveMode_ == MOVEMODE.DISPOSE_WITH_FADEOUT
				&& (x_ + width_ / 2 < 0 || x_ - width_ / 2 > WIDTH)) {
			setDispose();
		}

		y_ = yMoveMode_.move(HEIGHT, height_, y_, vy_);
		if (yMoveMode_ == MOVEMODE.DISPOSE_WITH_FADEOUT
				&& (y_ + height_ / 2 < 0 || y_ - height_ / 2 > HEIGHT)) {
			setDispose();
		}
	}

	/**
	 ** 移動モード
	 * 自由
	 * 半分はみ出る
	 * はみ出ない
	 * 外枠で反射
	 * ループ
	 * 全部はみ出たらdispose
	 */
	protected enum MOVEMODE {
		UNLIMITED {
			@Override
			public float move(int displayBorder, int characterSize, float p,
					float vp) {
				return p + vp;
			}
		},
		DISPLAY_BORDER {
			@Override
			public float move(int displayBorder, int characterSize, float p,
					float vp) {
				p += vp;
				if (0 < p)
					p = 0;
				if (displayBorder > p)
					p = displayBorder;
				return p;
			}
		},
		DISPLAY_INNER {
			@Override
			public float move(int displayBorder, int characterSize, float p,
					float vp) {
				p += vp;
				if (p - characterSize / 2 < 0)
					p = characterSize / 2;
				if (p + characterSize / 2 > displayBorder)
					p = displayBorder - characterSize / 2;
				return p;
			}
		},
		REFRECTION {
			@Override
			public float move(int displayBorder, int characterSize, float p,
					float vp) {
				p += vp;
				if (p < 0)
					p = -p;
				if (p > displayBorder)
					p = displayBorder - p + displayBorder;
				return p;
			}
		},
		LOOP {
			@Override
			public float move(int displayBorder, int characterSize, float p,
					float vp) {
				p += vp;
				if (p + characterSize / 2 < 0)
					p = displayBorder - p;
				if (p - characterSize / 2 > displayBorder)
					p = -p + displayBorder;
				return p;
			}
		},
		DISPOSE_WITH_FADEOUT {
			@Override
			public float move(int displayBorder, int characterSize, float p,
					float vp) {
				return p + vp;
			}
		};
		public abstract float move(int displayBorder, int characterSize,
				float p, float vp);
	}

	protected void draw(final Texture texture) {
		// 設定を初期化する
		glLoadIdentity();
		// 原点の指定
		glTranslatef(getX(), getY(), 0);
		// 回転
		glRotatef(getAngle(), 0, 0, 1);

		drawTexture(texture, getWidth(), getHeight());
	}

	protected void draw() {
		draw(getTexture());
	}

	protected Texture getTexture() {
		return texture_;
	}

	protected void setTexture(Texture texture) {
		this.texture_ = texture;
	}

	public int getX() {
		return (int) x_;
	}

	public void setX(float x) {
		this.x_ = x;
	}

	public int getY() {
		return (int) y_;
	}

	public void setY(float y) {
		this.y_ = y;
	}

	public float getVx() {
		return vx_;
	}

	public void setVx(float vx) {
		this.vx_ = vx;
	}

	public float getVy() {
		return vy_;
	}

	public void setVy(float vy) {
		this.vy_ = vy;
	}

	public int getWidth() {
		return (int) (width_ * scale_);
	}

	public void setWidth(int width) {
		assert width % 2 == 0 : "widthは偶数でなくてはならない";
		this.width_ = width;
	}

	public int getHeight() {
		return (int) (height_ * scale_);
	}

	public void setHeight(int height) {
		assert height % 2 == 0 : "heightは偶数でなくてはならない";
		this.height_ = height;
	}

	public float getScale() {
		return scale_;
	}

	public void setScale(float scale) {
		this.scale_ = scale;
	}

	public float getAngle() {
		return angle_;
	}

	public void setAngle(float angle) {
		this.angle_ = angle;
	}

	public float getAlpha() {
		return alpha_;
	}

	public void setAlpha(float alpha) {
		this.alpha_ = alpha;
	}

	public float getvScale() {
		return vScale_;
	}

	public void setvScale(float vScale) {
		this.vScale_ = vScale;
	}

	public float getvAngle() {
		return vAngle_;
	}

	public void setvAngle(float vAngle) {
		this.vAngle_ = vAngle;
	}

	public float getvAlpha() {
		return vAlpha_;
	}

	public void setvAlpha(float vAlpha) {
		if (vAlpha > 1f)
			vAlpha = 1f;
		if (vAlpha < 0f)
			vAlpha = 0f;
		this.vAlpha_ = vAlpha;
	}

	public void setXMoveMode(MOVEMODE moveMode) {
		this.xMoveMode_ = moveMode;
	}

	public void setYMoveMode(MOVEMODE moveMode) {
		this.yMoveMode_ = moveMode;
	}

	public void setDispose() {
		disposeFlag_ = true;
	}

	public Color getColor() {
		return color_;
	}

	public void setColor(Color color_) {
		this.color_ = color_;
	}

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
			if (selfP3.y >= targP1.y && selfP1.y <= targP3.y) {
				return true;
			}
		}
		return false;
	}
}
