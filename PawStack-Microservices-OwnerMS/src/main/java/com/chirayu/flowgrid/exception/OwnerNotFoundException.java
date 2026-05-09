package com.chirayu.flowgrid.exception;

import java.io.Serial;

/**
 * @author chirayu
 * @created 2026-05-01
 */

public class OwnerNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public OwnerNotFoundException(String message) {
        super(message);
    }

}
