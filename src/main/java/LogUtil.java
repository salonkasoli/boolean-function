/**
 * Утилита, помогающая считать логарифмы.
 */
public class LogUtil {

    /**
     * returns 0 for bits=0
     * Логарифм по основанию 2.
     * @param bits Откуда считаем логарифм.
     * @return Значения логарифма.
     */
    public static int binlog( int bits ) {
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }
}
