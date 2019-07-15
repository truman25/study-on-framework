package com.example.demo.vo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


public class DemoReq {

	@NotBlank(message="code不能为空")
    String code;
    
	@Length(max=10,message="name长度不能超过10")
    String name;
	
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
