package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
组合总和
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

        candidates 中的每个数字在每个组合中只能使用一次。

        说明：

        所有数字（包括目标数）都是正整数。
        解集不能包含重复的组合。
        示例 1:

        输入: candidates = [10,1,2,7,6,1,5], target = 8,
        所求解集为:
        [
        [1, 7],
        [1, 2, 5],
        [2, 6],
        [1, 1, 6]
        ]
        示例 2:

        输入: candidates = [2,5,2,1,2], target = 5,
        所求解集为:
        [
        [1,2,2],
        [5]
        ] */
public class CombinationSum {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(candidates);
        if(candidates[0]>target) return result;
        int length=candidates.length;
        int sum=0;
        for(int i=0;i<length;i++){
            sum=sum+candidates[i];
        }
        if(sum<target)
            return result;
        List<Integer> list=new ArrayList<>();
        backtracking(result,list,candidates,target,0);//result存放结果，list为每一个
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> list,int[] candidateds,int target,int start){
        if(0==target){
            result.add(new ArrayList<>(list));
            return;
        }
        else if(0>target)
            return;
        else {
            int pre = -1;  //去重复
            for(int i=start;i<candidateds.length;i++) {
                if(candidateds[i] == pre)
                    continue;
                pre = candidateds[i];
                list.add(candidateds[i]);
                backtracking(result,list,candidateds,target-candidateds[i],i);
                list.remove(list.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        int[] array=new int[]{1, 2, 3, 4, 5, 6, 7,8,9};
        CombinationSum com=new CombinationSum();
        List<List<Integer>> result=new ArrayList<>();
        result=com.combinationSum2(array,10);
        System.out.println(result);

    }
}
