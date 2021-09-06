package com.example.myapplication.fragments.bean;

import java.util.List;

/**
 * Create By shaodong on 2021/8/10 14:39
 */
public class Assort  {
    private long id ;
    private String subjectName;
    private long parentId;
    private List<Assort> childSubjectList;
    private int level;
    private Assort child;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<Assort> getChildSubjectList() {
        return childSubjectList;
    }

    public void setChildSubjectList(List<Assort> childSubjectList) {
        this.childSubjectList = childSubjectList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Assort getChild() {
        return child;
    }

    public void setChild(Assort child) {
        this.child = child;
    }
}
