package com.lyao.mo.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author lyao
 * @date 2019/6/28 8:54
 * @description
 */
public class RandomName {

    public static final String NICK_NAME_SEPARATOR = "@#$%^&*<>";
    /**
     * 随机返回a和b其中一个数
     */
    public static int randomAB(int a, int b){
        return (int)((Math.random()*Math.abs(a-b))+ Math.min(a, b));
    }

    /**
     * 生成姓氏
     * @throws IOException
     */
    private static String getSurname() throws IOException{
        List<String> fistNames = loadNames("/systemFiles/surname");
        return fistNames.get(randomAB(0,fistNames.size()));
    }

    /**
     * @生成名字
     * @return
     * @throws IOException
     */
    private static String getName(boolean male) throws IOException {
        List<String> names = loadNames("/systemFiles/name");
        return names.get(randomAB(0,names.size()));
    }

    /**
     * 读取百家姓文件，获取名字
     * @return
     * @throws IOException
     */
    private static List<String> loadNames(String path) throws IOException{
        InputStream in = RandomName.class.getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
        //文件读取
        String line;
        //结果集合
        List<String> result = new ArrayList<String>(200);
        while((line=br.readLine())!=null){
            line = line.trim();
            //使用空白字符分割
            String[] names = line.split("\\s+");
            result.addAll(Arrays.asList(names));
        }
        return result;
    }

    /**
     * 自动生成昵称
     * @param mobile
     * @return
     */
    public static String getNickName(String mobile){
        String name = null;
        try {
            name = RandomName.getSurname()+RandomName.getName(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        String sb = String.valueOf(NICK_NAME_SEPARATOR.charAt(random.nextInt(NICK_NAME_SEPARATOR.length())));
        return name + sb + mobile.substring(7);
    }

    public static void main(String[] args) {

        System.out.println(RandomName.getNickName(""));
    }
}
