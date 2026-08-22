package com.linksphere.user.dto;

import java.util.List;

public class FeedResponse {

    private List<PostResponse> posts;
    private int page;
    private int size;
    private boolean hasNext;

    public FeedResponse() {
    }

    public FeedResponse(List<PostResponse> posts,
                        int page,
                        int size,
                        boolean hasNext) {

        this.posts = posts;
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}