package com.djw.dailypaper.model.data.sports;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class SportsData {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-12-04 13:00","title":"格林：理解科尔吸食大麻 不过我从没吸过","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161204/1-161204120131.jpg","url":"http://www.51tyw.com/nba/2421.html"},{"ctime":"2016-12-04 00:00","title":"三分纪录延续！火箭队连续19场比赛命中10+三分球","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161203/1-161203233J3.jpg","url":"http://www.51tyw.com/nba/2417.html"},{"ctime":"2016-12-04 00:00","title":"詹姆斯谈三连败：是时候紧起来了 必须打得男人点","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161203/1-161203234010.jpg","url":"http://www.51tyw.com/nba/2418.html"},{"ctime":"2016-12-03 12:00","title":"骑士输赢都靠三分？那还要詹姆斯做什么？","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161203/1-161203104344.jpg","url":"http://www.51tyw.com/nba/2407.html"},{"ctime":"2016-12-03 00:00","title":"公牛vs骑士直播看点：詹伟兄弟对决","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161202/1-161202221205.jpg","url":"http://www.51tyw.com/nba/2395.html"},{"ctime":"2016-12-02 22:00","title":"火箭和勇士联手创NBA三分纪录","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161202/1-161202214212.jpg","url":"http://www.51tyw.com/nba/2392.html"},{"ctime":"2016-12-02 22:00","title":"巴克利：勇士打得像女式篮球，太软了！","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161202/1-161202215032.jpg","url":"http://www.51tyw.com/nba/2394.html"},{"ctime":"2016-12-02 20:00","title":"詹姆斯完成月最佳球员4连霸的壮举！","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161202/1-1612021PI0.jpg","url":"http://www.51tyw.com/nba/2388.html"},{"ctime":"2016-12-01 00:00","title":"威少再下三双战书！詹皇会不会先认怂？","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161128/1-16112Q13251.jpg","url":"http://www.51tyw.com/nba/2374.html"},{"ctime":"2016-12-01 00:00","title":"骑士这样的防守想夺冠？也许卫冕只是空谈！","description":"NBA新闻","picUrl":"http://www.51tyw.com/uploads/allimg/161130/1-161130222229.jpg","url":"http://www.51tyw.com/nba/2375.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2016-12-04 13:00
         * title : 格林：理解科尔吸食大麻 不过我从没吸过
         * description : NBA新闻
         * picUrl : http://www.51tyw.com/uploads/allimg/161204/1-161204120131.jpg
         * url : http://www.51tyw.com/nba/2421.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
