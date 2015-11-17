package com.zyl.centre.dao;

import java.util.List;

import com.zyl.centre.common.utils.IOperations;
import com.zyl.centre.entity.Imgsrc;

public interface IImgsrcDao extends IOperations<Imgsrc>{
  public List<Imgsrc> getImagsByServiceid(int serid);
  public List<Imgsrc> getImagsByShopid(int shopid);
  void deleteImgByServiceid(int servid);
  void deleteImgByShopid(int servid);
}
