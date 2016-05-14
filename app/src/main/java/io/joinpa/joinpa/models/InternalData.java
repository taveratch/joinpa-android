package io.joinpa.joinpa.models;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public class InternalData {
    private static InternalData internalData;

    private InternalData() {}

    public static InternalData getInstance() {
        if( internalData == null ) internalData = new InternalData();
        return internalData;
    }

    public Token token = null;
    public User user = null;
}
