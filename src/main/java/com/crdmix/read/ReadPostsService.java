package com.crdmix.read;

public interface ReadPostsService {

    public static final String WALL = "wall";

    void readUserTimeLine(String user);

    void readUserWall(String user);
}
