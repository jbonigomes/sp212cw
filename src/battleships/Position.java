/*
 * Class Position
 * 
 */

package battleships;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MARGARET WRIGHT
 * @author KLM
 * @author Jose B. Gomes
 */

public class Position
{
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int x;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int y;

    public Position(int x, int y)
    {
    	this.x = x;
    	this.y = y;
    }
}
