package spreadAndDie.tests;

public class RunTests {
	public static void main(String[] args) {
		RunTests tests = new RunTests();
		tests.run();
	}

	private void run() {
		Test[] tests = new Test[] { new GridTest(), new LevelTest() };
		for (Test test : tests) {
			test.run();
		}
	}
}
