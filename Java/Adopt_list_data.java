package org.tecttown.aniverse;

public class Adopt_list_data {


    int no;
    //String species;
    //String center_name;
    //String sex;
    String imgPath;

    public Adopt_list_data(int no,String imgPath) {
        this.no = no;
        //this.name = name;
        //this.msg = msg;
        this.imgPath = imgPath;
        //this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}