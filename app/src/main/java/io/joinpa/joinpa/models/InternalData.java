package io.joinpa.joinpa.models;

import io.joinpa.joinpa.R;

/**
 * Created by TAWEESOFT on 5/14/16 AD.
 */
public class InternalData {

    private static InternalData internalData;

    public Token token = null;
    public User user = null;
    public int[] avatarNormal = {
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.avatar_4,
            R.drawable.avatar_5,
            R.drawable.avatar_6,
            R.drawable.avatar_7,
            R.drawable.avatar_8,
            R.drawable.avatar_9,
            R.drawable.avatar_10,
            R.drawable.avatar_11,
            R.drawable.avatar_12
    };
    private InternalData() {}

    public static InternalData getInstance() {
        if (internalData == null) internalData = new InternalData();
        return internalData;
    }


}
