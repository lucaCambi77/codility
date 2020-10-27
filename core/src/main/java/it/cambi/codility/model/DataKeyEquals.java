/**
 *
 */
package it.cambi.codility.model;

import lombok.Data;

/**
 * @author luca
 *
 */

@Data
public class DataKeyEquals
{

    protected String name;
    protected int id;

    @Override
    public String toString()
    {
        return "DataKey [name=" + name + ", id=" + id + "]";
    }
}
