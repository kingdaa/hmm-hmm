import java.util.concurrent.atomic.AtomicInteger;

/*
  Generate Unique Integer 
  1. Each time generates an unique integer 
  2. Should be thread-safe: different threads never get same number
 */

public class GenerateUniqueInteger {
	private AtomicInteger ai = new AtomicInteger();

	// Thought 1: Use an AtomicInteger, increment and get each time
	public Integer getUniqueInt() {
		return ai.incrementAndGet();
	}
}
