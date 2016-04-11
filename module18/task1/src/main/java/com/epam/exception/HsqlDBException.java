package com.epam.exception;

import java.sql.SQLException;

public class HsqlDBException extends SQLException {
	private static final long serialVersionUID = -1409601674994371940L;

	public HsqlDBException(String message) {
		super(message);
	}

	public HsqlDBException(String message, Throwable ex) {
		super(message, ex);
	}
}
