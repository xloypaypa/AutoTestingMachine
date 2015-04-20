package runner;

import java.io.IOException;

public class ExeRunner extends Runner {

	@Override
	public Process codeStart() {
		try {
			return Runtime.getRuntime().exec(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
