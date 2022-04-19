package com.course.example.customarrayadapter;

public class SubjectData {
    private String SubjectName;
    private String Link;
    private int Image;

    public String getSubjectName(){
        return this.SubjectName;
    }

    public String getLink(){
        return this.Link;
    }

    public int getImage(){
        return this.Image;
    }

    public SubjectData(String subjectName, String link, int image) {
        this.SubjectName = subjectName;
        this.Link = link;
        this.Image = image;
    }
}
