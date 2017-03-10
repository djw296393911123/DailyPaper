package com.djw.dailypaper.model.data;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class DaypaperData {

    /**
     * date : 20170310
     * stories : [{"images":["http://pic3.zhimg.com/f38e846a57428a07d18417b028f31716.jpg"],"type":0,"id":9276322,"ga_prefix":"031009","title":"会计、金融和法律，三者是一个环"},{"images":["http://pic3.zhimg.com/874cac2c5ccf54dbe5d8851b3d96fa26.jpg"],"type":0,"id":9277255,"ga_prefix":"031008","title":"又一位金融领域的巨人走了，他是当之无愧的学术标杆"},{"images":["http://pic1.zhimg.com/8e2c84e9243ffafd5c4a575b388ff47c.jpg"],"type":0,"id":9277212,"ga_prefix":"031007","title":"吃一块小时候爱的糖，是不是已经没那么甜了？"},{"images":["http://pic2.zhimg.com/8961b424bf4801a894568db743d89905.jpg"],"type":0,"id":9277265,"ga_prefix":"031007","title":"财大气粗的腾讯也在借钱，一借就是 20 亿美元"},{"images":["http://pic1.zhimg.com/7b1a0e50c76c791aaf6328c7476603b0.jpg"],"type":0,"id":9276623,"ga_prefix":"031007","title":"等一下，我们玩的狼人杀是盗版吗？"},{"images":["http://pic2.zhimg.com/1f88d2caae1a00c4b30b6a477d223fc5.jpg"],"type":0,"id":9273232,"ga_prefix":"031006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic2.zhimg.com/109f88853c2a24f4ce71fa8ff25817f1.jpg","type":0,"id":9277265,"ga_prefix":"031007","title":"财大气粗的腾讯也在借钱，一借就是 20 亿美元"},{"image":"http://pic4.zhimg.com/a0acd89245e670a37d99cb8fc9e72927.jpg","type":0,"id":9276623,"ga_prefix":"031007","title":"等一下，我们玩的狼人杀是盗版吗？"},{"image":"http://pic4.zhimg.com/287a31b1a17abefc08e7bf4cee5f754f.jpg","type":0,"id":9275902,"ga_prefix":"030913","title":"靠着直播，陌陌赚了不少钱，盈利能力超过了微博"},{"image":"http://pic1.zhimg.com/79f84b3e4deb2ca79a2e704196b89cf0.jpg","type":0,"id":9198534,"ga_prefix":"030914","title":"老王煎饼果子公司的上市之路"},{"image":"http://pic4.zhimg.com/59877e50e4539f361621386fcabdebd7.jpg","type":0,"id":9269115,"ga_prefix":"030916","title":"销魂美味 松花鸡腿"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic3.zhimg.com/f38e846a57428a07d18417b028f31716.jpg"]
         * type : 0
         * id : 9276322
         * ga_prefix : 031009
         * title : 会计、金融和法律，三者是一个环
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic2.zhimg.com/109f88853c2a24f4ce71fa8ff25817f1.jpg
         * type : 0
         * id : 9277265
         * ga_prefix : 031007
         * title : 财大气粗的腾讯也在借钱，一借就是 20 亿美元
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
