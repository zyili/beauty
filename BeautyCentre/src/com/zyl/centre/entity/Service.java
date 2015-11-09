package com.zyl.centre.entity;

// Generated 2015-11-5 8:35:54 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Service generated by hbm2java
 */
@Entity
@Table(name = "service", catalog = "beautycentre")
public class Service implements java.io.Serializable {

	private Integer serviceid;
	private Shop shop;
	private Product product;
	private String servicename;
	private Date createtime;
	private Integer submituserid;
	private Date modifydatetime;
	private Integer modifyuserid;
	private Integer price;
	private String servicedec;
	private String ext1;
	private String ext2;
	private Set<Imgsrc> imgsrcs = new HashSet<Imgsrc>(0);
	private Set<Serviceordrel> serviceordrels = new HashSet<Serviceordrel>(0);
	private Set<Prodtyperel> prodtyperels = new HashSet<Prodtyperel>(0);

	public Service() {
	}

	public Service(Shop shop) {
		this.shop = shop;
	}

	public Service(Shop shop, Product product, String servicename,
			Date createtime, Integer submituserid, Date modifydatetime,
			Integer modifyuserid, Integer price, String servicedec,
			String ext1, String ext2, Set<Imgsrc> imgsrcs,
			Set<Serviceordrel> serviceordrels, Set<Prodtyperel> prodtyperels) {
		this.shop = shop;
		this.product = product;
		this.servicename = servicename;
		this.createtime = createtime;
		this.submituserid = submituserid;
		this.modifydatetime = modifydatetime;
		this.modifyuserid = modifyuserid;
		this.price = price;
		this.servicedec = servicedec;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.imgsrcs = imgsrcs;
		this.serviceordrels = serviceordrels;
		this.prodtyperels = prodtyperels;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "serviceid", unique = true, nullable = false)
	public Integer getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopid", nullable = false)
	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "servicename", length = 20)
	public String getServicename() {
		return this.servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "submituserid")
	public Integer getSubmituserid() {
		return this.submituserid;
	}

	public void setSubmituserid(Integer submituserid) {
		this.submituserid = submituserid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifydatetime", length = 0)
	public Date getModifydatetime() {
		return this.modifydatetime;
	}

	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}

	@Column(name = "modifyuserid")
	public Integer getModifyuserid() {
		return this.modifyuserid;
	}

	public void setModifyuserid(Integer modifyuserid) {
		this.modifyuserid = modifyuserid;
	}

	@Column(name = "price")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "servicedec", length = 65535)
	public String getServicedec() {
		return this.servicedec;
	}

	public void setServicedec(String servicedec) {
		this.servicedec = servicedec;
	}

	@Column(name = "ext1", length = 10)
	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "ext2", length = 20)
	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
	public Set<Imgsrc> getImgsrcs() {
		return this.imgsrcs;
	}

	public void setImgsrcs(Set<Imgsrc> imgsrcs) {
		this.imgsrcs = imgsrcs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
	public Set<Serviceordrel> getServiceordrels() {
		return this.serviceordrels;
	}

	public void setServiceordrels(Set<Serviceordrel> serviceordrels) {
		this.serviceordrels = serviceordrels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "service")
	public Set<Prodtyperel> getProdtyperels() {
		return this.prodtyperels;
	}

	public void setProdtyperels(Set<Prodtyperel> prodtyperels) {
		this.prodtyperels = prodtyperels;
	}

}
