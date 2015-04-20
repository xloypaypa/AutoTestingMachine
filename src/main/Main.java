package main;

import ui.page.ChoicePage;
import compiler.CPlusPlusCompiler;
import compiler.CompileManager;
import compiler.LispCompiler;

public class Main {
	public static void main(String[] args) {
		loadCompiler();
		
		ChoicePage page=new ChoicePage();
		page.setMainPage();
		page.loadItem();
	}

	protected static void loadCompiler() {
		CompileManager.addCompiler(new CPlusPlusCompiler());
		CompileManager.addCompiler(new LispCompiler());
	}
}
