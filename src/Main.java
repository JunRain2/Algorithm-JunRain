import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {
		BigDecimal decimalNumber = BigDecimal.valueOf(1234.123412341234);
		BigInteger intVal = decimalNumber.unscaledValue();
		System.out.println(intVal);
	}
}
