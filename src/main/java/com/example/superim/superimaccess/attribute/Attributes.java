package com.example.superim.superimaccess.attribute;

import com.example.superim.superimaccess.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
