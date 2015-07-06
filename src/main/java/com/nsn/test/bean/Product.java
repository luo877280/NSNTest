package com.nsn.test.bean;
public class Product  extends BaseBean{
	
	String brand;
	String category;
	String id;
	String model;
	String price;
	String productID;
	String siteName;
	String title;
	String url;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Product [brand=" + brand + ", category=" + category + ", id="
				+ id + ", model=" + model + ", price=" + price + ", productID="
				+ productID + ", siteName=" + siteName + ", title=" + title
				+ ", url=" + url + "]";
	}

	public String getOutputFiled(){
		return title;
	}
	public String geProductCategory(){
		return brand;
	}
	public String getSource(){
		return siteName;
	}
}
