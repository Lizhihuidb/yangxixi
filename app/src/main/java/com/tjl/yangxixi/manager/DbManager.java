package com.tjl.yangxixi.manager;

import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.DbModelSelector;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;

/**
 * 对db的操作
 * dbutils
 */
public class DbManager {
	private static String DBNAME="jiuyi.db";
	private DbManager() {
	}

	private static DbUtils getDbUtils(Context context) {
		return DbUtils.create(context, DBNAME);
	}
	public static void configDbName(){
	}


	public static void deleteById(Context context, Class<?> entityType,
								  Object idValue) throws DbException {
		getDbUtils(context).deleteById(entityType, idValue);
	}

	public static void update(Context context,Object entity, String... updateColumnNames) throws DbException{
		getDbUtils(context).update(entity, updateColumnNames);
	}


	public static void update(Context context,Object entity, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
		getDbUtils(context).update(entity, whereBuilder, updateColumnNames);
	}


	public static void updateAll(Context context,List<?> entities, String... updateColumnNames) throws DbException {
		getDbUtils(context).updateAll(entities, updateColumnNames);
	}
	public static void updateAll(Context context,List<?> entities, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
		getDbUtils(context).updateAll(entities, whereBuilder, updateColumnNames);
	}

	public static <T> T findFirst(Context context,Selector selector) {
		try {
			return getDbUtils(context).findFirst(selector);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> T findFirst(Context context,Class<T>entityType) {
		try {
			return getDbUtils(context).findFirst(entityType);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}



	public static void deleteAll(Context context, Class<?> entityType)
			throws DbException {
		getDbUtils(context).deleteAll(entityType);
	}


	public static void deleteAll(Context context, List<?> entities)
			throws DbException {
		getDbUtils(context).deleteAll(entities);
	}


	public static void delete(Context context, Class<?> entityType,
							  WhereBuilder whereBuilder) throws DbException {
		getDbUtils(context).delete(entityType, whereBuilder);
	}

	public static <T> List<T> findAll(Context context, Selector selector)
			throws DbException {

		return getDbUtils(context).findAll(selector);
	}
	public static List<DbModel> findDbModelAll(Context context,DbModelSelector selector) throws DbException {
		return getDbUtils(context).findDbModelAll(selector);
	}

	public static void execNoQuery(Context context,String sql){
		try {
			getDbUtils(context).execNonQuery(sql);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	public static Cursor execQuery(Context context,String sql){
		try {
			return getDbUtils(context).execQuery(sql);
		} catch (DbException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> List<T> findAll(Context context, Class<T> entityType)
			throws DbException {
		return getDbUtils(context).findAll(entityType);
	}


	public static <T> T findById(Context context, Class<T> entityType,
								 Object idValue) throws DbException {
		return getDbUtils(context).findById(entityType, idValue);
	}

	public static void save(Context context, Object obj) throws DbException {
		getDbUtils(context).save(obj);
	}

	public static void saveAll(Context context, List<?> entities)
			throws DbException {
		getDbUtils(context).saveAll(entities);
	}
	public static void saveBindingId(Context context, Object entity)
			throws DbException {
		getDbUtils(context).saveBindingId(entity);
	}
	public static void saveBindingIdAll(Context context, List<?> entities)
			throws DbException {
		getDbUtils(context).saveBindingIdAll(entities);
	}

	public static void saveOrUpdate(Context context, Object entity)
			throws DbException {
		getDbUtils(context).saveOrUpdate(entity);
	}
	public static void saveOrUpdateAll(Context context, List<?> entities)
			throws DbException {
		getDbUtils(context).saveOrUpdateAll(entities);
	}

}

