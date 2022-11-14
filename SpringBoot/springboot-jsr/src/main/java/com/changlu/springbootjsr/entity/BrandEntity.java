package com.changlu.springbootjsr.entity;

import com.changlu.springbootjsr.valid.AddGroup;
import com.changlu.springbootjsr.valid.ListValue;
import com.changlu.springbootjsr.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 品牌
 * 
 * @author changlu
 * @email 939974883@qq.com
 * @date 2022-11-05 16:20:08
 */
@Data
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id：
	 */
	@Null(message = "品牌id必须为空", groups = {AddGroup.class})
	@NotNull(message = "品牌id不能为空", groups = {UpdateGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "品牌logo地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@URL(message = "logo必须是一个合法的URL地址", groups = {AddGroup.class, UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	@NotBlank(message = "描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示] , message = "只能传入0或者1"
	 */
	@NotNull(message = "展示状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@ListValue(vals = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
	private Integer showStatus;

	/**
	 * 检索首字母
	 */
	@NotNull(message = "首字母不为空", groups = {AddGroup.class, UpdateGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
	private String firstLetter;

	/**
	 * 排序
	 */
	@NotNull(message = "排序字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Min(value = 0, message = "排序必须要大于等于0", groups = {AddGroup.class, UpdateGroup.class})
	private Integer sort;

}
