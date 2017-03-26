package asteroids.model;

import java.lang.Math;

import be.kuleuven.cs.som.annotate.*;
//import be.kuleuven.cs.som.taglet.*;

/**
 * A class for flying a ship in space involving the position, radius, velocity
 * and orientation of the ship. With facilities to move the ship by accelerating
 * and turning and a facility to predict the time and location of it colliding 
 * with an other ship.
 * 
 * @invar	The position of each ship must be a valid position for any ship.
 * 			| isValidPosition(getPosition())
 * @invar	The velocity of each ship must be a valid velocity for any ship.
 * 			| isValidVelocity(getVelocity())
 * @invar	The radius of each ship must be a valid radius for any ship.
 * 			| isValidRadius(getRadius())
 * @invar	The orientation of each ship must be a valid orientation for any ship.
 * 			| isValidOrientation(getOrientation())
 * 
 * @version	1.0
 * @author 	Lucas Desard and Nicolas Desmedt
 * 			
 * Course studies: 2nd Bachelor Engineering: Computer science/Electrical Engineering
 * Code Repository: https://github.com/NicolasDesmedt/RepositoryLucasNicolas
 */
public class Ship {
	
	/**
	 * Initialize this new ship with given position, given velocity, given radius and given orientation.
	 * 
	 * @param 	x
	 * 			The x-coordinate of the position of this new ship (in km).
	 * @param 	y
	 * 			The y-coordinate of the position of this new ship (in km).
	 * @param 	xVelocity
	 * 			The movement per unit time of this new ship in the x direction (in km/s).
	 * @param 	yVelocity
	 * 			The movement per unit time of this new ship in the y direction (in km/s).
	 * @param 	radius
	 * 			The radius of this new circle-shaped ship (in km). 
	 * 			This radius does not change during the program's execution.
	 * @param 	orientation
	 * 			The direction in which this new ship is faced (in radians).
	 * @post	The new position of this new ship is equal to the given position.
	 * 			| new.getPosition() == double[] {x,y}
	 * @post	The new velocity of this new ship is equal to the given velocity.
	 * 			| new.getVelocity() == double[] {xVelocity,yVelocity}
	 * @post	The new radius of this new ship is equal to the given radius.
	 * 			| new.getRadius == radius
	 * @post	The new orientation of this new ship is equal to the given orientation.
	 * 			| new.getOrientation == orientation
	 * @throws 	IllegalArgumentException
	 * 			The given position is not a valid position for a ship.
	 * 			| (! isValidPosition(position))
	 * @throws 	IllegalArgumentException
	 * 			The given radius is not a valid radius for any ship.
	 * 			| (! isValidRadius(radius))
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
		throws IllegalArgumentException{
		this.setPosition(new double[] {x,y});
		this.setVelocity(new double[] {xVelocity,yVelocity});
		this.setOrientation(orientation);
		if (!isValidRadius(radius)) throw new IllegalArgumentException("The given radius isn't a valid one");
		this.radius = radius;
	}
	
	/**
	 * Initialize this new ship in the origin of the axes with zero velocity, with a radius 
	 * set to its lowest possible value and with an orientation facing up at an angle of PI/2.
	 * 
	 * @effect	This new ship is initialized with the x-coordinate, y-coordinate, 
	 * 			xVelocity and yVelocity being zero, the radius being of minimal value minRadius,
	 * 			and the orientation of the ship being PI/2.
	 * 			| this(0,0,0,0,minRadius,(Math.PI/2));
	 */

	public Ship(){
		this(0,0,0,0,minRadius,(Math.PI/2));
	}
	
