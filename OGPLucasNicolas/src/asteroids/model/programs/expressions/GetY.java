package asteroids.model.programs.expressions;

import java.util.Map;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetY extends UnaryExpression<Double> {

	public GetY(Expression<Entity> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public String operatorToString() {
		return "gety";
	}

	@Override
	public Type getType(Map<String, Expression<?>> variables) {
		return Type.DOUBLE;
	}

	@Override
	public Double getValue(Map<String, Expression<?>> variables) {
		this.getStatement().connectExpression(this.getExpression());
		assert ((this.getExpression().getType(variables) == Type.ENTITY)
				&& (this.getExpression().getValue(variables) != null));
		return ((Entity)getExpression().getValue(variables)).getPositionY();
	}

}