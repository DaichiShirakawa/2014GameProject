package gobject.character.shooting.edf.weapon;

import static common.CommonMethod.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import com.sun.org.apache.xpath.internal.axes.SelfIteratorNoPredicate;

import texture.text.TextTextureMaker;
import gobject.character.shooting.ShootingCharacter;
import gobject.character.shooting.bullets.BasicBullet;
import gobject.scene.shooting.ShootingScene;

public class BasicWeapon extends ShootingCharacter{
	private ShootingCharacter owner =null;

	public BasicWeapon(ShootingScene scene, ShootingCharacter owner) {
		super(scene);
		this.owner = owner;
		setTexture(TextTextureMaker.createText("↑"));
		setY(10);
		setHeight(40);
		setWidth(20);
		setColor(Color.white);
	}
	
	@Override
	public void draw() {
		// 画面中央から指定方向を向き、TURN_RADIUS距離進んだ位置に描写
		glLoadIdentity();
		glTranslated(CENTER_X, CENTER_Y, 0);
		glRotatef(owner.getAngle(), 0, 0, 1);
		glTranslatef(getX(), owner.getY() + getY(), 0);
		setGlColor4f(getColor(), getAlpha());
		drawTexture(getTexture(), getWidth(), getHeight());
	}

	public void shoot() {
		getParentScene().shoot(this, new BasicBullet(this));
	}

}
