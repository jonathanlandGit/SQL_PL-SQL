package ss;

public class ProductManual {
	
	private int productId;
	private String fileName;
	private String mongoDBFileName;
	
	public ProductManual() {
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMongoDBFileName() {
		return mongoDBFileName;
	}

	public void setMongoDBFileName(String mongoDBFileName) {
		this.mongoDBFileName = mongoDBFileName;
	}

	@Override
	public String toString() {
		return "ProductManual [productId=" + productId + ", fileName="
				+ fileName + ", mongoDBFileName=" + mongoDBFileName + "]";
	}
}
