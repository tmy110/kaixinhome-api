package com.kaixin8848.home.web.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "UploadFile",description = "上传图片实体")
@Table(name = "upload_file")
public class UploadFile {
    /**
     * UUID 文件id
     */
    @ApiModelProperty(value = "ID" )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 类型 0本地 1网络
     */
    @ApiModelProperty(value = "类型 0本地 1网络" )
    private Byte type;

    /**
     * 组编号
     */
    @ApiModelProperty(value = "组编号" )
    @Column(name = "group_id")
    private String groupId;

    /**
     * 网络地址
     */
    @ApiModelProperty(value = "地址" )
    private String url;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型" )
    @Column(name = "file_type")
    private String fileType;

    /**
     * 名称、标题
     */
    @ApiModelProperty(value = "名称、标题" )
    private String title;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注" )
    private String remork;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取UUID 文件id
     *
     * @return id - UUID 文件id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置UUID 文件id
     *
     * @param id UUID 文件id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取组编号
     *
     * @return group_id - 组编号
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置组编号
     *
     * @param groupId 组编号
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取网络地址
     *
     * @return url - 网络地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置网络地址
     *
     * @param url 网络地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取文件类型
     *
     * @return file_type - 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取名称、标题
     *
     * @return title - 名称、标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置名称、标题
     *
     * @param title 名称、标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取备注
     *
     * @return remork - 备注
     */
    public String getRemork() {
        return remork;
    }

    /**
     * 设置备注
     *
     * @param remork 备注
     */
    public void setRemork(String remork) {
        this.remork = remork;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}