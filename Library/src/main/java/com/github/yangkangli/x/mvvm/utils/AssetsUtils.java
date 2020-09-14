package com.github.yangkangli.x.mvvm.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AssetsUtils {
    private AssetsUtils() {
        throw new UnsupportedOperationException("u can't initial here");
    }

    /**
     * 读取文本文件
     *
     * @param fileName
     * @return
     */
    public static String readTextFile(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(ContextUtils.getApplication().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
