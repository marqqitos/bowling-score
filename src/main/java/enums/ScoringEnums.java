package enums;

public enum ScoringEnums {
	STRIKE(10), SPARE(10), FOUL(-1), NOTATTEMPTED(-2);

	private int value;

	ScoringEnums(int value) {
	      this.value = value;
	   }

	public int getValue() {
		return this.value;
	}
}
