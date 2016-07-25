package com.imcode.entities;

import com.imcode.AbstractToken;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ruslan on 25.07.16.
 */
@Entity
@Table(name = "dbo_verification_token")
public class VerificationToken extends AbstractToken {
}
