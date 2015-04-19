package checker;

import encryptionAlgorithm.SHA1;

public class NormalChecker extends AbstractChecker {
	
	String ansCode;
	
	@Override
	public void setAnswer(String path) {
		super.setAnswer(path);
		ansCode=SHA1.getFileSha1(answerPath);
	}

	@Override
	public boolean checkFile(String path) {
		return ansCode.equals(SHA1.getFileSha1(path));
	}

}
