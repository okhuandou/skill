package com.mybatis.model;

public class LongTime {
    private Integer id;

    private String name;

    private String longtimeaim;

    private String finished;

    private String addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLongtimeaim() {
        return longtimeaim;
    }

    public void setLongtimeaim(String longtimeaim) {
        this.longtimeaim = longtimeaim == null ? null : longtimeaim.trim();
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished == null ? null : finished.trim();
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }
}