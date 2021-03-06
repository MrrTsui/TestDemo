package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * <p>
 * Title: ProductVO
 * </p>
 *
 * <p>
 * Description: 商品基本信息类
 * </p>
 *
 * @author shenlan
 *
 * @date 2018年4月5日
 */
public class ProductVO implements Serializable {

	/** serialVersionUID */

	private static final long serialVersionUID = -140284367761604417L;

	private Long productId;
	private String title;
	private String sellPoint;
	private Long price;
	private Long num;
	private String barcode;
	private String image;
	private Long cid;
	/**
	 * 不能未NULL
	 */
	private Character status;
	/**
	 * 描述，存在另一张表中
	 */
	private String desc;
	private Date createTime;
	private Date updateTime;
	/**
	 * 商品总数
	 */
	private Integer totalNum;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Character getStatus() {
		if(null == status) {
			setStatus('1');;
		}
		return status ;
	}

	public void setStatus(Character status) {
		this.status = status;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", title=" + title + ", sellPoint=" + sellPoint + ", price="
				+ price + ", num=" + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status="
				+ status + ", desc=" + desc + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", totalNum=" + totalNum + "]";
	}

}
