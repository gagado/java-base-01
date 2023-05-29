package com.example.base.数据结构和算法.leetcode.回溯算法.全排列;

import java.util.LinkedList;
import java.util.List;

class Solution {

    public static List<List<Integer>> res = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = permute(nums);
        System.out.println(lists);
    }

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    public static List<List<Integer>> permute(int[] nums) {

        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] used = new boolean[nums.length];
        
        //backtrack(nums, track, used);
        track(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
    // 结束条件：nums 中的元素全都在 track 中出现
    public static void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        // 为什么要for循环
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择 保证track中已经有了这个元素
            if (used[i]) {
                // nums[i] 已经在 track 中，跳过
                continue;
            }
            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树  递归之前做选择 递归之后撤销选择
            backtrack(nums, track, used);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }


    public static void track(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            // new LinkedList(track) 传递的是引用
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 判断是否在track
            if (track.contains(nums[i])) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            track(nums, track);
            // 撤销选择
            track.removeLast();
        }
    }
}