package binarysearch;

/**
 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

 每行中的整数从左到右按升序排列。
 每行的第一个整数大于前一行的最后一个整数。
 示例 1:

 输入:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 输出: true
 示例 2:

 输入:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 输出: false
 **/
public class MatrixSearch {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0){
            return false;
        }
        int n = matrix[0].length;
        int m = matrix.length;
        int left = 0;
        int right = n*m-1;

        while(left<=right){
            int mid = left+(right-left)/2;
            if(matrix[mid/n][mid%n]==target){
                return true;
            }else if(matrix[mid/n][mid%n] > target){
                right = mid-1;
            }else{
                left = mid+1;

            }
        }

        return false;

    }


    public static void main(String[] args) {
        int [][]a=new int[][]{{1},{3},{5}};
        int target=5;
        MatrixSearch m=new MatrixSearch();
        m.searchMatrix(a,target);
    }
}
