package com.xzy.util.idcard;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 校验身份证的工具类
 * Created by hukaisheng on 16/8/28.
 */
public class IdcardValidatorUtil {

    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    private static final Map<String, String> cityMap = new HashMap<>();

    /** 台湾身份首字母对应数字 */
    private static final Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
    /** 香港身份首字母对应数字 */
    private static final Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
    static {
        cityMap.put("11", "北京");
        cityMap.put("12", "天津");
        cityMap.put("13", "河北");
        cityMap.put("14", "山西");
        cityMap.put("15", "内蒙古");

        cityMap.put("21", "辽宁");
        cityMap.put("22", "吉林");
        cityMap.put("23", "黑龙江");

        cityMap.put("31", "上海");
        cityMap.put("32", "江苏");
        cityMap.put("33", "浙江");
        cityMap.put("34", "安徽");
        cityMap.put("35", "福建");
        cityMap.put("36", "江西");
        cityMap.put("37", "山东");

        cityMap.put("41", "河南");
        cityMap.put("42", "湖北");
        cityMap.put("43", "湖南");
        cityMap.put("44", "广东");
        cityMap.put("45", "广西");
        cityMap.put("46", "海南");

        cityMap.put("50", "重庆");
        cityMap.put("51", "四川");
        cityMap.put("52", "贵州");
        cityMap.put("53", "云南");
        cityMap.put("54", "西藏");

        cityMap.put("61", "陕西");
        cityMap.put("62", "甘肃");
        cityMap.put("63", "青海");
        cityMap.put("64", "宁夏");
        cityMap.put("65", "新疆");

        cityMap.put("71", "台湾");
        cityMap.put("81", "香港");
        cityMap.put("82", "澳门");
        cityMap.put("91", "国外");

        twFirstCode.put("A", 10);
        twFirstCode.put("B", 11);
        twFirstCode.put("C", 12);
        twFirstCode.put("D", 13);
        twFirstCode.put("E", 14);
        twFirstCode.put("F", 15);
        twFirstCode.put("G", 16);
        twFirstCode.put("H", 17);
        twFirstCode.put("J", 18);
        twFirstCode.put("K", 19);
        twFirstCode.put("L", 20);
        twFirstCode.put("M", 21);
        twFirstCode.put("N", 22);
        twFirstCode.put("P", 23);
        twFirstCode.put("Q", 24);
        twFirstCode.put("R", 25);
        twFirstCode.put("S", 26);
        twFirstCode.put("T", 27);
        twFirstCode.put("U", 28);
        twFirstCode.put("V", 29);
        twFirstCode.put("X", 30);
        twFirstCode.put("Y", 31);
        twFirstCode.put("W", 32);
        twFirstCode.put("Z", 33);
        twFirstCode.put("I", 34);
        twFirstCode.put("O", 35);
        hkFirstCode.put("A", 1);
        hkFirstCode.put("B", 2);
        hkFirstCode.put("C", 3);
        hkFirstCode.put("R", 18);
        hkFirstCode.put("U", 21);
        hkFirstCode.put("Z", 26);
        hkFirstCode.put("X", 24);
        hkFirstCode.put("W", 23);
        hkFirstCode.put("O", 15);
        hkFirstCode.put("N", 14);
    }

