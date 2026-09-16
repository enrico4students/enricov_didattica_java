/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.didattica.scuola.verifiche;

import java.util.HashMap;

/**
 *
 * @author enric
 */
class Verifica_q2<K, V> {
    
    HashMap<K, V> table = new HashMap<K, V>();
    
    public V getValue(K key) {
        return (V)table.get(key);
    }
    
}
