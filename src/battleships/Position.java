/*
 * Class Position
 * 
 */
package batteships;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MARGARET WRIGHT
 * @author keith
 */

@AllArgsConstructor
public class Position {

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int x;

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int y;
}
