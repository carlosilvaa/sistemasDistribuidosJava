package entities;

public enum Competencia {
	PYTHON("Python"), C_PLUS_PLUS("C++"), C_SHARP("C#"), SWIFT("Swift"), GO("Go"), JAVA("Java"), HTML("HTML"),
	CSS("CSS"), JS("JS"), TYPESCRIPT("TypeScript"), PHP("PHP"), SQL("SQL"), FLUTTER("Flutter"), RUBY("Ruby"),
	NOSQL("NOSQL"), PERL("Perl"), COBOL("Cobol"), DOT_NET("dotNet"), KOTLIN("Kotlin"), DART("Dart");

	private final String displayName;

	Competencia(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}

}
