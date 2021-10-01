package com.liu.ob.ob_backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Up {
    //用户id
    private Long mid;
    //昵称
    private String name;
    //头像
    private String face;
    //性别
    private String sex;
    //官方给的认证信息
    private String official;
    //等级
    private Integer level;
    //观察间隔
    private Integer obInterval;
    //是否锁定频率
    private Boolean forceFocus;
    //是否处于观测状态
    private Boolean focus;
    @Field("c_fans")
    private Integer cFans;
    @Field("c_archive_view")
    private Long cArchiveView;
    @Field("c_article_view")
    private Long cArticleView;
    @Field("c_like")
    private Long cLike;
    //up主最新的数据，存进去是为了减少一次数据查询
    private UpData cData;

    public Up(Long mid) {
        this.mid = mid;
        this.focus = true;
    }
}
