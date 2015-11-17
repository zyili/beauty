package com.zyl.centre.entity;

// Generated 2015-11-17 16:17:44 by Hibernate Tools 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Imgsrc generated by hbm2java
 */
@Entity
@Table(name = "imgsrc", catalog = "beautycentre")
public class Imgsrc implements java.io.Serializable {

	private Integer imgid;
	private Shop shop;
	private Service service;
	private String imgname;
	private String url;
	private String imgdec;
	private Integer reviewid;

	public Imgsrc() {
	}

	public Imgsrc(Shop shop, Service service, String imgname, String url,
			String imgdec, Integer reviewid) {
		this.shop = shop;
		this.service = service;
		this.imgname = imgname;
		this.url = url;
		this.imgdec = imgdec;
		this.reviewid = reviewid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "imgid", unique = true, nullable = false)
	public Integer getImgid() {
		return this.imgid;
	}

	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopid")
	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceid")
	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Column(name = "imgname", length = 32)
	public String getImgname() {
		return this.imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "imgdec", length = 65535)
	public String getImgdec() {
		return this.imgdec;
	}

	public void setImgdec(String imgdec) {
		this.imgdec = imgdec;
	}

	@Column(name = "reviewid")
	public Integer getReviewid() {
		return this.reviewid;
	}

	public void setReviewid(Integer reviewid) {
		this.reviewid = reviewid;
	}

}
