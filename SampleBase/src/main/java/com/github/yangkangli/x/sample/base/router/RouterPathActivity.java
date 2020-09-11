package com.github.yangkangli.x.sample.base.router;

public class RouterPathActivity {

    /**
     * Sample模块
     */
    public static class Sample {
        /**
         * 组名（这里与model名一致）
         */
        private static final String GROUP_NAME = "/SAMPLE";

        /**
         * 引导页面
         */
        public static final String PAGE_GUIDE = GROUP_NAME + "/PAGE_GUIDE";

        /**
         * 登录界面
         */
        public static final String PAGE_LOGIN = GROUP_NAME + "/PAGE_LOGIN";

        /**
         * 主界面
         */
        public static final String PAGE_MAIN = GROUP_NAME + "/PAGE_MAIN";
    }
}
