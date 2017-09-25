package com.zt.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * <p>Title: NumberUtils</p>
 * <p>Description: 数值处理工具类</p>
 *
 * @author Wangjianchun
 * @date 2017年6月29日
 */
public class NumberUtils {

    final static BigInteger HUNDRED = BigInteger.valueOf(100);


    private NumberUtils() {
    }

    public static double defaultDouble(Double value) {
        return (value == null ? 0D : value);
    }

    public static float defaultFloat(Float value) {
        return (value == null ? 0F : value);
    }

    public static long defaultLong(Long value) {
        return (value == null ? 0L : value);
    }

    public static int defaultInt(Integer value) {
        return (value == null ? 0 : value);
    }

    public static short defaultShort(Short value) {
        return (value == null ? 0 : value);
    }

    public static BigDecimal sqrt(BigDecimal number, int scale, int roundingMode) {
        if (number.compareTo(BigDecimal.ZERO) < 0)
            throw new ArithmeticException("sqrt with negative");
        BigInteger integer = number.toBigInteger();
        StringBuffer sb = new StringBuffer();
        String strInt = integer.toString();
        int lenInt = strInt.length();
        if (lenInt % 2 != 0) {
            strInt = '0' + strInt;
            lenInt++;
        }
        BigInteger res = BigInteger.ZERO;
        BigInteger rem = BigInteger.ZERO;
        for (int i = 0; i < lenInt / 2; i++) {
            res = res.multiply(BigInteger.TEN);
            rem = rem.multiply(HUNDRED);

            BigInteger temp = new BigInteger(strInt.substring(i * 2, i * 2 + 2));
            rem = rem.add(temp);

            BigInteger j = BigInteger.TEN;
            while (j.compareTo(BigInteger.ZERO) > 0) {
                j = j.subtract(BigInteger.ONE);
                if (((res.add(j)).multiply(j)).compareTo(rem) <= 0) {
                    break;
                }
            }

            res = res.add(j);
            rem = rem.subtract(res.multiply(j));
            res = res.add(j);
            sb.append(j);
        }
        sb.append('.');
        BigDecimal fraction = number.subtract(number.setScale(0, BigDecimal.ROUND_DOWN));
        int fracLen = (fraction.scale() + 1) / 2;
        fraction = fraction.movePointRight(fracLen * 2);
        String strFrac = fraction.toPlainString();
        for (int i = 0; i <= scale; i++) {
            res = res.multiply(BigInteger.TEN);
            rem = rem.multiply(HUNDRED);

            if (i < fracLen) {
                BigInteger temp = new BigInteger(strFrac.substring(i * 2, i * 2 + 2));
                rem = rem.add(temp);
            }

            BigInteger j = BigInteger.TEN;
            while (j.compareTo(BigInteger.ZERO) > 0) {
                j = j.subtract(BigInteger.ONE);
                if (((res.add(j)).multiply(j)).compareTo(rem) <= 0) {
                    break;
                }
            }
            res = res.add(j);
            rem = rem.subtract(res.multiply(j));
            res = res.add(j);
            sb.append(j);
        }
        return new BigDecimal(sb.toString()).setScale(scale, roundingMode);
    }

    public static BigDecimal sqrt(BigDecimal number, int scale) {
        return sqrt(number, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal sqrt(BigDecimal number) {
        int scale = number.scale() * 2;
        if (scale < 50)
            scale = 50;
        return sqrt(number, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算负面舆情占比
     *
     * @param totalOpinion         舆情总数
     * @param negativeOpinionCount 负面舆情数
     * @return 负面舆情占比，精确到小数点后两位
     */
    public static Double calculateNegativeProportion(int totalOpinion,
                                                     int negativeOpinionCount) {
        if (totalOpinion == 0 || negativeOpinionCount == 0) {
            return 0D;
        }
        BigDecimal tempTotalOpinion = new BigDecimal(totalOpinion + "");
        BigDecimal tempNegativeOpinionCount = new BigDecimal(negativeOpinionCount + "");
        Double result = tempNegativeOpinionCount.divide(tempTotalOpinion)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
        return result;
    }

}
