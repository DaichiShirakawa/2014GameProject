package gobject.character.shooting.bullets;

import gobject.character.shooting.ShootingCharacter;

public class BasicBullet extends ShootingBulletCharacter{

	public BasicBullet(ShootingCharacter shooter) {
		super(shooter);
		setDivision(shooter.getDivision());
		
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void hitEffect(ShootingCharacter target) {
		
	}

}
