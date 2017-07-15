package cn.edu.zhku.service;

/*
 * 用于获取下载文件的信息以及下载文件时更新文件下载次数
 */


import java.util.Map;

import cn.edu.zhku.util.DBUtil;

public class CommonService {
	private DBUtil db = new DBUtil();
	public Map getDownLoadFile(String id){
		String sql = "select filePath,fileName from files where id = ?";
		return db.getMap(sql, new String[] {id});
	}
	public void updateHits(String id){
		String sql = "update files set hits = hits + 1 where id = ?";
		db.update(sql, new String[] {id});
	}
}
