package com.zyl.centre.service;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Imgsrc;

public interface IImgService extends IOperations<Imgsrc> {
	public List<Imgsrc> getImagsByServiceid(int serid);

	public List<Imgsrc> getImagsByShopid(int shopid);
}
