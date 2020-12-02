package org.luojs.log_catch;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类的功能描述
 *
 * @author ylb
 * @date 2020/1/5 17:59
 */
public class LogTest2 {
    static Pattern p = Pattern.compile("[\\S]+ (\\S{4}-\\S{2}-\\S{2} \\S{2}:\\S{2}:\\S{2}) \\[[\\S ]+\\] \\S+  [\\S0-9 \\-]+ - 接口打印，梳理时间大于500毫秒，url:([\\/\\S\\._\\-]+), startTime:(\\d+), endTime:(\\d+)，time:(\\d+)");

    static Map<String, Integer> totalCnt = new HashMap<>();
    static Map<String, Integer> totalTime = new HashMap<>();

    public static void main(String[] args) {
        File file = new File("F:\\需求文档\\z服务端日志");

        System.out.println(file.getAbsolutePath());

        recursionFile(file);

        File file1 = new File("F:\\需求文档\\y超时日志统计"+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))+".txt");
        try {
            boolean exists = file1.exists();
            System.out.println(file1.getAbsolutePath() + "是否存在:" + exists);
            if (!exists) {
                file1.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            for (Map.Entry<String, Integer> e: totalCnt.entrySet()) {
                Integer integer = totalTime.get(e.getKey());
                bw.newLine();
                StringBuilder str = new StringBuilder();
                str.append(e.getValue());
                if (e.getValue() < 100) {
                    str.append(" ");
                }
                if (e.getValue() < 1000) {
                    str.append("\t");
                }
                Integer a = integer / e.getValue();
                str.append("\t\t\t" + a);
                if (a < 1000) {
                    str.append(" ");
                }
                str.append("\t\t" + e.getKey());
                bw.write(str.toString());
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recursionFile(File file) {
        System.out.println("进入目录：" + file.getName());
        String absolutePath = file.getAbsolutePath();
        absolutePath = absolutePath.replace("F:\\需求文档\\z服务端日志", "F:\\需求文档\\y超时日志统计");
        File dire = new File(absolutePath);
        if (!dire.exists()) {
            dire.mkdir();
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            if (file1.isDirectory()) {
                recursionFile(file1);
            } else {
                machFile(file1);
            }
        }
    }

    private static void machFile(File file) {
        System.out.println("进入文件：" + file.getName());
        String absolutePath = file.getAbsolutePath();
        absolutePath = absolutePath.replace("F:\\需求文档\\z服务端日志", "F:\\需求文档\\y超时日志统计");
        try {
            File dire = new File(absolutePath);
            if (!dire.exists()) {
                dire.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(dire);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            Map<String, Integer> map = new HashMap<>();
            Map<String, Integer> map2 = new HashMap<>();


            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String s;
            while ((s = br.readLine()) != null) {
                Matcher matcher = p.matcher(s);
                if (matcher.find()) {
                    StringBuilder str = new StringBuilder();
                    str.append(matcher.group(1)).append("\t");
                    String path = matcher.group(2);
                    str.append(matcher.group(3)).append("\t");
                    str.append(matcher.group(4)).append("\t");
                    Integer timeStr = Integer.parseInt(matcher.group(5));
                    str.append(timeStr);
                    if (timeStr < 1000) {
                        str.append(" ");
                    }
                    str.append("\t").append(path);
                    bw.newLine();
                    bw.write(str.toString());
                    Integer count = map.get(path);
                    if (count == null) {
                        count = 1;
                    } else {
                        count++;
                    }
                    map.put(path, count);

                    Integer time = map2.get(path);
                    if (time == null) {
                        time = timeStr;
                    } else {
                        time += timeStr;
                    }
                    map2.put(path, time);

                    Integer count1 = totalCnt.get(path);
                    if (count1 == null) {
                        count1 = 1;
                    } else {
                        count1++;
                    }
                    totalCnt.put(path, count1);

                    Integer time1 = totalTime.get(path);
                    if (time1 == null) {
                        time1 = timeStr;
                    } else {
                        time1 += timeStr;
                    }
                    totalTime.put(path, time1);
                }
            }
            StringBuilder title = new StringBuilder();
            title.append("超时次数\t平均超时时长\t超时路径");
            bw.newLine();
            bw.newLine();
            bw.write(title.toString());

            for (Map.Entry<String, Integer> e: map.entrySet()) {
                Integer integer = map2.get(e.getKey());
                bw.newLine();
                StringBuilder str = new StringBuilder();
                str.append(e.getValue());
                if (e.getValue() < 100) {
                    str.append(" ");
                }
                if (e.getValue() < 1000) {
                    str.append("\t");
                }
                Integer a = integer / e.getValue();
                str.append("\t\t\t" + a);
                if (a < 1000) {
                    str.append(" ");
                }
                str.append("\t\t" + e.getKey());
                bw.write(str.toString());
            }
            br.close();
            bw.close();
        } catch (Exception e) {

        }

    }
}
