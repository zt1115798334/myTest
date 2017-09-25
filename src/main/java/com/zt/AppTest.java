package com.zt;

import com.google.common.collect.Lists;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.zt.entity.SecondCategory;
import com.zt.entity.User;
import com.zt.utils.DateUtils;
import com.zt.utils.ExcelUtil;
import com.zt.utils.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zhangtong on 2017/6/28.
 */
public class AppTest {

    public static Integer randomGetInt() {
        int index = (new Random().nextInt(100));
        return index;
    }

    public static Double randomGetDouble() {
        Double nextDouble = (new Random().nextDouble()) + randomGetInt();
        BigDecimal bg = new BigDecimal(nextDouble);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    @Test
    public void test1() {
        System.out.println(DateUtils.formatDateTime(DateUtils.getStartDateTimeOfDay(DateUtils.paramesDateAddMonth(4, "2017-02-03"))));
    }

    @Test
    public void test2() {
        System.out.println(DateUtils.formatDateTime(DateUtils.getEndDateTimeOfDay(DateUtils.paramesDateAddDay(-4, "2017-02-03"))));
    }

    @Test
    public void test3() {
        System.out.println(randomGetDouble());
    }

    @Test
    public void test4() throws Base64DecodingException {
        String fozuStr = "ICAgICAgICAgICAgICAgICAgIF9vb09vb18KICAgICAgICAgICAgICAgICAgbzg4ODg4ODhvCiAgICAgICAgICAgICAgICAgIDg4IiAuICI4OAogICAgICAgICAgICAgICAgICAofCAtXy0gfCkKICAgICAgICAgICAgICAgICAgT1wgID0gIC9PCiAgICAgICAgICAgICAgIF9fX18vYC0tLSdcX19fXwogICAgICAgICAgICAgLicgIFxcfCAgICAgfC8vICBgLgogICAgICAgICAgICAvICBcXHx8fCAgOiAgfHx8Ly8gIFwKICAgICAgICAgICAvICBffHx8fHwgLTotIHx8fHx8LSAgXAogICAgICAgICAgIHwgICB8IFxcXCAgLSAgLy8vIHwgICB8CiAgICAgICAgICAgfCBcX3wgICcnXC0tLS8nJyAgfCAgIHwKICAgICAgICAgICBcICAuLVxfXyAgYC1gICBfX18vLS4gLwogICAgICAgICBfX19gLiAuJyAgLy0tLi0tXCAgYC4gLiBfXwogICAgICAuIiIgJzwgIGAuX19fXF88fD5fL19fXy4nICA+JyIiLgogICAgIHwgfCA6ICBgLSBcYC47YFwgXyAvYDsuYC8gLSBgIDogfCB8CiAgICAgXCAgXCBgLS4gICBcXyBfX1wgL19fIF8vICAgLi1gIC8gIC8KPT09PT09YC0uX19fX2AtLl9fX1xfX19fXy9fX18uLWBfX19fLi0nPT09PT09CiAgICAgICAgICAgICAgICAgICBgPS0tLT0nCl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXl5eXgogICAgICAgICAgICAgICAgIOS9m+elluS/neS9kSAgICAgICDmsLjml6BCVUc=";
        byte[] decode = Base64.decode(String.valueOf(fozuStr.toCharArray()));
        System.out.println("\n" + new String(decode));
    }

    @Test
    public void test5() {
        SecondCategory sc = new SecondCategory();
        sc.setFirstCategory("fc1");
        sc.setSecondCategory("停业破产");
        sc.setKeywords("停业 破产 倒闭 关门 关闭门店");
        JSONObject jsonObject = new JSONObject(sc);
        System.out.println(jsonObject.toString());
    }

    List<String> filePath = Lists.newArrayList();

    @Test
    public void test6() {
        List<String> filePath = traverseFolder("E:\\采集数据\\供应链金融语科");
        System.out.println("----------------------");
        filePath.forEach(System.out::println);
    }

    public List<String> traverseFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return filePath;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        filePath.add(file2.getAbsolutePath());
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return filePath;
    }


    @Test
    public void test7() {
        System.out.println(new BigDecimal(7.75626226827717721007834165110625E-11).toPlainString());
    }

    @Test
    public void test8() {
        List<Double> doubles = Arrays.asList(0.00000264355555668061, 0.000020257484440648755);
        List<BigDecimal> pressureIndexs = doubles.stream()
                .map(String::valueOf).map(BigDecimal::new).collect(Collectors.toList());
        System.out.println("pressureIndexs = " + getStandardDiviation(pressureIndexs));

    }

    private static BigDecimal getStandardDiviation(List<BigDecimal> value) {
        BigDecimal dAve = getAverageValue(value); //求平均值
        BigDecimal valSize = new BigDecimal(value.size());
        BigDecimal dVar = value.stream().reduce(BigDecimal.ZERO, (sum, item) -> sum.add(item.subtract(dAve).pow(2)));
        BigDecimal d = dVar.divide(valSize);
        System.out.println("d = " + d.toPlainString());
        return NumberUtils.sqrt(d);
    }

    /**
     * 算术平均数
     *
     * @param value
     * @return
     */
    private static BigDecimal getAverageValue(List<BigDecimal> value) {
        BigDecimal valSum = value.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valSize = new BigDecimal(value.size());
        return valSum.divide(valSize, 10, BigDecimal.ROUND_HALF_UP);
    }

    @Test
    public void test80() throws IOException {
        String xiao = "保定石";
        System.out.println(xiao != null && (xiao.contains("保定") || xiao.contains("石家庄")));
    }


}

