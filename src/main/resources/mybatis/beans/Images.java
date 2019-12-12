package mybatis.beans;

import java.util.Date;

public class Images {
    private Integer id;

    private String title;

    private String type;

    private Long size;

    private String serverPosition;

    private String hdfsPosition;

    private String uuid;

    private String uploadTitle;

    private String uploader;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String note1;

    private String note2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getServerPosition() {
        return serverPosition;
    }

    public void setServerPosition(String serverPosition) {
        this.serverPosition = serverPosition == null ? null : serverPosition.trim();
    }

    public String getHdfsPosition() {
        return hdfsPosition;
    }

    public void setHdfsPosition(String hdfsPosition) {
        this.hdfsPosition = hdfsPosition == null ? null : hdfsPosition.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUploadTitle() {
        return uploadTitle;
    }

    public void setUploadTitle(String uploadTitle) {
        this.uploadTitle = uploadTitle == null ? null : uploadTitle.trim();
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1 == null ? null : note1.trim();
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2 == null ? null : note2.trim();
    }
}