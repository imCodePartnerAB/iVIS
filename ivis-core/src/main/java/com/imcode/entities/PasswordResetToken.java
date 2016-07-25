package com.imcode.entities;

import javax.persistence.*;

/**
 * Created by ruslan on 22.07.16.
 */
@Entity
@Table(name = "dbo_password_reset_token")
public class PasswordResetToken extends AbstractToken {
}