    // 每位加权因子
    private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };


    /**
     * 验证所有的身份证的合法性
     * @param idcard 身份证号
     * @return 是否是合法身份证
     */
    public static boolean isValidatedAllIdcard(String idcard) {
        //简单判断是不是IDcard
        int length = idcard.length();
        if ((length == 15 || length == 18) && !isIdcard(idcard)) {
            return false;
        }
        //如果是15的
        if (idcard.length() == 15) {
            return isValidate15Idcard(idcard);
        }
        //如果是18位的
        if (idcard.length() == 18){
            return isValidate18Idcard(idcard);
        }

        //如果是台湾、香港、澳门这些特殊身份证号
        return validateSpecialIdcard(idcard);
    }

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idCard 身份证号
     * @return 是否是合法的18位身份证号
     */
    public static boolean isValidate18Idcard(String idCard) {
        //先校验正则是否匹配
        if(!is18Idcard(idCard)){
            return false;
        }
        //校验城市信息是否符合
        if(cityMap.get(idCard.substring(0, 2)) == null ){
            return false;
        }

        int[] prefix = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        int[] suffix = new int[]{ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
        int idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
        for(int i=0;i<17;i++){
            idCardWiSum+=Integer.parseInt(idCard.substring(i,i+1))*prefix[i];
        }

        int idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
        String idCardLast=idCard.substring(17);//得到最后一位身份证号码

        //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
        if (idCardMod == 2) {
            return idCardLast.equalsIgnoreCase("x");
        } else {
            //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
            return idCardLast.equals(suffix[idCardMod] + "");
        }
    }

    /**
     * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
     *
     * @param idcard 身份证号
     * @return 是否是合法的15位身份证号
     */
    public static boolean isValidate15Idcard(String idcard) {
        //校验省份信息
        if(cityMap.get(idcard.substring(0, 2)) == null ){
            return false;
        }

        // 是否全都为数字
        if (isDigital(idcard)) {
            String birthday = idcard.substring(6, 12);
            int year = Integer.parseInt(idcard.substring(6, 8));
            int month = Integer.parseInt(idcard.substring(8, 10));
            int day = Integer.parseInt(idcard.substring(10, 12));

            // 该身份证生出日期在当前日期之后时为假
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (birthdate == null || new Date().before(birthdate)) {
                return false;
            }

            // 判断是否为合法的年份
            GregorianCalendar curDay = new GregorianCalendar();
            int curYear = curDay.get(Calendar.YEAR);
            int year2bit = Integer.parseInt(String.valueOf(curYear)
                    .substring(2));

            // 判断该年份的两位表示法，小于50的和大于当前年份的，为假
            if ((year < 50 && year > year2bit)) {
                return false;
            }

            // 判断是否为合法的月份
            if (month < 1 || month > 12) {
                return false;
            }

            // 判断是否为合法的日期
            boolean mflag = false;
            curDay.setTime(birthdate);  //将该身份证的出生日期赋于对象curDay
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    mflag = (day >= 1 && day <= 31);
                    break;
                case 2: //公历的2月非闰年有28天,闰年的2月是29天。
                    if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                        mflag = (day >= 1 && day <= 29);
                    } else {
                        mflag = (day >= 1 && day <= 28);
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    mflag = (day >= 1 && day <= 30);
                    break;
                default:
                    mflag = (day >= 1 && day <= 31);
                    break;
            }
            if (!mflag) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard 身份证号
     * @return 18位身份证号
     */
    public static String convertIdcarBy15bit(String idcard) {
        String idcard17;
        // 非15位身份证
        if (idcard.length() != 15) {
            return null;
        }

        if (isDigital(idcard)) {
            // 获取出生年月日
            String birthday = idcard.substring(6, 12);
            Date birthdate;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                return null;
            }
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode;

            int bit[];

            // 将字符数组转为整型数组
            bit = converCharToInt(c);
            int sum17;
            sum17 = getPowerSum(bit);

            // 获取和值与11取模得到余数进行校验码
            checkCode = getCheckCodeBySum(sum17);
            // 获取不到校验位
            if (null == checkCode) {
                return null;
            }

            // 将前17位与第18位校验码拼接
            idcard17 += checkCode;
        } else { // 身份证包含数字
            return null;
        }
        return idcard17;
    }

    /**
     * 15位和18位身份证号码的基本数字和位数验校
     *
     * @param idcard 身份证号
     * @return 是否是身份证号
     */
    public static boolean isIdcard(String idcard) {
        return StringUtils.isNotBlank(idcard) && Pattern.matches(
                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idCard 身份证号
     * @return 是否18位
     */
    public static boolean is18Idcard(String idCard) {
        return Pattern.matches("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$", idCard);
    }

    /**
     * 数字验证
     *
     * @param str 字符串
     * @return 是否是数字组成的字符串
     */
    public static boolean isDigital(String str) {
        return StringUtils.isNotBlank(str) && str.matches("^[0-9]*$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit int数组
     * @return 加权值
     */
    public static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17 前17位身份真好
     * @return 校验位
     */
    public static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
            default:
                checkCode = null;
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c 字符数组
     * @return int数组
     * @throws NumberFormatException
     */
    public static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 验证10位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 身份证信息数组
     *         <p>
     *         [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false)
     *         若不是身份证件号码则返回null
     *         </p>
     */
    public static boolean validateSpecialIdcard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return false;
        }
        if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
            String char2 = idCard.substring(1, 2);
            if (!char2.equals("1") && !char2.equals("2")) {
                return false;
            }
            return validateTWCard(idCard);
        } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
            return true;
        } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
            return validateHKCard(idCard);
        } else {
            return false;
        }
    }

    /**
     * 验证台湾身份证号码
     *
     * @param idCard
     *            身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateTWCard(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.parseInt(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.parseInt(end) ? true : false;
    }

    /**
     * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
     * <p>
     * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
     * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
     * </p>
     * <p>
     * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
     * </p>
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateHKCard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum;
        if (card.length() == 9) {
            sum = (card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 9
                    + (card.substring(1, 2).toUpperCase().toCharArray()[0] - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (card.substring(0, 1).toUpperCase().toCharArray()[0] - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.parseInt(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.parseInt(end);
        }
        return (sum % 11 == 0) ? true : false;
    }

}


