package com.liu.ob.ob_backend.core.enumeration;

import lombok.Getter;

@Getter
public enum UpSortEnum {
    FANS(0, "c_fans"),
    ARCHIVE_VIEW(1, "c_archive_view"),
    ARTICLE_VIEW(2, "c_article_view"),
    LIKE(3, "cLike");

    private Integer flag;
    private String key;

    UpSortEnum(Integer flag, String key) {
        this.flag = flag;
        this.key = key;
    }

    public static String getKeyByFlag(Integer flag) {
        switch (flag) {
            case 1:
                return ARCHIVE_VIEW.getKey();
            case 2:
                return ARTICLE_VIEW.getKey();
            case 3:
                return LIKE.getKey();
            default:
                return FANS.getKey();
        }
    }
}
