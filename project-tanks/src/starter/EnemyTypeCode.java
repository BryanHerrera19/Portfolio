package starter;

public enum EnemyTypeCode {
	LIGHT, MEDIUM, HEAVY;

	public String toString() {
		switch(this) {
			case LIGHT: return "Light";
			case MEDIUM: return "Medium";
			case HEAVY: return "Heavy";
		}
		return "n/a";
	}

}
