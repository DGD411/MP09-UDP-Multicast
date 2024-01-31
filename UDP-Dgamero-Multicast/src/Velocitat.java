
import java.util.Random;

public class Velocitat {
	private int max;
	private Random random;

	public Velocitat(int max) {
		this.max = max;
		this.random = new Random();
	}

	public int agafaVelocitat() {
		return random.nextInt(max) + 1;
	}
}
