package com.imcode.entities;

import com.imcode.AbstractToken;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ruslan on 22.07.16.
 */
@Entity
@Table(name = "dbo_password_reset_token")
public class PasswordResetToken extends AbstractToken {
}
