package com.changlu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: changlu
 * @Date: 2:30 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    private Integer orderId;
    private String orderName;
    private Double price;

}
