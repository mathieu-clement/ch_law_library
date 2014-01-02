package com.mathieuclement.lib.chlaw.skeleton;

import java.util.HashMap;
import java.util.Map;

public class LocalizedString {
    public Map<Language, String> map = new HashMap<Language, String>();

    public void set(Language language, String str) {
        map.put(language, str);
    }

    public String get(Language language) {
        return map.get(language);
    }
}
