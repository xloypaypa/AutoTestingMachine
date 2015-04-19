package ui.tool;

public class CodeChoice extends AbstractTool {
	
	PathChoice path;
	LanguageChoice language;
	Input out;
	
	
	public CodeChoice(String name, PathChoice path) {
		super(name);
		this.path=path;
		language=new LanguageChoice();
		out=new Input();
		show.add(this.path.getTool());
		show.add(language.getTool());
		show.add(out.getTool());
	}

	@Override
	public void repaint() {
		path.repaint();
		language.repaint();
		out.repaint();
		show.repaint();
	}

	@Override
	protected void updateSize() {
		path.setBounds(0, 20, show.getWidth()-120, 40);
		language.setBounds(show.getWidth()-115, 20, 60, 40);
		out.setBounds(show.getWidth()-50, 20, 50, 40);
	}
	
	public String getPath() {
		return path.getPath();
	}
	
	public String getLanguage(){
		return language.getChoice();
	}
	
	public String getOut(){
		return out.getMessage();
	}

}
