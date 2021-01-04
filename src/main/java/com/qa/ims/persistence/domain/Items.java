package com.qa.ims.persistence.domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Items {

	private Long productID;
	private String productName;
	private String artist;
	private Date releaseDate;
	private BigDecimal productPrice;
	private Double productQty;

	public Items(String productName, String artist, Date releaseDate, BigDecimal productPrice, Double productQty) {
		this.productName = productName;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}

	public Items(Long productID, String productName, String artist, Date releaseDate, BigDecimal productPrice, Double productQty) {
		this.productID = productID;
		this.productName = productName;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductQty() {
		return productQty;
	}

	public void setProductQty(Double productQty) {
		this.productQty = productQty;
	}

	@Override
	public String toString() {
		return "Product ID:" + productID + " Product Name:" + productName + " Artist:" + artist + " Release Date:" + releaseDate + "Product Price:" + productPrice + " Product Qty:" + productQty;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productQty == null) {
			if (other.productQty != null)
				return false;
		} else if (!productQty.equals(other.productQty))
			return false;
		return true;
	}
}
