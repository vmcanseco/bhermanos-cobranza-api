/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bhermanos.cobranza.api.resources;

/**
 *
 * @author canseco.victor
 */
public class KeyValue {
    
    private Integer Key;
    private String value;

    public KeyValue() {
    }

    public KeyValue(Integer Key, String value) {
        this.Key = Key;
        this.value = value;
    }

    public Integer getKey() {
        return Key;
    }

    public void setKey(Integer Key) {
        this.Key = Key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
}
