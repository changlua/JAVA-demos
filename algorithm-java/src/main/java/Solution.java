class Solution {
    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    //左右指针指向初始、末尾，若是左<右，即左++，否则右--
    public static double findMaxAverage(int[] nums, int k) {
        double aver = 0.0;
        int l = 0, r = nums.length - 1;
        double sum = 0.0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
        }
        double max = 0.0;
        while (l < r) {
            aver = sum / (r - r + 1);
            max = Math.max(max, aver);
            if (nums[l] > nums[r]) {
                sum -= nums[r --];
            }else {
                sum -= nums[l ++];
            }
        }
        return max;
    }
}
