package pl.kielce.tu.drylofudala.authentication;

import java.util.List;

public class ValidationResult {
	private final boolean valid;
	private final List<String> messages;

	public ValidationResult(boolean valid, List<String> messages) {
		this.messages = messages;
		this.valid = valid;
	}

	public ValidationResult(boolean valid) {
		this.messages = null;
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}

	public List<String> getMessages() {
		return messages;
	}
}
