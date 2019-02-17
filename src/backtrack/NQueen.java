package backtrack;

import java.util.ArrayList;
import java.util.List;

/*
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

示例:

输入: 4
输出: [
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。
 */
public class NQueen {
    List<List<String>> result=new ArrayList<List<String>>();
    //每一次新添加皇后都需要进行判断，这里每一行都有一个皇后
    private boolean place(List<Integer> list){
        int size=list.size();
        for(int j=0;j<size-1;j++){
            if(Math.abs(j-size+1)==Math.abs(list.get(j)-list.get(size-1))||list.get(j)==list.get(size-1))
                return false;
        }
        return true;
    }
    public List<List<String>> solveNQueens(int n) {
        List<Integer> list=new ArrayList<>();
        backtracking(n,0,list);
        return result;
    }

    private void backtracking(int n,int start,List<Integer> list){
        if(start==n) {
            String item="";
            for(int i=0;i<n;i++){
                item=item+".";
            }
            List<String> res=new ArrayList<>();
            for(Integer i:list){
                String s=item.substring(0,i)+"Q"+item.substring(i+1);
                res.add(s);
            }
            result.add(new ArrayList<>(res));
        }
        else{
            for(int i=0;i<n;i++){
                list.add(i);
                if(place(list)){
                    backtracking(n,start+1,list);
                    list.remove(list.size()-1);
                }else {
                    list.remove(list.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        NQueen n=new NQueen();
        System.out.println(n.solveNQueens(4));
    }
}
