package gobject.character.shooting.bullets;

import gobject.character.shooting.ShootingCharacter;

public abstract class ShootingBulletCharacter extends ShootingCharacter {
	private ShootingCharacter shooter = null;
	private ShootingCharacter target = null;
	
    public ShootingBulletCharacter(ShootingCharacter shooter) {
		super(null);
		this.shooter = shooter;
		setDivision(shooter.getDivision());
	}
    
    public ShootingCharacter getParentCharacter() {
    	return getShooter();
    }

	@Override
    public void dispose() {
        return;
    }
	
    abstract public void hitEffect(ShootingCharacter target);

	public ShootingCharacter getShooter() {
		return shooter;
	}

	public ShootingCharacter getTarget() {
		return target;
	}

	public void setTarget(ShootingCharacter target) {
		this.target = target;
	}
}
