package gobject.character.ball;

import static common.Commons.*;
import io.Key;

import java.awt.Color;
import java.util.Random;

public class BallGravity extends Ball {
    private static final double VG = 0.25;
    protected static final double ACCELE = 0.5;
    protected static final double Y_HANSYA = 0.8;
    protected static final double X_KOROGARI = 0.985;
    protected static final double X_HANSYA = 0.6;
    protected static final double SIKII = 1;

    public BallGravity(int x, int y, int vx, int vy, int size) {
        super(x, y, vx, vy, size);
        Random rnd = new Random();
        this.color = new Color(rnd.nextInt(256), rnd.nextInt(256),
                rnd.nextInt(256));
    }

    public void move() {
		if (Key.LEFT.isPressing()) {
            vx -= ACCELE;
        }
        if (Key.RIGHT.isPressing()) {
            vx += ACCELE;
        }
        if ((y + size == HEIGHT) && (vy < SIKII)
                && (Key.SPACE.isPressing())) {
            vy -= new Random().nextInt(20);
        }
        x += vx;
        if (x < 0 || x + size > WIDTH) {
            vx = -vx;
            vx *= X_HANSYA;
            pong.play();
            if (x < 0) {
                x = -x;
            }
            if (x + size > WIDTH) {
                x -= x + size - (WIDTH);
            }
        }

        vy += VG;
        y += vy;
        if (y < 0 || y + size > HEIGHT) {
            vy = -vy;
            vy *= Y_HANSYA;
            vx *= X_KOROGARI;
            if (y < 0) {
                y = -y;
                pong.play();
            }
            if (y + size > HEIGHT) {
                y -= y + size - (HEIGHT);
                if (Math.abs(vy) > SIKII) {
                    pong.play();
                }
            }
        }
    }
}
