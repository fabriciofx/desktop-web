package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsHtml;

public final class TkIndex implements Take {
	@Override
	public Response act(final Request req) throws IOException {
		return new RsHtml(
			TkIndex.class.getClassLoader()
				.getResourceAsStream("webapp/index.html")
		);
	}
}
