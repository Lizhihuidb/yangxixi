package com.tjl.yangxixi.utils;

import java.util.Date;

/**
 * Created by dingbohua on 2017/5/15.
 */
public class uitl {


    /**
     * 计算每月多少天
     *
     * @param month
     * @param year
     */
    public String calDays(String year, String month) {

        boolean leayyear = false;
        if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (Integer.parseInt(month)) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return 31+"";
                case 2:
                    if (leayyear) {
                        return 29+"";
                    } else {
                        return 28+"";
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    return 30+"";
            }
        }
        if (year.equals( new Date().getDay()) && month .equals( new Date().getMonth())) {
            return new Date().getDay()+"";
        }
        return "";
    }
}
