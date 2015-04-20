package compiler;

public abstract class AbstractCompiler implements Compile {
	
	protected String name;
	
	public AbstractCompiler(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isThisCompiler(String name) {
		return this.name.equals(name);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
