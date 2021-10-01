package com.liu.ob.ob_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(value = "up_data")
public class UpData {
    private Long mid;
    //粉丝数
    private Long fans;
    //关注他人数
    private Integer attention;
    //视频稿件数
    private Integer archive;
    //文章数
    private Integer article;
    //视频播放量
    private Long archiveView;
    //专栏文章播放量
    private Long articleView;
    //获赞次数
    private Long like;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;
}
