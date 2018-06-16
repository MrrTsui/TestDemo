package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: ProductMenuVO
 * </p>
 *
 * <p>
 * Description:商品类目VO
 * </p>
 * <p>
 * <p>
 * 使用单例设计，保证效率，不用重复查询SQL
 *
 * @author wansiliang
 * @date 2018年4月6日
 */
public class ProductMenuVO implements Serializable {


    private static ProductMenuVO productMenuVO = null;

    private ProductMenuVO() {

    }

    public static ProductMenuVO getInstance() {
        //双重判断，减少逻辑循环
        if (productMenuVO == null) {
            synchronized ("ProductMenuVO") {
                if (productMenuVO == null) {
                    productMenuVO = new ProductMenuVO();
                }
                return productMenuVO;
            }
        }
        return productMenuVO;
    }

    /**
     * bigint 类目ID
     */
    private Long menuId;
    /**
     * bigint 父类目ID=0时，代表的是一级的类目
     */
    private Long parentMenuId;

    /**
     * varchar 类目名称
     */
    private String menuName;

    /**
     * int 状态。可选值:1(正常),2(删除)
     */
    private Character status;
    /**
     * int 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    private Integer sortOrder;
    /**
     * tinyint 该类目是否为父类目，1为true，0为false
     */
    private Byte isParent;
    /**
     * datetime 创建时间
     */
    private Date createTime;
    /**
     * datetime 创建时间
     */
    private Date updateTime;

    /**
     * 商品总数
     */
    private Integer totalNum;

    /**
     * 类别下的分组
     */
    private String paramGroups;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Byte getIsParent() {
        return isParent;
    }

    public void setIsParent(Byte isParent) {
        this.isParent = isParent;
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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getParamGroups() {
        return paramGroups;
    }

    public void setParamGroups(String paramGroups) {
        this.paramGroups = paramGroups;
    }

    @Override
    public String toString() {
        return "ProductMenuVO{" +
                "menuId=" + menuId +
                ", parentMenuId=" + parentMenuId +
                ", menuName='" + menuName + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", isParent=" + isParent +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", totalNum=" + totalNum +
                ", paramGroups='" + paramGroups + '\'' +
                '}';
    }
}
