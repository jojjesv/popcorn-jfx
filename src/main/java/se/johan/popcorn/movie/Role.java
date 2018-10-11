/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.movie;

/**
 *
 * @author johansvensson
 */
public enum Role {
    DIRECTOR, CAST, WRITER;
    
    static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.toString().equalsIgnoreCase(role)){
                return r;
            }
        }
        
        throw new IllegalArgumentException("Unknown role: " + role);
    }
}
