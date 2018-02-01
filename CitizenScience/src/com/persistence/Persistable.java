package com.persistence;

/**
 * Created by marne on 11/16/2016.
 */
public interface Persistable {

    public long getId();
    public void setId( int id );
    public boolean isPersistent();
}
