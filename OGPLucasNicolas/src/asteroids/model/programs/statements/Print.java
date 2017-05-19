package asteroids.model.programs.statements;

import java.util.Map;

import asteroids.model.programs.expressions.Expression;
import asteroids.model.programs.expressions.Type;
import asteroids.part3.programs.SourceLocation;

public class Print extends NoActionStatement {

	public Print(SourceLocation sourceLocation, Expression<?> toPrint) {
		super(sourceLocation);
		this.expression = toPrint;
	}

	public Expression<?> getExpression(){
		return expression;
	}
	
	private final Expression<?> expression;
	
	@Override
	public String toString() {
		System.out.println(getExpression());
		//return "print ";
		return "print " + getExpression().toString() + ";";
	}

	@Override
	public void executeStatement(Map<String, Expression<?>> variables) {
		System.out.println("We zijn in de print");
		if (this.getExpression().getType(variables) == (Type.BOOL) || 
				this.getExpression().getType(variables) == (Type.DOUBLE) )  {
			System.out.println(this.getExpression().getValue(variables));
		}
		else{
			System.out.println(this.getExpression().getValue(variables).toString());
		}
		this.getProgram().addToValuesPrinted(this.getExpression().getValue(variables));
	}

}