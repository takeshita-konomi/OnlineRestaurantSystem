package bean;

import lombok.Data;

@Data
public class Message implements java.io.Serializable{
	
	//InvalidClassException(エラー)が出ないようにするために宣言している
	private static final long serialVersionUID = 1L;

	private String message;
}




