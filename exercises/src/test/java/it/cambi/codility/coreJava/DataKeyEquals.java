/**
 *
 */
package it.cambi.codility.coreJava;

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
