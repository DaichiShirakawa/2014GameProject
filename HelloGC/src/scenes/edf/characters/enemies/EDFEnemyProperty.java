package scenes.edf.characters.enemies;

import texture.Texture;

public class EDFEnemyProperty {
	public final int power;
	public final int hp;
	public final int size;
	public final Texture texture;
	public final float rotateSpeed;
	public final float fallSpeed;
	public final int money;

	public EDFEnemyProperty(int power, int hp, int size, Texture texture,
			float rotateSpeed, float fallSpeed, int money) {
		super();
		this.power = power;
		this.hp = hp;
		this.size = size;
		this.texture = texture;
		this.rotateSpeed = rotateSpeed;
		this.fallSpeed = fallSpeed;
		this.money = money;
	}
}
