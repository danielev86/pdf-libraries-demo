package com.redcatdev86.openpdfdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String productName;

    private String productCode;

    private int unit;

    private BigDecimal price;

}
