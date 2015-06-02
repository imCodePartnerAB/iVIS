package com.imcode.imcms.addon.ivisclient.controllers.form;

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
