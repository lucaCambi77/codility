/**
 * 
 */
package it.cambi.codility.coreJava;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luca
 *
 */

@Getter
@Setter
@EqualsAndHashCode
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
