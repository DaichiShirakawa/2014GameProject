package scenes.testball;

import static common.Commons.*;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {
    // ボールの大きさ
    protected int size;
    // ボールの位置 (x, y) 円の左上の座標
    protected double x, y;
    // ボールの速度 (vx, vy)
    protected double vx, vy;
    protected AudioClip pong;
    // ボールの色
    protected Color color;
    protected static AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);

    // コンストラクタ（新しいボールオブジェクトを作る工場）
    public Ball(int x, int y, int vx, int vy, int size) {
        // ボールの属性を設定
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.size = size;
        this.pong = Applet.newAudioClip(getClass().getResource("../mediaResources/pong.wav"));
        this.color = Color.blue;
    }

    public void move() {
        // ボールを速度分だけ移動させる
        x += vx;
        y += vy;

        // 左または右に当たったらx方向速度の符号を反転させる
        if (x < 0 || x + size > WIDTH) {
            vx = -vx;
            pong.play();
            if (x < 0)
                x = -x;
            if (x + size > WIDTH)
                x -= x + size - WIDTH;
        }

        // 上または下に当たったらy方向速度の符号を反転させる
        if (y < 0 || y + size > HEIGHT) {
            vy = -vy;
            pong.play();
            if (y < 0)
                y = -y;
            if (y + size > HEIGHT)
                y -= y + size - HEIGHT;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Composite curComp = g2.getComposite();
        g2.setComposite(composite);
        g2.setColor(color);
        g2.fillOval((int) x, (int) y, size, size);
        g2.setComposite(curComp);
    }
}
