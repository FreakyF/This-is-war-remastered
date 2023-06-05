package pl.kielce.tu.drylofudala.authentication;

public class ValidationResult {
	private final boolean valid;
	private final String message;

	public ValidationResult(boolean valid, String message) {
		this.message = message;
		this.valid = valid;
	}

	public ValidationResult(boolean valid) {
		this.message = null;
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}

	public String getMessage() {
		return message;
	}
}