	/**
	 * Return the position of this ship.
	 * 	The position of a ship locates the ship in an unbounded two-dimensional space.
	 */
	@Basic @Raw
	public double[] getPosition(){
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any ship.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return True if and only if the position consists of a double containing two real numbers who are not infinite.
	 *         | result == ( (!Double.isNaN(position[0])) && (!Double.isNaN(position[1])) &&
	 *         |			 (!Double.isInfinite(position[0])) && (!Double.isInfinite(position[0])) && (position.length == 2) )
	 */
	public static boolean isValidPosition(double[] position){
		if ( (!Double.isNaN(position[0])) && (!Double.isNaN(position[1])) && 
				(!Double.isInfinite(position[0])) && (!Double.isInfinite(position[0])) && (position.length == 2) ) {
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Set the position of this ship to the given position.
	 * 
	 * @param 	position
	 * 			The new position for this ship.
	 * @post	The position of this ship is equal to the given position.
	 *       	| new.getPosition() == position
	 * @throws 	IllegalArgumentException
	 *  		The given position is not a valid position for any ship.
	 *       	| ! isValidPosition(position)
	 */
	@Raw
	public void setPosition(double[] position)
		throws IllegalArgumentException{
		if (!isValidPosition(position)) throw new IllegalArgumentException("The given position isn't a valid one");
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this ship.
	 */
	private double[] position = new double[2];
	
	/**
	 * Return the velocity of this ship.
	 * 	The velocity of a ship consists of a component in the x direction 
	 *  and a component in the y direction who determine the vessel’s movement 
	 *  per time unit in the x and y direction respectively.
	 */
	@Basic @Raw
	public double[] getVelocity(){
		return this.velocity;
	}
	
	/**
	 * Check whether the given velocity is a valid velocity for
	 * any ship.
	 *  
	 * @param  velocity
	 *         The velocity to check.
	 * @return True if and only if the velocity consists of a double containing two real numbers.
	 *         | result == ( (!Double.isNaN(velocity[0])) && (!Double.isNaN(velocity[1])) && (velocity.length == 2) )
	 */
	public static boolean isValidVelocity(double[] velocity){
		if ( (!Double.isNaN(velocity[0])) && (!Double.isNaN(velocity[1])) && (velocity.length == 2) ){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Set the velocity of this ship to the given velocity.
	 * 
	 * @param 	velocity
	 * 			The new velocity for this ship.
	 * @post	If the given velocity does not result in a total speed 
	 * 			which exceeds the maximum speed for all ships, 
	 * 			the velocity of this ship is equal to the given velocity.
	 * 			| if ( getSpeed(velocity) < maxSpeed )
	 * 			|	then new.getVelocity() == velocity
	 * @post	If the given velocity does result in a total speed which 
	 * 			exceeds the maximum speed for all ships and is not infinite,
	 * 			the velocity of this ship is reduced until the total speed
	 * 			equals the maximum speed, without changing the ratio between 
	 * 			the given velocity in the x direction and in the y direction.
	 * 			| if ( getSpeed(velocity) > maxSpeed )
	 * 			| 	then ( (new.getVelocity()[0] == (velocity[0]*maxSpeed/getSpeed(velocity))) 
	 * 			|		 && (new.getVelocity()[1] == (velocity[1]*maxSpeed/getSpeed(velocity))) )
	 * @post	If the given velocity is infinite in the positive 
	 * 			or negative x direction and not infinite in the y direction, 
	 * 			the velocity of this ship is equal to 
	 * 			the maximum speed in that direction.
	 * 			| if ( Double.isInfinite(velocity[0]) && (velocity[0] > 0) )
	 * 			|	then new.getVelocity()[0] == maxSpeed
	 * 			| else if ( Double.isInfinite(velocity[0]) && (velocity[0] < 0) )
	 * 			|	then new.getVelocity()[0] == - maxSpeed ) )
	 * 			| new.getVelocity()[1] == 0
	 * @post	If the given velocity is infinite in the positive 
	 * 			or negative y direction and not infinite in the x direction, 
	 * 			the velocity of this ship is equal to 
	 * 			the maximum speed in that direction.
	 * 			| if ( Double.isInfinite(velocity[1]) && (velocity[1] > 0) )
	 * 			|	then new.getVelocity[1] == maxSpeed
	 * 			| else if ( Double.isInfinite(velocity[1]) && (velocity[1] < 0) )
	 * 			|	then new.getVelocity[1] == - maxSpeed ) )
	 * 			| new.getVelocity[0] == 0
	 * @post	If the given velocity is infinite in both the 
	 * 			negative or positive x and the negative or positive y direction,
	 * 			the velocity of this ship is equal to the maximum speed
	 * 			in the direction PI/4 radians from the axes, with signs of
	 * 			both the velocity in the x direction as in the y direction
	 * 			equal to the signs of the given velocity in their respective directions.
 	 *			| if (Double.isInfinite(velocity[0]) && (Double.isInfinite(velocity[1])))
	 *			|	if (velocity[0] > 0)
	 *			|		then new.getVelocity[0] == maxSpeed/Math.sqrt(2)
	 *		 	|	else
	 *			|		then new.getVelocity[0] == -maxSpeed/Math.sqrt(2)
	 *			|	if (velocity[1] > 0)
	 *			|		then new.getVelocity[1] == maxSpeed/Math.sqrt(2)
	 *		 	|	else
	 *			|		then new.getvelocity[1] == -maxSpeed/Math.sqrt(2)
	 * @post	If the given velocity in the x or y direction is not a number,
	 * 			the velocity of this ship in that direction is zero.
	 * 			| if (Double.isNaN(velocity[0])) {
	 *		    |	then new.getVelocity() == setVelocity({0,velocity[1]})
	 *			| if (Double.isNaN(velocity[1])) {
	 *			|	then new.getVelocity() == setVelocity({velocity[0],0})
	 */
	@Raw
	public void setVelocity(double[] velocity){
		if (Double.isNaN(velocity[1])) {
			setVelocity(new double[] {velocity[0],0});
			return;
		}
		if (Double.isNaN(velocity[0])) {
			setVelocity(new double[] {0,velocity[1]});
			return;
		}
		double speed = getSpeed(velocity);
		if (Double.isInfinite(velocity[0]) && (Double.isInfinite(velocity[1]))){
			if (velocity[0] > 0){
				this.velocity[0] = maxSpeed/Math.sqrt(2);
			} else{
				this.velocity[0] = -maxSpeed/Math.sqrt(2);
			}
			if (velocity[1] > 0){
				this.velocity[1] = maxSpeed/Math.sqrt(2);
			} else{
				this.velocity[1] = -maxSpeed/Math.sqrt(2);
			}
		} else if (Double.isInfinite(velocity[0])){
			if (velocity[0] > 0){
				this.velocity[0] = maxSpeed;
			} else{
				this.velocity[0] = -maxSpeed;
			}
			this.velocity[1] = 0;
		} else if (Double.isInfinite(velocity[1])){
			if (velocity[1] > 0){
				this.velocity[1] = maxSpeed;
			} else{
				this.velocity[1] = -maxSpeed;
			}
			this.velocity[0] = 0;
		} else if (speed > maxSpeed){
			this.velocity[0] = (velocity[0]*maxSpeed)/speed;
			this.velocity[1] = (velocity[1]*maxSpeed)/speed;
		} 
		else{
			this.velocity = velocity;
		} 
	}
	
	/**
	 * Variable registering the velocity of this ship.
	 */
	private double[] velocity = new double[2];
	
	/**
	 * Return the maximum speed for each ship.
	 */
	@Basic @Immutable
	public static double getMaxSpeed() {
		return Ship.maxSpeed;
	}
	
	/**
	 * Constant reflecting the speed of light.
	 */
	public static final double SPEED_OF_LIGHT = 300000;
	
	/**
	 * Constant reflecting the maximum speed that applies to all ships.
	 */
	public static final double maxSpeed = SPEED_OF_LIGHT;
	
	/**
	 * Return the total speed of the ship given the velocity 
	 * in the x direction and in the y direction.
	 * 
	 * @param 	velocity
	 * 			The velocity of this ship.
	 * @return	The square root of the sum of the velocity in 
	 * 			the x direction squared with the velocity in the y direction squared.
	 * 			| result == Math.sqrt(Math.pow(velocity[0], 2) + Math.pow(velocity[1], 2))
	 */
	public double getSpeed(double[] velocity){
		double speed = Math.sqrt(Math.pow(velocity[0], 2) + Math.pow(velocity[1], 2));
		return speed;
	}
	
	/**
	 * Return the radius of this ship.
	 * 	The radius of a ship is the distance between the
	 *  center of the ship and the outer edge of the ship.
	 */
	@Basic @Immutable @Raw
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Check whether the given radius is a valid radius for
	 * any ship.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the radius is a 
	 * 			real number greater than or equal to the minimum radius.
	 * 			| result == ( (radius >= MIN_RADIUS) && (!Double.isInfinite(radius)) && (!Double.isNaN(radius)) )
	 */
	public static boolean isValidRadius(double radius){
		if ( (radius >= minRadius) && (!Double.isInfinite(radius)) && (!Double.isNaN(radius)) ) {
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	/**
	 * Return the minimum radius for each ship.
	 */
	@Basic @Immutable
	public static double getMinRadius() {
		return minRadius;
	}
	/**
	 * Constant reflecting the minimum radius that applies to all ships.
	 */
	private static final double minRadius = 10;
	
	/**
	 * Return the orientation of this ship.
	 * 	The orientation is the direction in which the ship is faced
	 * 	expressed as an angle in radians. For example, the orientation
	 *  of a ship facing right is 0, a ship facing up is at angle PI/2.
	 */
	@Basic @Raw
	public double getOrientation(){
		return this.orientation;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any ship.
	 * 
	 * @param	orientation
	 * 			The orientation to check.
	 * @return	True if and only if the orientation is a real number between 0 and 2*PI.
	 * 			| result == ( (orientation>=0) && (orientation<=2*Math.PI) && (!Double.isNaN(orientation)))
	 */
	public boolean isValidOrientation(double orientation){
		return ( (orientation>=0) && (orientation<=2*Math.PI) && (!Double.isNaN(orientation)));
	}
	
	/**
	 * Set the orientation of this ship to the given orientation.
	 * 
	 * @param 	orientation
	 * 			The new orientation for this ship.
	 * @pre	  	The given orientation must be a valid orientation for a ship.
     *        	| isValidOrientation(orientation)
     * @post	The orientation of this ship is equal to the given orientation.
	 *       	| new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
	/**
	 * Move this ship for a given duration based on its current
	 * position and current velocity to a new position.
	 * 
	 * @param 	duration
	 * 			The duration for how long the ship moves to its new position.
	 * @effect	The new position of this ship is set to the old position of
	 * 			this ship incremented with the product of the given duration
	 * 			and the velocity of the ship.
	 * 			| setPosition( {(getPosition()[0] + duration*getVelocity()[0]),
	 * 			|				(getPosition()[1] + duration*getVelocity()[1])} )
	 * @throws 	IllegalArgumentException
	 * 			The given duration is not a valid duration for any ship.
	 * 			| ! isValidDuration(duration)
	 */
	public void move(double duration)
		throws IllegalArgumentException{
		if (! isValidDuration(duration)) throw new IllegalArgumentException("The given duration isn't a valid one");
		double newX = (getPosition()[0] + duration*getVelocity()[0]);
		double newY = (getPosition()[1] + duration*getVelocity()[1]);
		double[] newPosition = {newX, newY};
		setPosition(newPosition);
	}
	
	/**
	 * Check whether the given duration is a valid
	 * duration for any ship.
	 * 
	 * @param 	duration
	 * 			The duration to check.
	 * @return	True if and only if the duration is a real number,
	 * 			greater than zero and not infinite.
	 * 			| result == ( (!Double.isNaN(duration)) && (!Double.isInfinite(duration)) && (duration >= 0) )
	 */
	public static boolean isValidDuration(double duration){
		if ( (!Double.isNaN(duration)) && (!Double.isInfinite(duration)) && (duration >= 0) ) {
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Turn this ship by adding a given angle to its orientation.
	 * 
	 * @param 	angle
	 * 			The angle that indicates how much this ship must turn
	 * 			and in which direction.
	 * @pre		The given angle must be a valid angle for this ship.
	 * 			| isValidOrientation(getOrientation() + angle)
	 * @effect	The new orientation of this ship is set to the old
	 * 			orientation of this ship incremented with the given angle.
	 *       	| new.getOrientation() == orientation
	 */
	public void turn(double angle){
		assert isValidOrientation(getOrientation() + angle);
		setOrientation(getOrientation() + angle);
	}
	
	/**
	 * Change this ship's velocity based on the current velocity, its orientation, and on a given amount.
	 * 
	 * @param 	amount
	 * 			The variable who determines how much the velocity must change.
	 * @effect	If the given amount is negative or not a number,
	 * 			the velocity does not change.
	 * 			| if (amount < 0 || Double.isNaN(amount))
	 * 			|	then new.getVelocity() == this.getVelocity()
	 * @effect	If the given amount is a positive real number,
	 * 			the new velocity in the x direction of this ship is set to 
	 * 			the old velocity in the x direction of this ship incremented 
	 * 			with the product of the given amount and the cosinus of the 
	 * 			orientation of this ship, while the new velocity in the y direction
	 * 			of this ship is set to the old velocity in the y direction of
	 * 			this ship incremented with the product of the given amount and
	 * 			the sinus of the orientation of this ship.
	 * 			| setVelocity( {(getVelocity()[0] + amount*Math.cos(this.getOrientation())),
	 * 							(getVelocity()[1] + amount*Math.sin(this.getOrientation()))} )
	 */
	public void thrust(double amount){
		if (amount < 0 || Double.isNaN(amount)){
			return;
		}else{
		double newXVelocity = (this.getVelocity()[0] + amount*Math.cos(this.getOrientation()));
		double newYVelocity = (this.getVelocity()[1] + amount*Math.sin(this.getOrientation()));
		double[] newVelocity= {newXVelocity, newYVelocity};
		this.setVelocity(newVelocity);
		}
	}
	
	/**
	 * Return the distance in between this ship and a given ship.
	 * 
	 * @param 	other
	 * 			The given ship.
	 * @return	Zero for the distance between a ship and itself.
	 * 			| if (this == ship2)
	 * 			|	then result == 0
	 * @return	The distance between the centers of this ship
	 * 			and the given ship minus the sum of their radiuses.
	 * 			This distance may be negative if the ships overlap.
	 * 			| if (this != ship2)
	 * 			| 	then result == (Math.sqrt(Math.pow((ship2.getPosition()[0] - 
	 * 			|					this.getPosition()[0]), 2) + Math.pow((ship2.getPosition()[1] - 
	 * 			|					this.getPosition()[1]), 2)) - this.getRadius() - ship2.getRadius())
	 * @throws 	NullPointerException
	 * 			The other ship is not effective
	 * 			| other == null
	 */
	public double getDistanceBetween(Ship other) throws NullPointerException{
		if (other == null) throw new NullPointerException("The other ship is not effective");
		if (this == other){
			return 0;
		}
		double distance = (Math.sqrt(Math.pow((other.getPosition()[0] - this.getPosition()[0]), 2) + Math.pow((other.getPosition()[1] - this.getPosition()[1]), 2)) - this.getRadius() - other.getRadius());
		return distance;
	}
	
	/**
	 * Check whether this ship overlaps with a given ship.
	 * 
	 * @param 	other
	 * 			The given ship.
	 * @return	True if and only if the distance between this ship and
	 * 			the given ship is smaller than or equal to zero.
	 * 			| result == (this.getDistanceBetween(ship2) <= 0)
	 * @throws 	NullPointerException
	 * 			The other ship is not effective
	 * 			| other == null
	 */
	public boolean overlap(Ship other) throws NullPointerException{
		if (other == null) throw new NullPointerException("The other ship is not effective");
		if (this.getDistanceBetween(other) <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Return when (i.e. in how many seconds), if ever, this ship 
	 * and a given ship will collide. This method does not apply to 
	 * ships that overlap. If this ship and the given ship never collide,
	 * return positive infinity.
	 * 
	 * @param 	other
	 * 			The given ship.
	 * @return 	The resulting time to collision is a double greater than zero.
	 * 			| result > 0
	 * @return	No smaller double that specifies the time to collision between
	 * 			this ship and the given ship exists.
	 * 			| for each double < result:
	 * 			| 	this.getDistanceBetween(other) > 0
	 * @throws 	IllegalStateException
	 * 			The ships overlap.
	 * 			| this.overlap(ship2)
	 * @throws 	NullPointerException
	 * 			The other ship is not effective
	 * 			| other == null
	 */
	public double getTimeToCollision(Ship other)
			throws IllegalStateException, NullPointerException{
		if (other == null) throw new NullPointerException("The other ship is not effective");
		if (this.overlap(other)) throw new IllegalStateException("This method does not apply to ships that overlap");
		double diffX = other.getPosition()[0] - this.getPosition()[0];
		double diffY = other.getPosition()[1] - this.getPosition()[1];
		double diffVX = other.getVelocity()[0] - this.getVelocity()[0];
		double diffVY = other.getVelocity()[1] - this.getVelocity()[1];
		double distanceCentersSquared = Math.pow((this.getRadius() + other.getRadius()), 2);
		double varD = (Math.pow((diffX*diffVX + diffY*diffVY), 2) - (Math.pow(diffVX,2) + Math.pow(diffVY,2))*((Math.pow(diffX,2) + Math.pow(diffY,2)) - distanceCentersSquared));
		if ((diffVX*diffX + diffVY*diffY) >= 0 || varD <= 0){
			return Double.POSITIVE_INFINITY;
		}else{
			double timeToCollision = -(((diffVX*diffX + diffVY*diffY) + Math.sqrt(varD))/(Math.pow(diffVX,2) + Math.pow(diffVY,2)));
			return timeToCollision;
		}
	}
	
	/**
	 * Return where, if ever, this ship and the given ship will collide. 
	 * The method shall return null if the ships never collide. 
	 * This method does not apply to ships that overlap.
	 * 
	 * @param 	other
	 * 			The given ship.
	 * @return	Null if this ship never collides with
	 * 			the given ship.
	 * 			| if (getTimeToCollision(ship2) == Double.POSITIVE_INFINITY)
	 * 			|	then result == null
	 * 			Otherwise, the position of the collision between 
	 * 			this ship and the given ship.
	 * 			| else
	 * 			|	result == {collisionX2 + ship2.getRadius()*Math.cos(angleCenters), 
	 * 			|			   collisionY2 + ship2.getRadius()*Math.sin(angleCenters)}
	 * @throws	IllegalStateException
	 * 			The ships overlap.
	 * 			| this.overlap(ship2)
	 * @throws 	NullPointerException
	 * 			The other ship is not effective
	 * 			| other == null
	 */
	public double[] getCollisionPosition(Ship other) throws NullPointerException, IllegalStateException{
		if (other == null) throw new NullPointerException("The other ship is not effective");
		if (this.overlap(other)) throw new IllegalStateException("This method does not apply to ships that overlap");
		
		if (getTimeToCollision(other) == Double.POSITIVE_INFINITY){
			return null;
		}else{
			double duration = this.getTimeToCollision(other);
			double collisionX1 = (this.getPosition()[0] + duration*this.getVelocity()[0]);
			double collisionY1 = (this.getPosition()[1] + duration*this.getVelocity()[1]);
			double collisionX2 = (other.getPosition()[0] + duration*other.getVelocity()[0]);
			double collisionY2 = (other.getPosition()[1] + duration*other.getVelocity()[1]);
			double diffX = collisionX2 - collisionX1;
			double diffY = collisionY2 - collisionY1;
			double angleCenters = 0;
			if (diffX*diffY >= 0){
				if (diffX == 0){
					if (diffY > 0){
						angleCenters = (Math.PI/2);
					}else{
						angleCenters = -(Math.PI/2);
					}
				}else if ((diffX > 0) || (diffY == 0)){
					angleCenters = Math.atan(diffY/diffX) + Math.PI;
				}else if((diffX < 0) || (diffY == 0)){
					angleCenters = Math.atan(diffY/diffX);
				}
			}else {
				if (diffY > 0){
					angleCenters = Math.atan(diffY/diffX);
				}else if(diffY < 0){
					angleCenters = Math.atan(diffY/diffX) + Math.PI;
				}
			}
			
			double[] collisionPoint = {collisionX2 + other.getRadius()*Math.cos(angleCenters), collisionY2 + other.getRadius()*Math.sin(angleCenters)};
			return collisionPoint;
		}
	}
}