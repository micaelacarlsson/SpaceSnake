package model.objects;

import java.util.ArrayList;

import model.IGravity;
import model.Moveable;
import model.WorldCollection;
import model.WorldObject;
import util.Vector2D;

/**
 * An non movable object with gravity
 * @author Gustav
 *
 */

public class BlackHole
extends WorldObject
implements IGravity
{

	private double gravity_constant = 1;
	
	public BlackHole(double xPos, double yPos, double mass, double radius) {
		this(new Vector2D(xPos, yPos), mass, radius);
	}
	
	public BlackHole(Vector2D position, double mass, double radius) {
		super(position, mass, radius);
	}

	@Override
	public double getGravity() {
		return gravity_constant;
	}

	@Override
	public void gravityPull(ArrayList<WorldObject> data, double dT) {
		for(WorldObject obj : data){
			if(obj instanceof Moveable)
			{
				Vector2D distance = position.sub(obj.getPosition());
				((Moveable)obj).accelerate(distance.normalize().scale(gravity_constant*mass*obj.getMass()/distance.lengthsquared()), dT);
			}
		}
	}


}