package com.changlu.java;


import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @Description:
 * @Author: changlu
 * @Date: 4:27 PM
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Test {

    private static int i = 1;

    public static void main(String[] args) throws Exception {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
//        while (true) {
//            Thread.sleep(1500);
//            sendPost();
//        }
    }

    public static void sendPost() throws Exception{
        URL url = new URL("https://api.live.bilibili.com/msg/send");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("authority", "api.live.bilibili.com");
        httpConn.setRequestProperty("pragma", "no-cache");
        httpConn.setRequestProperty("cache-control", "no-cache");
        httpConn.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");
        httpConn.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        httpConn.setRequestProperty("content-type", "multipart/form-data; boundary=----WebKitFormBoundaryYD642S7BUw8IO9r7");
        httpConn.setRequestProperty("accept", "*/*");
        httpConn.setRequestProperty("origin", "https://live.bilibili.com");
        httpConn.setRequestProperty("sec-fetch-site", "same-site");
        httpConn.setRequestProperty("sec-fetch-mode", "cors");
        httpConn.setRequestProperty("sec-fetch-dest", "empty");
        httpConn.setRequestProperty("referer", "https://live.bilibili.com/21430296?spm_id_from=333.337.search-card.all.click");
        httpConn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
        httpConn.setRequestProperty("cookie", "buvid3=5D1FD8E1-40A1-F00B-9745-483C7B87B1AB46787infoc; i-wanna-go-back=-1; _uuid=7178810102-CE66-B7110-102E9-C8C1A5D26CCB00430infoc; buvid4=8EBB72A7-1855-DCC1-4B43-264FB8B7394847271-022031315-svv/lSmXe1GMVQPw4ElE0A%3D%3D; buvid_fp_plain=undefined; buvid_fp=dbe1b4e808c0ae1ed51926cbe32eb2b7; DedeUserID=481905751; DedeUserID__ckMd5=3823872784ca6868; rpdid=|(J|)Ru~lJJ~0J'uYR~ulY)uk; b_ut=5; CURRENT_BLACKGAP=0; LIVE_BUVID=AUTO1216475764679543; nostalgia_conf=-1; fingerprint=0a09e00eb537df1c4c0af6876bb10a61; fingerprint3=f6f164c7dbca1fe2a1e10ea5de342e1b; blackside_state=0; CURRENT_QUALITY=80; is-2022-channel=1; hit-dyn-v2=1; Hm_lvt_8a6e55dbd2870f0f5bc9194cddf32a02=1657189603,1657807392,1658664560,1659444802; go_old_video=1; PVID=1; bsource=search_baidu; innersign=1; SESSDATA=eb4de722%2C1676284285%2Cacf63%2A81; bili_jct=d38ea8df6019e647632645a0f814cd43; sid=6dyq5dku; CURRENT_FNVAL=4048; b_lsid=DC2E7AF2_182AC01EAA6; b_timer=%7B%22ffp%22%3A%7B%22333.788.fp.risk_5D1FD8E1%22%3A%22182ABC8B1C2%22%2C%22333.1007.fp.risk_5D1FD8E1%22%3A%22182A55E26C1%22%2C%22333.337.fp.risk_5D1FD8E1%22%3A%22182AC0CCD96%22%2C%22333.976.fp.risk_5D1FD8E1%22%3A%22182901EDB12%22%2C%22444.8.fp.risk_5D1FD8E1%22%3A%22182AC0D16E6%22%2C%22333.999.fp.risk_5D1FD8E1%22%3A%22182ABCF8C76%22%2C%22333.937.fp.risk_5D1FD8E1%22%3A%221829B1FB93F%22%2C%22333.1193.fp.risk_5D1FD8E1%22%3A%22182AB5B9CA3%22%2C%22333.880.fp.risk_5D1FD8E1%22%3A%221829F697017%22%2C%22333.851.fp.risk_5D1FD8E1%22%3A%22182AB5B9A4D%22%7D%7D; bp_video_offset_481905751=695385763946168300");

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        i++;
        if (i > 3) {
            i = 0;
        }
        if (i % 3 == 1) {
            writer.write("------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"bubble\"\r\n\r\n0\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"msg\"\r\n\r\n\u51B2\u51B2\u51B2\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"color\"\r\n\r\n16777215\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"mode\"\r\n\r\n1\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"fontsize\"\r\n\r\n25\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"rnd\"\r\n\r\n1660743913\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"roomid\"\r\n\r\n21430296\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"csrf\"\r\n\r\nd38ea8df6019e647632645a0f814cd43\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7\r\nContent-Disposition: form-data; name=\"csrf_token\"\r\n\r\nd38ea8df6019e647632645a0f814cd43\r\n------WebKitFormBoundaryYD642S7BUw8IO9r7--\r\n");
        }else if (i % 3 == 2) {
            writer.write("------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"bubble\"\r\n\r\n0\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"msg\"\r\n\r\n\u62BD\u4E00\u4E0B\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"color\"\r\n\r\n16777215\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"mode\"\r\n\r\n1\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"fontsize\"\r\n\r\n25\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"rnd\"\r\n\r\n1660749458\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"roomid\"\r\n\r\n21430296\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"csrf\"\r\n\r\nd38ea8df6019e647632645a0f814cd43\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5\r\nContent-Disposition: form-data; name=\"csrf_token\"\r\n\r\nd38ea8df6019e647632645a0f814cd43\r\n------WebKitFormBoundaryELRoAgMLmMJ1dWG5--\r\n");
        }
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();
        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println("发送弹幕成功！");
    }

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind u = new UnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (grid[i][j] == 0) {
                    u.area[index] = 0;
                } else {
                    u.maxArea = Math.max(u.maxArea, 1);
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        u.merge(index, (i - 1) * n + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        u.merge(index, i * n + j - 1);
                    }
                }
            }
        }
        return u.maxArea;
    }

    //并查集
    public class UnionFind {
        int fa[];
        int rank[];
        int area[];
        int maxArea = 0;
        public UnionFind(int n) {
            fa = new int[n];
            rank = new int[n];
            area = new int[n];
            for(int i = 0; i < n; ++i){
                fa[i] = i;
                rank[i] = 1;
                area[i] = 1;
            }
        }
        public int find(int x) {
            return fa[x] == x ? x : (fa[x] = find(fa[x]));
        }
        public void merge(int p, int q) {
            int pRoot = find(p), qRoot = find(q);
            if (pRoot != qRoot) {
                if (rank[pRoot] > rank[qRoot]) {
                    fa[qRoot] = pRoot;
                    area[pRoot] += area[qRoot];
                } else if (rank[pRoot] < rank[qRoot]) {
                    fa[pRoot] = qRoot;
                    area[qRoot] += area[pRoot];
                } else {
                    fa[pRoot] = qRoot;
                    rank[qRoot]++;
                    area[qRoot] += area[pRoot];
                }
                maxArea = Math.max(maxArea, Math.max(area[pRoot], area[qRoot]));
            }
        }
    }
}

