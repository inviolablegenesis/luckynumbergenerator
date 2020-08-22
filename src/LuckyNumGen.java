import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LuckyNumGen {

  // given: yyyymmdd
  private static final String dob = "19931023";

  public static void main(String[] args) {

    validate(dob);
    int[] num = convertToIntArray(dob);

    System.out.println(MessageFormat.format("Your lucky number is {0}!", generate(num)));
  }

  /**
   * Add numbers until single digit remains
   *
   * @param num
   * @return
   */
  private static int generate(int[] num) {
    int sum = 0;

    // loop to get the sum
    for (int n : num) {
      sum += n;
    }

    // if it's still not a single digit, split and add the numbers again
    if (sum > 9) {
      return generate(convertToIntArray(String.valueOf(sum)));
    }

    return sum;
  }

  /**
   * Check input is a valid date
   *
   * @param dob
   */
  private static void validate(String dob) {
    // DateTimeFormatter.BASIC_ISO_DATE is yyyymmdd
    try {
      LocalDate.parse(dob, DateTimeFormatter.BASIC_ISO_DATE);
    } catch (Exception e) {
      throw new RuntimeException("Not a valid date");
    }
  }

  /**
   * Convert string into an array of int
   *
   * @param str
   * @return
   */
  private static int[] convertToIntArray(String str) {
    return Arrays.stream(str.split("")).mapToInt(Integer::parseInt).toArray();
  }
}
