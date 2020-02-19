package com.yuance.commoncache.impl;

import com.tencent.mmkv.MMKV;
import com.yuance.commoncache.core.ICache;

import static com.esotericsoftware.minlog.Log.debug;

/**
 * @creator : yangshuai
 * @create-time : 2019/1/9 1:45 PM
 * @description :微信开源支持多进程的轻量存储
 */
public class MkCache implements ICache {

    private MMKV mmkv;

    public MkCache(String fildName) {
        mmkv = MMKV.mmkvWithID(fildName);
    }

    @Override
    public ICache put(String key, boolean value) {
        mmkv.encode(key, value);
        return this;
    }

    @Override
    public ICache put(String key, float value) {
        mmkv.encode(key, value);
        debug("put boolean value: " + value + " success");
        return this;
    }

    @Override
    public ICache put(String key, int value) {
        mmkv.encode(key, value);
        debug("put int value: " + value + " success");
        return this;
    }

    @Override
    public ICache put(String key, long value) {
        mmkv.encode(key, value);
        debug("put long value: " + value + " success");
        return this;
    }

    @Override
    public ICache put(String key, String value) {
        mmkv.encode(key, value);
        debug("put string value: " + value + " success");
        return this;
    }

    @Override
    public boolean get(String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    @Override
    public float get(String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    @Override
    public int get(String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    @Override
    public long get(String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    @Override
    public String get(String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    @Override
    public boolean contains(String key) {
        return mmkv.contains(key);
    }

    @Override
    public ICache remove(String key) {
        mmkv.removeValueForKey(key);
        return this;
    }

    @Override
    public void apply() {

    }

    @Override
    public ICache clear() {
        mmkv.clearAll();
        return this;
    }
}
