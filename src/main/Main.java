package main;

import compiler.AbstractCompiler;
import compiler.CPlusPlusCompiler;
import compiler.LispCompiler;
import logic.CodeCheckeLogic;
import ui.UI;
import ui.page.ChoicePage;
import ui.page.StatusPage;
import ui.tool.LanguageChoice;

public class Main {
	public static void main(String[] args) {
		
		LanguageChoice.addLanguage("c++");
		LanguageChoice.addLanguage("lisp");
		
		CodeCheckeLogic.addCompiler(new CPlusPlusCompiler());
		CodeCheckeLogic.addCompiler(new LispCompiler());
		
		AbstractCompiler.setRunPath("E:/test_space/run_path");
		
		UI.addPage(new ChoicePage());
		UI.addPage(new StatusPage());
		UI.createWindow();
		UI.showPage("choice");
	}
}
