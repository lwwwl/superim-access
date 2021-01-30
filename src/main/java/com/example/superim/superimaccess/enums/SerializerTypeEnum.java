package com.example.superim.superimaccess.enums;

/**
 * @Description: 报文解析类型 枚举类
 * @author: i_laowei
 * @date: 2021/1/19  10:36
 */

public enum SerializerTypeEnum {

    JSONSerializer((byte) 1);

    private byte algorithmType;

    SerializerTypeEnum(byte algorithmType) {
        this.algorithmType = algorithmType;
    }

    public byte getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(byte algorithmType) {
        this.algorithmType = algorithmType;
    }
}
