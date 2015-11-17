package com.zyl.centre.entity;

// Generated 2015-11-17 16:17:44 by Hibernate Tools 3.2.2.GA

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "beautycentre")
public class Product implements java.io.Serializable {

	private Integer productid;
	private String productname;
	private Date createtime;
	private String productdec;
	private String ext1;
	private String ext2;
	private String ext3;
	private Set<Prodtyperel> prodtyperels = new HashSet<Prodtyperel>(0);
	private Set<Service> services = new HashSet<Service>(0);
	private Set<Prodtype> prodtypes = new HashSet<Prodtype>(0);

	public Product() {
	}

	public Product(String productname, Date createtime, String productdec,
			String ext1, String ext2, String ext3,
			Set<Prodtyperel> prodtyperels, Set<Service> services,
			Set<Prodtype> prodtypes) {
		this.productname = productname;
		this.createtime = createtime;
		this.productdec = productdec;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.ext3 = ext3;
		this.prodtyperels = prodtyperels;
		this.services = services;
		this.prodtypes = prodtypes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productid", unique = true, nullable = false)
	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	@Column(name = "productname", length = 20)
	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", length = 0)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "productdec", length = 65535)
	public String getProductdec() {
		return this.productdec;
	}

	public void setProductdec(String productdec) {
		this.productdec = productdec;
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

	@Column(name = "ext3", length = 30)
	public String getExt3() {
		return this.ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Prodtyperel> getProdtyperels() {
		return this.prodtyperels;
	}

	public void setProdtyperels(Set<Prodtyperel> prodtyperels) {
		this.prodtyperels = prodtyperels;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Service> getServices() {
		return this.services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Prodtype> getProdtypes() {
		return this.prodtypes;
	}

	public void setProdtypes(Set<Prodtype> prodtypes) {
		this.prodtypes = prodtypes;
	}

}
