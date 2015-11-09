package com.zyl.centre.service;

import java.util.List;

import javax.annotation.Resource;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.dao.IImgsrcDao;

import com.zyl.centre.entity.Imgsrc;


import org.springframework.stereotype.Service;
@Service("imgService")
public class ImgService extends AbstractService<Imgsrc> implements IImgService {

	@Resource(name = "imgsrcDao")
	private IImgsrcDao dao;

	@Override
	protected IOperations<Imgsrc> getDao() {
		// TODO Auto-generated method stub
		return this.dao;
	}

	@Override
	public List<Imgsrc> getImagsByServiceid(int serid) {
		// TODO Auto-generated method stub
		return dao.getImagsByServiceid(serid);
	}

	@Override
	public List<Imgsrc> getImagsByShopid(int shopid) {
		// TODO Auto-generated method stub
		return dao.getImagsByShopid(shopid);
	}
}
