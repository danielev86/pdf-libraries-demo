package com.redcatdev86.openpdfdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryShopDataBean {

    private PersonBean personBean;

    private List<ProductBean> products;

}
