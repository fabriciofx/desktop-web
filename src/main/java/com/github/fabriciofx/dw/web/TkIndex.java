package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsWithBody;
import org.takes.rs.RsWithStatus;
import org.takes.rs.RsWithType;

public final class TkIndex implements Take {
	@Override
	public Response act(final Request req) throws IOException {
		return new RsWithStatus(
			new RsWithType(
				new RsWithBody("<html>Bem-vindo a App Web!</html>"),
				"text/html"
			),
			200
		);
	}
}
