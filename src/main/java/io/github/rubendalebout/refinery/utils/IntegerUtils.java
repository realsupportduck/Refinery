package io.github.rubendalebout.refinery.utils;

public class IntegerUtils {
    /**
     * Checks if a number is even.
     * @param number The number to check.
     * @return True if the number is even, otherwise false.
     */
    public boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    /**
     * Checks if a number is odd.
     * @param number The number to check.
     * @return True if the number is odd, otherwise false.
     */
    public boolean isOdd(int number) {
        return number % 2 != 0;
    }
    
    /**
     * Checks if a number is prime.
     * @param number The number to check.
     * @return True if the number is prime, otherwise false.
     */
    public boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }
    
    /**
     * Computes the factorial of a number.
     * @param number The number to compute factorial for.
     * @return The factorial of the number.
     */
    public int factorial(int number) {
        if (number == 0) return 1;
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Computes the greatest common divisor of two numbers.
     * @param a The first number.
     * @param b The second number.
     * @return The greatest common divisor.
     */
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /**
     * Computes the least common multiple of two numbers.
     * @param a The first number.
     * @param b The second number.
     * @return The least common multiple.
     */
    public int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    /**
     * Checks if a number is a palindrome.
     * @param number The number to check.
     * @return True if the number is a palindrome, otherwise false.
     */
    public boolean isPalindrome(int number) {
        int reverse = 0;
        int originalNumber = number;
        while (number != 0) {
            int remainder = number % 10;
            reverse = reverse * 10 + remainder;
            number /= 10;
        }
        return originalNumber == reverse;
    }
    
    /**
     * Reverses the digits of a number.
     * @param number The number to reverse.
     * @return The reversed number.
     */
    public int reverse(int number) {
        int reversedNumber = 0;
        while (number != 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }
        return reversedNumber;
    }
    
    /**
     * Checks if a number is an Armstrong number.
     * @param number The number to check.
     * @return True if the number is an Armstrong number, otherwise false.
     */
    public boolean isArmstrong(int number) {
        int originalNumber, remainder, result = 0, n = 0;
        originalNumber = number;
        while (originalNumber != 0) {
            originalNumber /= 10;
            ++n;
        }
        originalNumber = number;
        while (originalNumber != 0) {
            remainder = originalNumber % 10;
            result += (int) Math.pow(remainder, n);
            originalNumber /= 10;
        }
        return result == number;
    }
    
    /**
     * Converts a binary number to decimal.
     * @param binary The binary number to convert.
     * @return The decimal equivalent of the binary number.
     */
    public int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }
    
    /**
     * Converts a decimal number to binary.
     * @param decimal The decimal number to convert.
     * @return The binary equivalent of the decimal number.
     */
    public String decimalToBinary(int decimal) {
        return Integer.toBinaryString(decimal);
    }
    
    /**
     * Converts a decimal number to hexadecimal.
     * @param decimal The decimal number to convert.
     * @return The hexadecimal equivalent of the decimal number.
     */
    public String decimalToHex(int decimal) {
        return Integer.toHexString(decimal);
    }
    
    /**
     * Converts a hexadecimal number to decimal.
     * @param hex The hexadecimal number to convert.
     * @return The decimal equivalent of the hexadecimal number.
     */
    public int hexToDecimal(String hex) {
        return Integer.parseInt(hex, 16);
    }
    
    /**
     * Computes the power of a number.
     * @param base The base.
     * @param exponent The exponent.
     * @return The result of base raised to the power of exponent.
     */
    public int power(int base, int exponent) {
        return (int) Math.pow(base, exponent);
    }
    
    /**
     * Computes the sum of the digits of a number.
     * @param number The number.
     * @return The sum of the digits.
     */
    public int sumDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    
    /**
     * Counts the number of digits in a number.
     * @param number The number.
     * @return The number of digits.
     */
    public int countDigits(int number) {
        return String.valueOf(number).length();
    }
    
    /**
     * Checks if a number is perfect.
     * @param number The number to check.
     * @return True if the number is perfect, otherwise false.
     */
    public boolean isPerfect(int number) {
        int sum = 1;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                sum += i;
                if (i != number / i) {
                    sum += number / i;
                }
            }
        }
        return sum == number && number != 1;
    }
    
    /**
     * Checks if two numbers are amicable.
     * @param num1 The first number.
     * @param num2 The second number.
     * @return True if the numbers are amicable, otherwise false.
     */
    public boolean isAmicable(int num1, int num2) {
        return (sumOfDivisors(num1) == num2 && sumOfDivisors(num2) == num1);
    }
    
    /**
     * Computes the sum of divisors of a number.
     * @param n The number.
     * @return The sum of divisors.
     */
    public int sumOfDivisors(int n) {
        int sum = 1;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) {
                    sum += n / i;
                }
            }
        }
        return sum;
    }
    
    /**
     * Checks if a number is abundant.
     * @param number The number to check.
     * @return True if the number is abundant, otherwise false.
     */
    public boolean isAbundant(int number) {
        return sumOfDivisors(number) > number;
    }
    
    /**
     * Checks if a number is deficient.
     * @param number The number to check.
     * @return True if the number is deficient, otherwise false.
     */
    public boolean isDeficient(int number) {
        return sumOfDivisors(number) < number;
    }
    
    /**
     * Computes the frequency of a digit in a number.
     * @param number The number.
     * @param digit The digit to count frequency for.
     * @return The frequency of the digit.
     */
    public int digitFrequency(int number, int digit) {
        int count = 0;
        while (number > 0) {
            if (number % 10 == digit) {
                count++;
            }
            number /= 10;
        }
        return count;
    }
    
    /**
     * Finds the nearest prime number greater than or equal to the given number.
     * @param number The number.
     * @return The nearest prime number.
     */
    public int nearestPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }
    
    /**
     * Finds the next power of two greater than the given number.
     * @param number The number.
     * @return The next power of two.
     */
    public int nextPowerOfTwo(int number) {
        return (int) Math.pow(2, Math.ceil(Math.log(number) / Math.log(2)));
    }
    
    /**
     * Checks if a number is a power of two.
     * @param number The number.
     * @return True if the number is a power of two, otherwise false.
     */
    public boolean isPowerOfTwo(int number) {
        return (number & (number - 1)) == 0 && number != 0;
    }
    
    /**
     * Checks if a number is a Harsh number.
     * @param number The number.
     * @return True if the number is a Harsh number, otherwise false.
     */
    public boolean isHarsh(int number) {
        return number % sumDigits(number) == 0;
    }
    
    /**
     * Generates the Collatz sequence for a given number.
     * @param number The number.
     * @return The Collatz sequence.
     */
    public String collatzSequence(int number) {
        StringBuilder sequence = new StringBuilder();
        sequence.append(number).append(" ");
        while (number != 1) {
            if (number % 2 == 0) {
                number /= 2;
            } else {
                number = 3 * number + 1;
            }
            sequence.append(number).append(" ");
        }
        return sequence.toString();
    }
    
    /**
     * Computes the number of divisors of a number.
     * @param number The number.
     * @return The number of divisors.
     */
    public int numberOfDivisors(int number) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                if (number / i == i) {
                    count++;
                } else {
                    count += 2;
                }
            }
        }
        return count;
    }
    
    /**
     * Checks if a number is a Fibonacci number.
     * @param number The number.
     * @return True if the number is a Fibonacci number, otherwise false.
     */
    public boolean isFibonacci(int number) {
        return isPerfectSquare(5 * number * number + 4) || isPerfectSquare(5 * number * number - 4);
    }
    
    /**
     * Generates an array containing the first N prime numbers.
     * @param n The number of primes to generate.
     * @return An array of the first N prime numbers.
     */
    public int[] firstNPrimes(int n) {
        if (n < 1) return new int[0];
        int[] primes = new int[n];
        primes[0] = 2;
        int count = 1;
        for (int num = 3; count < n; num += 2) {
            if (isPrime(num)) {
                primes[count] = num;
                count++;
            }
        }
        return primes;
    }
    
    /**
     * Computes the sum of all primes below the given limit.
     * @param limit The upper limit.
     * @return The sum of all primes below the limit.
     */
    public int sumOfPrimes(int limit) {
        int sum = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }
    
    /**
     * Finds the next prime number greater than the given number.
     * @param number The number.
     * @return The next prime number.
     */
    public int nextPrime(int number) {
        while (true) {
            if (isPrime(++number)) {
                return number;
            }
        }
    }
    
    /**
     * Checks if a number is a perfect square.
     * @param number The number.
     * @return True if the number is a perfect square, otherwise false.
     */
    public boolean isSquare(int number) {
        return Math.sqrt(number) % 1 == 0;
    }
    
    /**
     * Computes the Nth Fibonacci number.
     * @param n The index of the Fibonacci number to compute.
     * @return The Nth Fibonacci number.
     */
    public int nthFibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
    
    /**
     * Computes the square root of a number.
     * @param number The number.
     * @return The square root of the number.
     */
    public int squareRoot(int number) {
        return (int) Math.sqrt(number);
    }
    
    /**
     * Checks if a number is a perfect square.
     * @param number The number.
     * @return True if the number is a perfect square, otherwise false.
     */
    public boolean isPerfectSquare(int number) {
        int sqrt = squareRoot(number);
        return sqrt * sqrt == number;
    }
    
    /**
     * Checks if a number is a triangular number.
     * @param number The number.
     * @return True if the number is a triangular number, otherwise false.
     */
    public boolean isTriangular(int number) {
        int sum = 0;
        for (int i = 1; sum <= number; i++) {
            sum += i;
            if (sum == number) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Computes the Nth triangular number.
     * @param n The index of the triangular number to compute.
     * @return The Nth triangular number.
     */
    public int triangleNumber(int n) {
        return n * (n + 1) / 2;
    }
    
    /**
     * Checks if a number is a pronic number.
     * @param number The number.
     * @return True if the number is a pronic number, otherwise false.
     */
    public boolean isPronic(int number) {
        int sqrt = squareRoot(number);
        return sqrt * (sqrt + 1) == number;
    }
    
    /**
     * Checks if a number is a disarium number.
     * @param number The number.
     * @return True if the number is a disarium number, otherwise false.
     */
    public boolean disariumNumber(int number) {
        int n = number;
        int digitCount = countDigits(number);
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            sum += (int) Math.pow(digit, digitCount);
            n /= 10;
            digitCount--;
        }
        return sum == number;
    }
    
    /**
     * Multiplies two numbers without using the multiplication operator.
     * @param a The first number.
     * @param b The second number.
     * @return The product of the two numbers.
     */
    public int multiplyWithoutOperator(int a, int b) {
        int result = 0;
        boolean isNegative = (a < 0 && b > 0) || (a > 0 && b < 0);
        a = Math.abs(a);
        b = Math.abs(b);
        for (int i = 0; i < b; i++) {
            result += a;
        }
        return isNegative ? -result : result;
    }
    
    /**
     * Computes the sum of the squares of the digits of a number.
     * @param number The number.
     * @return The sum of the squares of the digits.
     */
    public int sumOfDigitsSquared(int number) {
        int sum = 0;
        while (number != 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }
    
    /**
     * Checks if a number is a perfect power.
     * @param number The number.
     * @return True if the number is a perfect power, otherwise false.
     */
    public boolean isPerfectPower(int number) {
        if (number == 1) return true;
        for (int base = 2; base * base <= number; base++) {
            int temp = base;
            while (temp <= number) {
                temp *= base;
                if (temp == number) return true;
            }
        }
        return false;
    }
    
    /**
     * Computes the number of digits in a number.
     * @param number The number.
     * @return The number of digits.
     */
    public int numberOfDigits(int number) {
        return (int) Math.log10(number) + 1;
    }
    
    /**
     * Checks if a number is an automorphic number.
     * @param number The number.
     * @return True if the number is an automorphic number, otherwise false.
     */
    public boolean isAutomorphic(int number) {
        int square = number * number;
        return Integer.toString(square).endsWith(Integer.toString(number));
    }
    
    /**
     * Computes the number of binary '1' bits in the binary representation of a number.
     * @param number The number.
     * @return The number of binary '1' bits.
     */
    public int numberOfBinaryOnes(int number) {
        int count = 0;
        while (number != 0) {
            count++;
            number &= (number - 1);
        }
        return count;
    }
    
    /**
     * Checks if a number is a Kaprekar number.
     * @param number The number.
     * @return True if the number is a Kaprekar number, otherwise false.
     */
    public boolean isKaprekar(int number) {
        if (number == 1) return true;
        int square = number * number;
        String squareStr = String.valueOf(square);
        for (int i = 1; i < squareStr.length(); i++) {
            int left = Integer.parseInt(squareStr.substring(0, i));
            int right = Integer.parseInt(squareStr.substring(i));
            if (left > 0 && right > 0 && left + right == number) return true;
        }
        return false;
    }
    
    /**
     * Checks if a number is a Lucas number.
     * @param number The number.
     * @return True if the number is a Lucas number, otherwise false.
     */
    public boolean isLucas(int number) {
        return isPerfectSquare(5 * number * number + 4) || isPerfectSquare(5 * number * number - 4);
    }
    
    /**
     * Checks if a number is a narcissistic number.
     * @param number The number.
     * @return True if the number is a narcissistic number, otherwise false.
     */
    public boolean isNarcissistic(int number) {
        int n = number;
        int numDigits = countDigits(number);
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            sum += (int) Math.pow(digit, numDigits);
            n /= 10;
        }
        return sum == number;
    }
}