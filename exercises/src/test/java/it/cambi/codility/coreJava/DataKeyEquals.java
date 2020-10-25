/**
 *
 */
package it.cambi.codility.coreJava;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
