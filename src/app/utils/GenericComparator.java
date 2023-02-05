package app.utils;

import java.util.Comparator;

public class GenericComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        if ((int) o1 < (int) o2) {
            return -1;
        } else if ((int) o1 > (int) o2) {
            return 1;
        } else {
            return 0;
        }
    }

}
