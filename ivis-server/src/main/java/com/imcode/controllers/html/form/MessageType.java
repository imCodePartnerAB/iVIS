package com.imcode.controllers.html.form;

/**
 * Created by vitaly on 08.04.15.
 */
public enum MessageType {
    ERROR, SUCCESS;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
