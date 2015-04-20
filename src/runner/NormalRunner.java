package runner;

import java.io.IOException;

public class NormalRunner extends Runner {

	@Override
	public Process codeStart() {
		try {
			return Runtime.getRuntime().exec("cmd /c "+filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
