package com.qa.ims.persistence.domain;

public class Item {

	private Long productID;
	private String productName;
	private String artistName;
	private Double releaseDate;
	private Double productPrice;
	private Double productQty;
	

	public Item(String productName, String artistName, Double releaseDate, Double productPrice, Double productQty) {
		this.productName = productName;
		this.artistName = artistName;
		this.releaseDate = releaseDate;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}

	public Item(Long productID, String productName, String artistName, Double releaseDate, Double productPrice, Double productQty) {
		this.productID = productID;
		this.productName = productName;
		this.artistName = artistName;
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

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Double getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Double releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductQty() {
		return productQty;
	}

	public void setProductQty(Double productQty) {
		this.productQty = productQty;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
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
		if (artistName == null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(other.artistName))
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