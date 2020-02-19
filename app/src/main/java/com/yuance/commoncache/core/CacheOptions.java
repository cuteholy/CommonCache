package com.yuance.commoncache.core;

import android.text.TextUtils;

/**
 * Created by michaelliu on 29/12/15.
 * 针对缓存的配置
 */
public class CacheOptions {

	/**
	 * SharedPreferences存储
	 */
	public static final int TYPE_PREFERENCE = 0;
	/**
	 * SnappyDB存储
	 */
	public static final int TYPE_DB         = 1;
	/**
	 * MK存储
	 */
	public static final int TYPE_MK         = 2;

	private String mFileName     = null;
	private int     mCacheType    = 0;
	private String mAbsolutePath = null;
	private boolean mDebug        = false;

	private CacheOptions(Builder builder) {
		mFileName = builder.fileName;
		mCacheType = builder.cacheType;
		mAbsolutePath = builder.absolutePath;
		mDebug = builder.debug;
	}

	public String getFileName() {
		return mFileName;
	}

	public int getCacheType() {
		return mCacheType;
	}

	public String getAbsolutePath() {
		return mAbsolutePath;
	}

	public boolean isDebug() {
		return mDebug;
	}

	public static class Builder {

		private String fileName     = null;
		private int     cacheType    = 0;
		private String absolutePath = null;
		private boolean debug        = false;

		/**
		 * 是否开启调试模式
		 *
		 * @param debug
		 * @return
		 */
		public Builder debugable(boolean debug) {
			this.debug = debug;
			return this;
		}

		/**
		 * 默认的缓存文件名
		 *
		 * @param fileName
		 * @return
		 */
		public Builder defaultFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}

		/**
		 * 默认的缓存类型
		 *
		 * @param cacheType
		 * @return
		 * @see #TYPE_DB
		 * @see #TYPE_PREFERENCE
		 */
		public Builder defaultCacheType(int cacheType) {
			this.cacheType = cacheType;
			return this;
		}

		/**
		 * 默认缓存文件路径，目前只支持SnappyDB存储自定义缓存路径，eg：SD卡
		 *
		 * @param absolutePath
		 * @return
		 */
		public Builder defaultFilePath(String absolutePath) {
			this.absolutePath = absolutePath;
			return this;
		}

		public CacheOptions build() {
			if (TextUtils.isEmpty(fileName)) {
				throw new NullPointerException("fileName can't be null,please invoke defaultFileName(String fileName)");
			}
			if (this.cacheType == TYPE_PREFERENCE && !TextUtils.isEmpty(absolutePath)) {
				throw new RuntimeException("not support custom absolutePath of TYPE_PREFERENCE yet. Please use TYPE_DB or use the SharedPreferences default path.");
			}
			return new CacheOptions(this);
		}
	}
}
