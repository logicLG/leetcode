package backtrack;

import java.util.List;

/*
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.

 */
public class WordSearch {
    int[][] d = new int[][]{{-1, 0}, {0,-1}, {1, 0}, {0, 1}};
    boolean[][] visited;
    boolean  result=false;
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        if(word.length() == 0) return true;
        if(board.length == 0 || board[0].length == 0) return false;
        m = board.length;
        n = board[0].length;
        if(m*n<word.length()){
            return false;
        }
        visited=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==word.charAt(0)) {
                    visited[i][j]=true;
                    backtracking(board, i, j, word, 1);
                    visited[i][j]=false;
                }
            }
        }
        return result;
    }
    private boolean inArea(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    private void backtracking(char[][] board,int startx,int starty,String word,int start){
        if(result == true){//大量减少工作
            return;
        }
        if(start==word.length()){
            result =true;
            return ;
        }else {

            for(int i=0;i<4;i++){
                int newX=startx+d[i][0];
                int newY=starty+d[i][1];
                if(inArea(newX,newY)&&!visited[newX][newY]){
                    if(board[newX][newY]==word.charAt(start)) {
                        visited[newX][newY]=true;
                        backtracking(board, newX, newY, word, start + 1);
                        visited[newX][newY]=false;
                    }
                }
            }

        }

    }

    public static void main(String[] args) {
        char[][] board ={{'b','b'},{'a','b'},{'b','a'}};
        WordSearch a= new WordSearch();
        System.out.println(a.exist(board,"a"));
    }
}
