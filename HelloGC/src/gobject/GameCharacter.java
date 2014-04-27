package gobject;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Point;

import texture.Texture;

public abstract class GameCharacter implements GameObject {
    private boolean disposeFlag = false;
    private int disposeTimer = -1;
    private boolean enableFlag = true;
    private boolean visibleFlag = true;
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
    private MOVEMODE xMoveMode = MOVEMODE.UNLIMITED;
    private MOVEMODE yMoveMode = MOVEMODE.UNLIMITED;

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

    protected void move() {
        x = xMoveMode.move(WIDTH, width, x, vx);
        if (xMoveMode == MOVEMODE.DISPOSE_WITH_FADEOUT
                && (x + width / 2 < 0 || x - width / 2 > WIDTH)) {
            setDispose();
        }

        y = yMoveMode.move(HEIGHT, height, y, vy);
        if (yMoveMode == MOVEMODE.DISPOSE_WITH_FADEOUT
                && (y + height / 2 < 0 || y - height / 2 > HEIGHT)) {
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
                if (0 < p) {
                    p = 0;
                }
                if (displayBorder > p) {
                    p = displayBorder;
                }
                return p;
            }
        },
        DISPLAY_INNER {
            @Override
            public float move(int displayBorder, int characterSize, float p,
                    float vp) {
                p += vp;
                if (p - characterSize / 2 < 0) {
                    p = characterSize / 2;
                }
                if (p + characterSize / 2 > displayBorder) {
                    p = displayBorder - characterSize / 2;
                }
                return p;
            }
        },
        REFRECTION {
            @Override
            public float move(int displayBorder, int characterSize, float p,
                    final float vp) {
                p += vp;
                if (p < 0) {
                    p = -p;
                }
                if (p > displayBorder) {
                    p = displayBorder - p + displayBorder;
                }
                return p;
            }
        },
        LOOP {
            @Override
            public float move(int displayBorder, int characterSize, float p,
                    float vp) {
                p += vp;
                if (p + characterSize / 2 < 0) {
                    p = displayBorder - p;
                } else if (p - characterSize / 2 > displayBorder) {
                    p = displayBorder - p;
                }
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

    protected void draw(final Texture texture) {
        if (!isVisible()) {
            return;
        }
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

    public Texture getTexture() {
        return texture;
    }

    protected void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getX() {
        return (int) x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public int getWidth() {
        return (int) (width * scale);
    }

    public void setWidth(int width) {
        assert width % 2 == 0 : "widthは偶数でなくてはならない";
        this.width = width;
    }

    public int getHeight() {
        return (int) (height * scale);
    }

    public void setHeight(int height) {
        assert height % 2 == 0 : "heightは偶数でなくてはならない";
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getvScale() {
        return vScale;
    }

    public void setvScale(float vScale) {
        this.vScale = vScale;
    }

    public float getvAngle() {
        return vAngle;
    }

    public void setvAngle(float vAngle) {
        this.vAngle = vAngle;
    }

    public float getvAlpha() {
        return vAlpha;
    }

    public void setvAlpha(float vAlpha) {
        if (vAlpha > 1f) {
            vAlpha = 1f;
        }
        if (vAlpha < 0f) {
            vAlpha = 0f;
        }
        this.vAlpha = vAlpha;
    }

    public void setXMoveMode(MOVEMODE moveMode) {
        this.xMoveMode = moveMode;
    }

    public void setYMoveMode(MOVEMODE moveMode) {
        this.yMoveMode = moveMode;
    }

    public void setDispose() {
        disposeFlag = true;
        disable();
    }

    /**
     * seconds秒経過後に破棄する。いまのところ1/FPS秒の精度
     */
    public void setDisposeTimer(float seconds) {
        disposeTimer = (int) (FPS * seconds);
    }

    public int getDisposeTimer() {
        return disposeTimer;
    }

    public void show() {
        visibleFlag = true;
    }

    public void hide() {
        visibleFlag = false;
    }

    protected boolean isVisible() {
        return visibleFlag;
    }

    public void enable() {
        enableFlag = true;
    }

    public void disable() {
        enableFlag = false;
        hide();
    }

    protected boolean isEnable() {
        return enableFlag;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
            if (selfP3.y <= targP1.y && selfP1.y >= targP3.y) {
                return true;
            }
        }
        return false;
    }
}
