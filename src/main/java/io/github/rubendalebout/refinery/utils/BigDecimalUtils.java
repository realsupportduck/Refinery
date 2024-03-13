package io.github.rubendalebout.refinery.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class BigDecimalUtils {
    /**
     * Checks if the BigDecimal is equal to zero.
     * @param number The BigDecimal to check.
     * @return True if the BigDecimal is zero, otherwise false.
     */
    public static boolean isZero(BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Checks if the BigDecimal is positive.
     * @param number The BigDecimal to check.
     * @return True if the BigDecimal is positive, otherwise false.
     */
    public static boolean isPositive(BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Checks if the BigDecimal is negative.
     * @param number The BigDecimal to check.
     * @return True if the BigDecimal is negative, otherwise false.
     */
    public static boolean isNegative(BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * Checks if the BigDecimal is an even number.
     * @param number The BigDecimal to check.
     * @return True if the BigDecimal is even, otherwise false.
     */
    public static boolean isEven(BigDecimal number) {
        return number.remainder(BigDecimal.valueOf(2)).equals(BigDecimal.ZERO);
    }

    /**
     * Checks if the BigDecimal is an odd number.
     * @param number The BigDecimal to check.
     * @return True if the BigDecimal is odd, otherwise false.
     */
    public static boolean isOdd(BigDecimal number) {
        return !isEven(number);
    }

    /**
     * Checks if two BigDecimals are equal.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return True if the BigDecimals are equal, otherwise false.
     */
    public static boolean isEqual(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == 0;
    }

    /**
     * Checks if one BigDecimal is greater than another.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return True if the first BigDecimal is greater than the second, otherwise false.
     */
    public static boolean isGreater(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0;
    }

    /**
     * Checks if one BigDecimal is less than another.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return True if the first BigDecimal is less than the second, otherwise false.
     */
    public static boolean isLess(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) < 0;
    }

    /**
     * Adds two BigDecimals.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return The sum of the two BigDecimals.
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /**
     * Subtracts one BigDecimal from another.
     * @param a The BigDecimal to subtract from.
     * @param b The BigDecimal to subtract.
     * @return The result of the subtraction.
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /**
     * Multiplies two BigDecimals.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return The product of the two BigDecimals.
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /**
     * Divides one BigDecimal by another.
     * @param a The dividend.
     * @param b The divisor.
     * @return The result of the division.
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, RoundingMode.HALF_UP);
    }

    /**
     * Raises a BigDecimal to a given power.
     * @param base The base BigDecimal.
     * @param exponent The exponent to raise the base to.
     * @return The result of the exponentiation.
     */
    public static BigDecimal power(BigDecimal base, int exponent) {
        return base.pow(exponent);
    }

    /**
     * Rounds a BigDecimal to a specified precision.
     * @param number The BigDecimal to round.
     * @param precision The number of decimal places to round to.
     * @return The rounded BigDecimal.
     */
    public static BigDecimal round(BigDecimal number, int precision) {
        return number.setScale(precision, RoundingMode.HALF_UP);
    }

    /**
     * Rounds up to the nearest whole number.
     * @param number The BigDecimal to round.
     * @return The rounded BigDecimal.
     */
    public static BigDecimal ceil(BigDecimal number) {
        return number.setScale(0, RoundingMode.CEILING);
    }

    /**
     * Rounds down to the nearest whole number.
     * @param number The BigDecimal to round.
     * @return The rounded BigDecimal.
     */
    public static BigDecimal floor(BigDecimal number) {
        return number.setScale(0, RoundingMode.FLOOR);
    }

    /**
     * Gets the absolute value of a BigDecimal.
     * @param number The BigDecimal.
     * @return The absolute value.
     */
    public static BigDecimal abs(BigDecimal number) {
        return number.abs();
    }

    /**
     * Negates the value of a BigDecimal.
     * @param number The BigDecimal.
     * @return The negated BigDecimal.
     */
    public static BigDecimal negate(BigDecimal number) {
        return number.negate();
    }

    /**
     * Adjusts the scale of a BigDecimal.
     * @param number The BigDecimal.
     * @param newScale The new scale.
     * @return The BigDecimal with the adjusted scale.
     */
    public static BigDecimal scale(BigDecimal number, int newScale) {
        return number.setScale(newScale, RoundingMode.HALF_UP);
    }

    /**
     * Adjusts the precision of a BigDecimal.
     * @param number The BigDecimal.
     * @param newPrecision The new precision.
     * @return The BigDecimal with the adjusted precision.
     */
    public static BigDecimal precision(BigDecimal number, int newPrecision) {
        return number.setScale(newPrecision - number.scale(), RoundingMode.HALF_UP);
    }

    /**
     * Gets the maximum of two BigDecimals.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return The maximum of the two BigDecimals.
     */
    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        return a.max(b);
    }

    /**
     * Gets the minimum of two BigDecimals.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return The minimum of the two BigDecimals.
     */
    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.min(b);
    }

    /**
     * Calculates the square root of a BigDecimal.
     * @param number The BigDecimal.
     * @return The square root.
     */
    public static BigDecimal sqrt(BigDecimal number) {
        return BigDecimal.valueOf(Math.sqrt(number.doubleValue()));
    }

    /**
     * Calculates the cube root of a BigDecimal.
     * @param number The BigDecimal.
     * @return The cube root.
     */
    public static BigDecimal cbrt(BigDecimal number) {
        return BigDecimal.valueOf(Math.cbrt(number.doubleValue()));
    }

    /**
     * Calculates the remainder of division.
     * @param dividend The dividend.
     * @param divisor The divisor.
     * @return The remainder of division.
     */
    public static BigDecimal remainder(BigDecimal dividend, BigDecimal divisor) {
        return dividend.remainder(divisor);
    }

    /**
     * Calculates the reciprocal of a BigDecimal.
     * @param number The BigDecimal.
     * @return The reciprocal.
     */
    public static BigDecimal reciprocal(BigDecimal number) {
        return BigDecimal.ONE.divide(number, number.scale(), RoundingMode.HALF_UP);
    }

    /**
     * Calculates the factorial of a non-negative integer.
     * @param n The non-negative integer.
     * @return The factorial.
     */
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Calculates the natural logarithm of a BigDecimal.
     * @param number The BigDecimal.
     * @return The natural logarithm.
     */
    public static BigDecimal log(BigDecimal number) {
        return BigDecimal.valueOf(Math.log(number.doubleValue()));
    }

    /**
     * Calculates the base 10 logarithm of a BigDecimal.
     * @param number The BigDecimal.
     * @return The base 10 logarithm.
     */
    public static BigDecimal log10(BigDecimal number) {
        return BigDecimal.valueOf(Math.log10(number.doubleValue()));
    }

    /**
     * Calculates the exponential function e^x for a BigDecimal.
     * @param number The BigDecimal.
     * @return The exponential function value.
     */
    public static BigDecimal exp(BigDecimal number) {
        return BigDecimal.valueOf(Math.exp(number.doubleValue()));
    }

    /**
     * Calculates the exponential function with a custom base.
     * @param base The base BigDecimal.
     * @param exponent The exponent.
     * @return The result of the exponential function with the custom base.
     */
    public static BigDecimal expCustomBase(BigDecimal base, BigDecimal exponent) {
        return BigDecimal.valueOf(Math.pow(base.doubleValue(), exponent.doubleValue()));
    }

    /**
     * Calculates the nth root of a BigDecimal.
     * @param number The BigDecimal.
     * @param n The root.
     * @return The nth root.
     */
    public static BigDecimal nthRoot(BigDecimal number, int n) {
        return BigDecimal.valueOf(Math.pow(number.doubleValue(), 1.0 / n));
    }

    /**
     * Calculates the absolute difference between two BigDecimals.
     * @param a The first BigDecimal.
     * @param b The second BigDecimal.
     * @return The absolute difference.
     */
    public static BigDecimal absDifference(BigDecimal a, BigDecimal b) {
        return a.subtract(b).abs();
    }

    /**
     * Converts a BigDecimal to an integer.
     * @param number The BigDecimal to convert.
     * @return The integer value of the BigDecimal.
     */
    public static int toInteger(BigDecimal number) {
        return number.intValue();
    }

    /**
     * Generates a random BigDecimal within a specified range.
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return A random BigDecimal within the specified range.
     */
    public static BigDecimal random(BigDecimal min, BigDecimal max) {
        BigDecimal range = max.subtract(min);
        BigDecimal randomFactor = new BigDecimal(new Random().nextDouble());
        BigDecimal randomValue = randomFactor.multiply(range).add(min);
        return randomValue.setScale(max.scale(), RoundingMode.HALF_UP);
    }
}
