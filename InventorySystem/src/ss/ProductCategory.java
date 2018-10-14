package ss;

public class ProductCategory {
	private String code;
	private String description;
	
	public ProductCategory() {
	}

	public ProductCategory(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductCategory [code=" + code + ", description=" + description
				+ "]";
	}
}
