package test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class BarcodeEntity {
	@JsonProperty("id") private int id;
	@JsonProperty("idItem") private int idItem;
	@JsonProperty("nameProduct") private String nameProduct;
	@JsonProperty("codeBar") private String codeBar;
	@JsonProperty("cant") private int cant;
	
	
	public BarcodeEntity() {
		// TODO Auto-generated constructor stub
	}

	
	
	public BarcodeEntity(String nameProduct, String codeBar) {
		super();
		this.nameProduct = nameProduct;
		this.codeBar = codeBar;
	}



	public BarcodeEntity(int id, String nameProduct, String codeBar, int cant) {
		super();
		this.id = id;
		this.nameProduct = nameProduct;
		this.codeBar = codeBar;
		this.cant = cant;
	}
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getCodeBar() {
		return codeBar;
	}

	public void setCodeBar(String codeBar) {
		this.codeBar = codeBar;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	
	

}
