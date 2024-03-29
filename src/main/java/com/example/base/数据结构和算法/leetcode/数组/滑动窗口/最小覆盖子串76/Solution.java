package com.example.base.数据结构和算法.leetcode.数组.滑动窗口.最小覆盖子串76;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xue
 * @date 2023/4/9 14:28
 */
public class Solution {

    public static void main(String[] args) {

        String s = "ABCBECODEBANC";
        String t = "ABC";
        String window = minWindow1(s, t);
        System.out.println(window);

    }

    public static String minWindow1(String s, String t) {

        Map<Character, Integer> need = Maps.newHashMap();
        Map<Character, Integer> window = Maps.newHashMap();

        for (char c : t.toCharArray()) {
            need.put( c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        int start = 0; int len = Integer.MAX_VALUE;

        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            right++;
            // 扩大窗口
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 更新最小覆盖串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d是要移出窗口的字符
                char d = s.charAt(left);
                left++;

                // 进行窗口内数据的一系列的更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    public static String minWindow(String s, String t) {
        // 用于记录需要的字符和窗口中的字符及其出现的次数
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // 统计 t 中各字符出现次数
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0; // 窗口中满足需要的字符个数
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 扩大窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++; // 只有当 window[c] 和 need[c] 对应的出现次数一致时，才能满足条件，valid 才能 +1
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 缩小窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--; // 只有当 window[d] 内的出现次数和 need[d] 相等时，才能 -1
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start + len);
    }

}
