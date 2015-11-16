package com.zyl.centre.dao;

import com.zyl.centre.common.utils.IOperations;

import com.zyl.centre.entity.Product;

public interface IProductDao extends IOperations<Product> {
	Product findOneById(int prodid);
}
